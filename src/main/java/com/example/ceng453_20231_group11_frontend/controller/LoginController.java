package com.example.ceng453_20231_group11_frontend.controller;

import com.example.ceng453_20231_group11_frontend.NavigationHistoryManager;
import com.example.ceng453_20231_group11_frontend.Utils;
import com.example.ceng453_20231_group11_frontend.constants.GeneralConstants;
import com.example.ceng453_20231_group11_frontend.service.UserService;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Pane pane;

    @FXML
    private ProgressIndicator loader;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> pane.requestFocus());
    }

    @FXML
    public void onClickLoginButton(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Show the loader
        loader.setVisible(true);

        // Validate input
        if (username.isEmpty()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Error", "Username cannot be empty.");
            return;
        }
        if (password.isEmpty()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Error", "Password cannot be empty.");
            return;
        }

        // Create the background task
        Task<Pair<Integer, String>> loginTask = new Task<Pair<Integer, String>>() {
            @Override
            protected Pair<Integer, String> call() {
                return UserService.login(username, password);
            }
        };

        // Set the on succeeded event handler
        loginTask.setOnSucceeded(e -> {
            // Hide the loader
            loader.setVisible(false);

            Pair<Integer, String> response = loginTask.getValue();

            if (response.getKey() == 200) {
                Utils.showAlert(Alert.AlertType.INFORMATION, "Success", "Login successful.");
                try {
                    Utils.routeToPage(event, GeneralConstants.HOME_PAGE);
                } catch (Exception ex) {
                    Utils.showAlert(Alert.AlertType.ERROR, "Error", "Error routing to home page.");
                }
            } else {
                Utils.showAlert(Alert.AlertType.ERROR, "Error", response.getValue());
            }
        });

        // Set the on failed event handler
        loginTask.setOnFailed(e -> {
            // Hide the loader and show the error message
            loader.setVisible(false);
            Utils.showAlert(Alert.AlertType.ERROR, "Error", "An error occurred during login.");
        });

        // Start the task on a background thread
        new Thread(loginTask).start();
    }

    @FXML
    public void onClickBackButton(ActionEvent event) {
        Parent previousPage = NavigationHistoryManager.pop();
        if (previousPage != null) {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene existingScene = previousPage.getScene();

            if (existingScene == null) {
                existingScene = new Scene(previousPage);
            }

            currentStage.setScene(existingScene);
            currentStage.show();
        }
    }


}

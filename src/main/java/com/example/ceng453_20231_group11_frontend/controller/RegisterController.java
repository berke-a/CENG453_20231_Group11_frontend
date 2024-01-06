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
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private TextField emailField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Pane pane;

    @FXML
    private ProgressIndicator loader;

    @FXML
    private ImageView backgroundImage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Platform.runLater(() -> pane.requestFocus());
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(GeneralConstants.REGISTER_BACKGROUND_IMAGE)));
        backgroundImage.setImage(image);
    }

    @FXML
    protected void onClickRegisterButton(ActionEvent event) {
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Validate input
        if (email.isEmpty()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Error", "Email cannot be empty.");
            return;
        }

        if (username.isEmpty()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Error", "Username cannot be empty.");
            return;
        }

        if (password.isEmpty()) {
            Utils.showAlert(Alert.AlertType.ERROR, "Error", "Password cannot be empty.");
            return;
        }

        // Show the loader
        loader.setVisible(true);

        // Create the background task for registration
        Task<Pair<Integer, String>> registrationTask = new Task<Pair<Integer, String>>() {
            @Override
            protected Pair<Integer, String> call() {
                return UserService.register(email, username, password);
            }
        };

        // Set the on succeeded event handler
        registrationTask.setOnSucceeded(e -> {
            // Hide the loader
            loader.setVisible(false);

            Pair<Integer, String> response = registrationTask.getValue();

            if (response.getKey() == 200) {
                Utils.showAlert(Alert.AlertType.INFORMATION, "Success", "Registration successful.");
                Utils.routeToPage(event, GeneralConstants.LOGIN_PAGE);
            } else {
                Utils.showAlert(Alert.AlertType.ERROR, "Error", response.getValue());
            }
        });

        // Set the on failed event handler
        registrationTask.setOnFailed(e -> {
            // Hide the loader and show the error message
            loader.setVisible(false);
            Utils.showAlert(Alert.AlertType.ERROR, "Error", "An error occurred during registration.");
        });

        // Start the task on a background thread
        new Thread(registrationTask).start();
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

package com.example.ceng453_20231_group11_frontend.controller;

import com.example.ceng453_20231_group11_frontend.NavigationHistoryManager;
import com.example.ceng453_20231_group11_frontend.Utils;
import com.example.ceng453_20231_group11_frontend.constants.GeneralConstants;
import com.example.ceng453_20231_group11_frontend.service.UserService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.net.URL;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> pane.requestFocus());
    }

    @FXML
    protected void onClickRegisterButton(ActionEvent event) {

        String email = emailField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

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

        Pair<Integer, String> Response = UserService.register(email, username, password);

        if (Response.getKey() != 200) {
            Utils.showAlert(Alert.AlertType.ERROR, "Error", Response.getValue());
            return;
        }

        Utils.showAlert(Alert.AlertType.INFORMATION, "Success", "Registration successful.");
        Utils.routeToPage(event, GeneralConstants.LOGIN_PAGE);
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

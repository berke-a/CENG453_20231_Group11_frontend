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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;


public class ResetPasswordController implements Initializable {

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField tokenField;

    @FXML
    private Pane pane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> pane.requestFocus());
    }

    @FXML
    private void onClickResetPassword(ActionEvent event) {
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String token = tokenField.getText();

        if (!newPassword.equals(confirmPassword)) {
            Utils.showAlert(Alert.AlertType.ERROR, "Error", "Passwords do not match.");
            return;
        }

        Pair<Integer, String> response = UserService.changePassword(token, newPassword);
        if (response.getKey() == 200) {
            Utils.showAlert(Alert.AlertType.INFORMATION, "Success", "Password successfully changed.");
            Utils.routeToPage(event, GeneralConstants.HOME_PAGE);
        } else {
            Utils.showAlert(Alert.AlertType.ERROR, "Error", "Failed to change password. " + response.getValue());
        }
    }

    @FXML
    public void onClickResendEmail(ActionEvent event) {
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

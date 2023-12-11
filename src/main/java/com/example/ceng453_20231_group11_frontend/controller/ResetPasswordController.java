package com.example.ceng453_20231_group11_frontend.controller;

import com.example.ceng453_20231_group11_frontend.NavigationHistoryManager;
import com.example.ceng453_20231_group11_frontend.Utils;
import com.example.ceng453_20231_group11_frontend.constants.GeneralConstants;
import com.example.ceng453_20231_group11_frontend.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Pair;

public class ResetPasswordController {

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField tokenField;

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
            navigateToHomePage(event);
        } else {
            Utils.showAlert(Alert.AlertType.ERROR, "Error", "Failed to change password. " + response.getValue());
        }
    }


    private void navigateToHomePage(ActionEvent event) {
        try {
            Utils.routeToPage(event, GeneralConstants.HOME_PAGE);
        } catch (Exception e) {
            Utils.showAlert(Alert.AlertType.ERROR, "Navigation Error", "Error navigating to the home page.");
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

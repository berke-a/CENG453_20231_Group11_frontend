package com.example.ceng453_20231_group11_frontend.controller;

import com.example.ceng453_20231_group11_frontend.CatanApplication;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Pair;

public class ForgotPasswordController {
    @FXML
    private TextField emailField;

    @FXML
    private void onClickSubmitButton(ActionEvent event) {
        String email = emailField.getText();
        Pair<Integer, String> response = UserService.requestPasswordReset(email);

        if (response.getKey() == 200) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Password reset email sent successfully.");
            try {
                Parent resetPasswordPage = CatanApplication.loadFXML(GeneralConstants.RESET_PASSWORD_PAGE);
                Utils.routeToPage(event, resetPasswordPage);
                System.out.println("Successfully routed to reset password page!");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                showAlert(Alert.AlertType.ERROR, "Error", "Error processing the request.");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to send password reset email. " + response.getValue());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
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

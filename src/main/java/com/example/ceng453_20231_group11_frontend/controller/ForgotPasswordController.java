package com.example.ceng453_20231_group11_frontend.controller;

import com.example.ceng453_20231_group11_frontend.CatanApplication;
import com.example.ceng453_20231_group11_frontend.NavigationHistoryManager;
import com.example.ceng453_20231_group11_frontend.Utils;
import com.example.ceng453_20231_group11_frontend.constants.GeneralConstants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ForgotPasswordController {
    @FXML
    private TextField emailField;

    @FXML
    private void onClickSubmitButton(ActionEvent event) {
        try {
            String email = emailField.getText();
            Parent resetPasswordPage = CatanApplication.loadFXML(GeneralConstants.RESET_PASSWORD_PAGE);
            Utils.routeToPage(event, resetPasswordPage);
            System.out.println("Successfully routed to reset password page!");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
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

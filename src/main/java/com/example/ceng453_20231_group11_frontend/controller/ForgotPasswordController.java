package com.example.ceng453_20231_group11_frontend.controller;

import com.example.ceng453_20231_group11_frontend.NavigationHistoryManager;
import com.example.ceng453_20231_group11_frontend.Utils;
import com.example.ceng453_20231_group11_frontend.constants.GeneralConstants;
import com.example.ceng453_20231_group11_frontend.service.UserService;
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
import javafx.application.Platform;

import java.net.URL;
import java.util.ResourceBundle;

public class ForgotPasswordController implements Initializable {
    @FXML
    private TextField emailField;

    @FXML
    private Pane pane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> pane.requestFocus());
    }

    @FXML
    private void onClickSubmitButton(ActionEvent event) {
        String email = emailField.getText();
        Pair<Integer, String> response = UserService.requestPasswordReset(email);

        if (response.getKey() == 200) {
            Utils.showAlert(Alert.AlertType.INFORMATION, "Success", "Password reset email sent successfully.");
            Utils.routeToPage(event, GeneralConstants.RESET_PASSWORD_PAGE);
        } else {
            Utils.showAlert(Alert.AlertType.ERROR, "Error", "Failed to send password reset email. " + response.getValue());
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

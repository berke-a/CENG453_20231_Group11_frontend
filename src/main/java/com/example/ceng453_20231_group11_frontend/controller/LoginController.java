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
import org.w3c.dom.Text;

public class LoginController {

    @FXML
    TextField usernameField;

    @FXML
    TextField passwordField;

    @FXML
    public void onClickLoginButton(ActionEvent event) {
        try{
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (username.isEmpty()) {
                Utils.showAlert(Alert.AlertType.ERROR, "Error", "Username cannot be empty.");
                return;
            }
            if (password.isEmpty()) {
                Utils.showAlert(Alert.AlertType.ERROR, "Error", "Password cannot be empty.");
                return;
            }

            Pair<Integer, String> Response = UserService.login(username, password);

            if (Response.getKey() != 200) {
                throw new Exception(Response.getValue());
            }

            Utils.showAlert(Alert.AlertType.INFORMATION, "Success", "Login successful.");
            Parent homePage = CatanApplication.loadFXML(GeneralConstants.HOME_PAGE);
            Utils.routeToPage(event, homePage);

        } catch (Exception e) {
            System.out.println("An error occured: " + e.getMessage());
            Utils.showAlert(Alert.AlertType.ERROR, "Error", "Login failed. ");
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

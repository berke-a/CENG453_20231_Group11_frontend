package com.example.ceng453_20231_group11_frontend.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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

        System.out.println(newPassword);
        System.out.println(confirmPassword);
        System.out.println(token);
    }
}

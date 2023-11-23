package com.example.ceng453_20231_group11_frontend.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class RegisterController {
    @FXML
    private Text registerTitle;

    @FXML
    protected void onRegisterButtonClick() {
        registerTitle.setText("Registered Succesfully!");
    }

}

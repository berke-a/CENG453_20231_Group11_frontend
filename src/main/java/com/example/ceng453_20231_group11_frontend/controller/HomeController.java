package com.example.ceng453_20231_group11_frontend.controller;

import com.example.ceng453_20231_group11_frontend.CatanApplication;
import com.example.ceng453_20231_group11_frontend.Utils;
import com.example.ceng453_20231_group11_frontend.constants.GeneralConstants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;

public class HomeController {

    @FXML
    public void onClickRouteRegister(ActionEvent event) {
        try {
            Parent registerPage = CatanApplication.loadFXML(GeneralConstants.REGISTER_PAGE);
            Utils.routeToPage(event, registerPage);
            System.out.println("Succesfully routed to register page!");
        } catch (Exception e) {
            System.out.println("An error occured: " + e.getMessage());
        }
    }

    @FXML
    protected void onClickRouteLogin(ActionEvent event) {
        try {
            Parent loginPage = CatanApplication.loadFXML(GeneralConstants.LOGIN_PAGE);
            Utils.routeToPage(event, loginPage);
            System.out.println("Succesfully routed to register page!");
        } catch (Exception e) {
            System.out.println("An error occured: " + e.getMessage());
        }
    }

    @FXML
    protected void onClickRouteLeaderboard() {
        System.out.println("Leaderboard button clicked!");
    }

}

package com.example.ceng453_20231_group11_frontend.controller;

import com.example.ceng453_20231_group11_frontend.CatanApplication;
import com.example.ceng453_20231_group11_frontend.Utils;
import com.example.ceng453_20231_group11_frontend.constants.GeneralConstants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;

public class HomeController {

    @FXML
    Button playButton;

    @FXML
    protected void onClickPlay(ActionEvent event) {
        System.out.println("Play button clicked!");
    }

    @FXML
    protected void onClickRouteRegister(ActionEvent event) {
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
    protected void onClickRouteLeaderboard(ActionEvent event) {
        try {
            Parent leaderboardPage = CatanApplication.loadFXML(GeneralConstants.LEADERBOARD_PAGE);
            Utils.routeToPage(event, leaderboardPage);
        } catch (Exception e) {
            System.out.println("An error occured: " + e.getMessage());
        }
    }

    @FXML
    protected void onClickRouteForgotPassword(ActionEvent event) {
        try {
            Parent forgotPasswordPage = CatanApplication.loadFXML(GeneralConstants.FORGOT_PASSWORD_PAGE);
            Utils.routeToPage(event, forgotPasswordPage);
            System.out.println("Succesfully routed to forgot password page!");
        } catch (Exception e) {
            System.out.println("An error occured: " + e.getMessage());
        }
    }

}

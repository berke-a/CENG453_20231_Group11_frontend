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
    private Button playButton;

    @FXML
    protected void onClickPlay(ActionEvent event) {
        System.out.println("Play button clicked!");
    }

    @FXML
    protected void onClickRouteRegister(ActionEvent event) {
        try {
            Utils.routeToPage(event, GeneralConstants.REGISTER_PAGE);
            System.out.println("Succesfully routed to register page!");
        } catch (Exception e) {
            System.out.println("An error occured: " + e.getMessage());
        }
    }

    @FXML
    protected void onClickRouteLogin(ActionEvent event) {
        try {
            Utils.routeToPage(event, GeneralConstants.LOGIN_PAGE);
            System.out.println("Succesfully routed to register page!");
        } catch (Exception e) {
            System.out.println("An error occured: " + e.getMessage());
        }
    }

    @FXML
    protected void onClickRouteLeaderboard(ActionEvent event) {
        try {
            Utils.routeToPage(event, GeneralConstants.LEADERBOARD_PAGE);
        } catch (Exception e) {
            System.out.println("An error occured: " + e.getMessage());
        }
    }

    @FXML
    protected void onClickRouteForgotPassword(ActionEvent event) {
        try {
            Utils.routeToPage(event, GeneralConstants.FORGOT_PASSWORD_PAGE);
            System.out.println("Succesfully routed to forgot password page!");
        } catch (Exception e) {
            System.out.println("An error occured: " + e.getMessage());
        }
    }

}

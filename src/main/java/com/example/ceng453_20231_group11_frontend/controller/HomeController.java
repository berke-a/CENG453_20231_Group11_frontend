package com.example.ceng453_20231_group11_frontend.controller;

import com.example.ceng453_20231_group11_frontend.Utils;
import com.example.ceng453_20231_group11_frontend.constants.GeneralConstants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Button playButton;

    @FXML
    private Button routeRegisterButton;

    @FXML
    private Button routeLoginButton;

    @FXML
    private Text welcomeText;

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        Boolean isLoggedIn = Utils.isLoggedIn();
        if (isLoggedIn) {
            routeRegisterButton.setVisible(false);
            routeLoginButton.setVisible(false);
            welcomeText.setText("Welcome, " + Utils.getUsername());
        } else {
            playButton.setVisible(false);
        }
    }

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

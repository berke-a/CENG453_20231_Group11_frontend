package com.example.ceng453_20231_group11_frontend.controller;

import com.example.ceng453_20231_group11_frontend.Utils;
import com.example.ceng453_20231_group11_frontend.constants.GeneralConstants;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private Button playButton;

    @FXML
    private Button routeRegisterButton;

    @FXML
    private Button routeLoginButton;

    @FXML
    private Button routeForgotPasswordButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Text welcomeText;

    @FXML
    private Pane pane;

    @FXML
    private ImageView backgroundImage;

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        Platform.runLater(() -> pane.requestFocus());
        Boolean isLoggedIn = Utils.isLoggedIn();
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(GeneralConstants.HOME_BACKGROUND_IMAGE)));
        backgroundImage.setImage(image);
        if (isLoggedIn) {
            routeRegisterButton.setVisible(false);
            routeLoginButton.setVisible(false);
            routeForgotPasswordButton.setVisible(false);
            welcomeText.setText("Welcome, " + Utils.getUsername());
        } else {
            //playButton.setVisible(false);
            logoutButton.setVisible(false);
        }
    }

    @FXML
    protected void onClickPlay(ActionEvent event) {
        Utils.routeToPage(event, GeneralConstants.BOARD_PAGE);
    }

    @FXML
    protected void onClickLogout(ActionEvent event) {
        Utils.logout();
        Utils.routeToPage(event, GeneralConstants.HOME_PAGE);
    }

    @FXML
    protected void onClickRouteRegister(ActionEvent event) {
        Utils.routeToPage(event, GeneralConstants.REGISTER_PAGE);
    }

    @FXML
    protected void onClickRouteLogin(ActionEvent event) {
        Utils.routeToPage(event, GeneralConstants.LOGIN_PAGE);
    }

    @FXML
    protected void onClickRouteLeaderboard(ActionEvent event) {
        Utils.routeToPage(event, GeneralConstants.LEADERBOARD_PAGE);
    }

    @FXML
    protected void onClickRouteForgotPassword(ActionEvent event) {
        Utils.routeToPage(event, GeneralConstants.FORGOT_PASSWORD_PAGE);
    }

    @FXML
    protected void onClickRouteGameRules(ActionEvent event) {
        Utils.routeToPage(event, GeneralConstants.GAME_RULES_PAGE);
    }

}

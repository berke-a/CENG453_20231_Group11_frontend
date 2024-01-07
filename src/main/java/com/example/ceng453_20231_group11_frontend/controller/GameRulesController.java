package com.example.ceng453_20231_group11_frontend.controller;

import com.example.ceng453_20231_group11_frontend.Utils;
import com.example.ceng453_20231_group11_frontend.constants.GeneralConstants;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class GameRulesController implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private ImageView backgroundImage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> pane.requestFocus());
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(GeneralConstants.GAME_RULES_BACKGROUND_IMAGE)));
        backgroundImage.setImage(image);
    }

    @FXML
    public void onClickRouteBack(ActionEvent event) {
        Utils.routeToPage(event, GeneralConstants.HOME_PAGE);
    }
}

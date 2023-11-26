package com.example.ceng453_20231_group11_frontend.controller;

import com.example.ceng453_20231_group11_frontend.CatanApplication;
import com.example.ceng453_20231_group11_frontend.Utils;
import com.example.ceng453_20231_group11_frontend.constants.GeneralConstants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;

public class LeaderboardController {

    @FXML
    public void onClickRouteBack(ActionEvent event) {
        try {
            Parent homePage = CatanApplication.loadFXML(GeneralConstants.HOME_PAGE);
            Utils.routeToPage(event, homePage);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}

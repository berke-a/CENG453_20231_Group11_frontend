package com.example.ceng453_20231_group11_frontend.controller;

import com.example.ceng453_20231_group11_frontend.Utils;
import com.example.ceng453_20231_group11_frontend.constants.GeneralConstants;
import com.example.ceng453_20231_group11_frontend.models.LeaderboardItem;
import com.example.ceng453_20231_group11_frontend.service.LeaderboardService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class LeaderboardController implements Initializable {

    @FXML
    private TableView<LeaderboardItem> weeklyTableView;

    @FXML
    private TableView<LeaderboardItem> monthlyTableView;

    @FXML
    private TableView<LeaderboardItem> alltimeTableView;

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        System.out.println("init");
        // TODO weekly init sirasinda doldurulacak.
        // TODO loader gosterilecek
    }

    public void onGetWeeklyClick() {
        List<Map<String, Object>> leaderboard = LeaderboardService.getWeeklyLeaderboard();
        if (leaderboard != null) {
            ObservableList<LeaderboardItem> leaderboardItemList = FXCollections.observableArrayList();
            for (Map<String, Object> leaderboardItemMap : leaderboard) {
                LeaderboardItem newItem = new LeaderboardItem(leaderboardItemMap);
                leaderboardItemList.add(newItem);
            }
            weeklyTableView.getItems().clear();
            weeklyTableView.setItems(leaderboardItemList);
        }
    }

    public void onGetMonthlyClick() {
        List<Map<String, Object>> leaderboard = LeaderboardService.getMonthlyLeaderboard();
        if (leaderboard != null) {
            ObservableList<LeaderboardItem> leaderboardItemList = FXCollections.observableArrayList();
            for (Map<String, Object> leaderboardItemMap : leaderboard) {
                LeaderboardItem newItem = new LeaderboardItem(leaderboardItemMap);
                leaderboardItemList.add(newItem);
            }
            monthlyTableView.getItems().clear();
            monthlyTableView.setItems(leaderboardItemList);
        }
    }

    public void onGetAlltimeClick() {
        List<Map<String, Object>> leaderboard = LeaderboardService.getAlltimeLeaderboard();
        if (leaderboard != null) {
            ObservableList<LeaderboardItem> leaderboardItemList = FXCollections.observableArrayList();
            for (Map<String, Object> leaderboardItemMap : leaderboard) {
                LeaderboardItem newItem = new LeaderboardItem(leaderboardItemMap);
                leaderboardItemList.add(newItem);
            }
            alltimeTableView.getItems().clear();
            alltimeTableView.setItems(leaderboardItemList);
        }
    }

    @FXML
    public void onClickRouteBack(ActionEvent event) {
        try {
            Utils.routeToPage(event, GeneralConstants.HOME_PAGE);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}

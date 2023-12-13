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
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class LeaderboardController implements Initializable {

    @FXML
    private Tab monthlyTab;

    @FXML
    private Tab alltimeTab;

    @FXML
    private TableView<LeaderboardItem> weeklyTableView;

    @FXML
    private TableView<LeaderboardItem> monthlyTableView;

    @FXML
    private TableView<LeaderboardItem> alltimeTableView;

    private boolean monthlyDataFetched = false;
    private boolean alltimeDataFetched = false;

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        getWeekly();
        // TODO loader gosterilecek
    }

    public void getWeekly() {
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

    public void getMonthly() {
        List<Map<String, Object>> leaderboard = LeaderboardService.getMonthlyLeaderboard();
        if (leaderboard != null) {
            ObservableList<LeaderboardItem> leaderboardItemList = FXCollections.observableArrayList();
            for (Map<String, Object> leaderboardItemMap : leaderboard) {
                LeaderboardItem newItem = new LeaderboardItem(leaderboardItemMap);
                leaderboardItemList.add(newItem);
            }
            monthlyTableView.getItems().clear();
            monthlyTableView.setItems(leaderboardItemList);
            monthlyDataFetched = true;
        }
    }

    public void getAlltime() {
        List<Map<String, Object>> leaderboard = LeaderboardService.getAlltimeLeaderboard();
        if (leaderboard != null) {
            ObservableList<LeaderboardItem> leaderboardItemList = FXCollections.observableArrayList();
            for (Map<String, Object> leaderboardItemMap: leaderboard) {
                LeaderboardItem newItem = new LeaderboardItem(leaderboardItemMap);
                leaderboardItemList.add(newItem);
            }
            alltimeTableView.getItems().clear();
            alltimeTableView.setItems(leaderboardItemList);
            alltimeDataFetched = true;
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

    public void onMonthlySelectionChange() {
        if (monthlyTab.isSelected() && !monthlyDataFetched) {
            getMonthly();
        }
    }

    public void onAlltimeSelectionChange() {
        if (alltimeTab.isSelected() && !alltimeDataFetched) {
            getAlltime();
        }
    }
}

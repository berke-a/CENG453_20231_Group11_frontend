package com.example.ceng453_20231_group11_frontend.controller;

import com.example.ceng453_20231_group11_frontend.Utils;
import com.example.ceng453_20231_group11_frontend.constants.GeneralConstants;
import com.example.ceng453_20231_group11_frontend.models.LeaderboardItem;
import com.example.ceng453_20231_group11_frontend.service.LeaderboardService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

    @FXML
    private Pane pane;

    @FXML
    private ImageView backgroundImage;

    @FXML
    private ProgressIndicator loader;

    private boolean monthlyDataFetched = false;
    private boolean alltimeDataFetched = false;

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        Platform.runLater(() -> pane.requestFocus());
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(GeneralConstants.LEADERBOARD_BACKGROUND_IMAGE)));
        backgroundImage.setImage(image);
        getWeekly();
        // TODO loader gosterilecek
    }

    public void getWeekly() {
        loader.setVisible(true);

        Task<List<Map<String, Object>>> getWeeklyLeaderboardTask = new Task<List<Map<String, Object>>>() {
            @Override
            protected List<Map<String, Object>> call() {
                return LeaderboardService.getWeeklyLeaderboard();
            }
        };

        getWeeklyLeaderboardTask.setOnSucceeded(e -> {
            loader.setVisible(false);
            List<Map<String, Object>> leaderboard = getWeeklyLeaderboardTask.getValue();
            if (leaderboard != null) {
                ObservableList<LeaderboardItem> leaderboardItemList = FXCollections.observableArrayList();
                for (Map<String, Object> leaderboardItemMap : leaderboard) {
                    LeaderboardItem newItem = new LeaderboardItem(leaderboardItemMap);
                    leaderboardItemList.add(newItem);
                }
                weeklyTableView.getItems().clear();
                weeklyTableView.setItems(leaderboardItemList);
            }
        });

        getWeeklyLeaderboardTask.setOnFailed(e -> {
            loader.setVisible(false);
            Utils.showAlert(Alert.AlertType.ERROR, "Error", "An error occurred during retrieving leaderboard.");
        });

        new Thread(getWeeklyLeaderboardTask).start();
    }

    public void getMonthly() {
        loader.setVisible(true);

        Task<List<Map<String, Object>>> getMonthlyLeaderboardTask = new Task<List<Map<String, Object>>>() {
            @Override
            protected List<Map<String, Object>> call() {
                return LeaderboardService.getMonthlyLeaderboard();
            }
        };

        getMonthlyLeaderboardTask.setOnSucceeded(e -> {
            loader.setVisible(false);
            List<Map<String, Object>> leaderboard = getMonthlyLeaderboardTask.getValue();
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
        });

        getMonthlyLeaderboardTask.setOnFailed(e -> {
            loader.setVisible(false);
            Utils.showAlert(Alert.AlertType.ERROR, "Error", "An error occurred during retrieving leaderboard.");
        });

        new Thread(getMonthlyLeaderboardTask).start();
    }

    public void getAlltime() {
        loader.setVisible(true);

        Task<List<Map<String, Object>>> getAlltimeLeaderboardTask = new Task<List<Map<String, Object>>>() {
            @Override
            protected List<Map<String, Object>> call() {
                return LeaderboardService.getAlltimeLeaderboard();
            }
        };

        getAlltimeLeaderboardTask.setOnSucceeded(e -> {
            loader.setVisible(false);
            List<Map<String, Object>> leaderboard = getAlltimeLeaderboardTask.getValue();
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
        });

        getAlltimeLeaderboardTask.setOnFailed(e -> {
            loader.setVisible(false);
            Utils.showAlert(Alert.AlertType.ERROR, "Error", "An error occurred during retrieving leaderboard.");
        });

        new Thread(getAlltimeLeaderboardTask).start();
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

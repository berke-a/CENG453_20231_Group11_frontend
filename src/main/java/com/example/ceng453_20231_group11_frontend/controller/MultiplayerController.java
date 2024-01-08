package com.example.ceng453_20231_group11_frontend.controller;

import com.example.ceng453_20231_group11_frontend.Utils;
import com.example.ceng453_20231_group11_frontend.constants.GeneralConstants;
import com.example.ceng453_20231_group11_frontend.models.GameLobbyItem;
import com.example.ceng453_20231_group11_frontend.service.GameLobbyService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

public class MultiplayerController implements Initializable {
    @FXML
    private Pane pane;

    @FXML
    private ProgressIndicator loader;

    @FXML
    private ImageView backgroundImage;

    @FXML
    private TableView<GameLobbyItem> gameLobbyTableView;

    @FXML
    private Label noLobbyText;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> pane.requestFocus());
        loader.toFront();
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(GeneralConstants.GAME_LOBBY_BACKGROUND_IMAGE)));
        backgroundImage.setImage(image);
        getAllLobies();
        noLobbyText.setVisible(false);
    }

    @FXML
    public void onClickRouteBack(ActionEvent event) {
        Utils.routeToPage(event, GeneralConstants.HOME_PAGE);
    }

    @FXML
    public void onClickRefresh(ActionEvent event) {
        gameLobbyTableView.getItems().clear();
        getAllLobies();
    }

    public void getAllLobies() {
        loader.setVisible(true);

        Task<List<Object>> getGameLobbiesTask = new Task<List<Object>>() {
            @Override
            protected List<Object> call() throws Exception {
                return GameLobbyService.getGameLobbies();
            }
        };

        getGameLobbiesTask.setOnSucceeded(e -> {
            loader.setVisible(false);
            noLobbyText.setVisible(false);

            List<Object> gameLobbies = getGameLobbiesTask.getValue();

            if (!gameLobbies.isEmpty()) {
                ObservableList<GameLobbyItem> gameLobbyList = FXCollections.observableArrayList();
                for (Object gameLobby : gameLobbies) {
                    Map<String, Object> gameLobbyMap = (Map<String, Object>) gameLobby;
                    Double gameLobbyId = (Double) gameLobbyMap.get("id");
                    Double playerCount = (Double) gameLobbyMap.get("playerCount");
                    String gameState = (String) gameLobbyMap.get("gameState");

                    Integer gameLobbyIdInt = gameLobbyId.intValue();
                    Integer playerCountInt = playerCount.intValue();

                    String gameLobbyName = "Room " + gameLobbyIdInt.toString();
                    GameLobbyItem gameLobbyItem = new GameLobbyItem(gameLobbyName, playerCountInt.toString(), gameState);
                    gameLobbyList.add(gameLobbyItem);
                }
                gameLobbyTableView.getItems().clear();
                gameLobbyTableView.setItems(gameLobbyList);
            }

            else {
                noLobbyText.setVisible(true);
            }


        });

        getGameLobbiesTask.setOnFailed(e -> {
            loader.setVisible(false);
            noLobbyText.setVisible(true);
            Utils.showAlert(Alert.AlertType.ERROR, "Error", "An error occurred during retrieving game lobbies.");
        });

        new Thread(getGameLobbiesTask).start();
    }
}

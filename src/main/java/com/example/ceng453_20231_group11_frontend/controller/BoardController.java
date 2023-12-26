package com.example.ceng453_20231_group11_frontend.controller;

import com.example.ceng453_20231_group11_frontend.constants.GeneralConstants;
import com.example.ceng453_20231_group11_frontend.enums.PlayerColor;
import com.example.ceng453_20231_group11_frontend.enums.ResourceType;
import com.example.ceng453_20231_group11_frontend.enums.TurnPlayerState;
import com.example.ceng453_20231_group11_frontend.enums.TurnState;
import com.example.ceng453_20231_group11_frontend.models.*;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;
import javafx.util.Pair;

import java.net.URL;
import java.util.*;

import static com.example.ceng453_20231_group11_frontend.enums.TurnPlayerState.TURN_RED;

public class BoardController extends BoardControllerAbstract {
    Dice dice = new Dice();

    private final GameManager gameManager = GameManager.getInstance();

    private Timeline timer;

    private Player player = new Player(PlayerColor.RED);
    private CPUPlayer[] cpuPlayers = new CPUPlayer[3];

    private Player longestRoadPlayer = null;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            this.initializeTiles();
            this.initializeCircles();
            this.initializeCpuPlayers();
            this.rollDiceButton.setDisable(true);
            this.endTurnButton.setDisable(true);
            this.helpContentTable.setVisible(false);
            this.gameManager.turnState = TurnState.INITIALIZATION;
            this.updateGameState();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void onClickHelpButton() {
        boolean isVisible = !this.helpContentTable.isVisible();
        this.helpContentTable.setVisible(isVisible);

        if (isVisible) {
            this.helpContentTable.toFront();
        } else {
            this.helpContentTable.toBack();
        }
    }

    public void onClickRollDice() {
        if (timer != null) {
            timer.stop(); // Stop the timer if the button is clicked
        }

        this.manageDiceUpdate();
    }

    public void onClickEndTurn() {
        if (timer != null) {
            timer.stop();
        }
        this.changePlayerBuildingColor(Color.GRAY);
        this.advanceToNextTurn();
    }

    private void updateGameState() {
        this.updateVpCounts();
        switch (gameManager.turnState) {
            case INITIALIZATION:
                this.playerInitialPlacement();
                break;
            case ROLL_DICE:
                this.manageDiceRoll();
                break;
            case RESOURCE_DISTRIBUTION:
                this.distributeResources();
                break;
            case TURN_PLAYER:
                this.managePlayerTurn();
                break;
        }
    }

    private void managePlayerTurn() {
        this.logTextArea.appendText("- Player " + this.gameManager.turnPlayerState.toString() + " is Playing\n");

        // Handle the turn based on the current player
        switch (this.gameManager.turnPlayerState) {
            case TURN_RED:
                this.endTurnButton.setDisable(false);
                this.changePlayerBuildingColor(Color.RED);
                this.setTimeOut(60, this::advanceToNextTurn);
                break;
            case TURN_BLUE:
                this.cpuPlayers[0].play(gameManager, circleMap, occupiedEdges);
                advanceToNextTurn();
                break;
            case TURN_GREEN:
                this.cpuPlayers[1].play(gameManager, circleMap, occupiedEdges);
                advanceToNextTurn();
                break;
            case TURN_ORANGE:
                this.cpuPlayers[2].play(gameManager, circleMap, occupiedEdges);
                advanceToNextTurn();
                break;
        }
    }

    private void advanceToNextTurn() {
        this.endTurnButton.setDisable(true);
        this.resetHighlighting();
        this.changePlayerBuildingColor(Color.GRAY);
        this.gameManager.turnPlayerState = this.gameManager.turnPlayerState.next();
        this.gameManager.turnState = TurnState.ROLL_DICE;
        this.updateGameState();
    }

    private void initializeCpuPlayers() {
        this.cpuPlayers[0] = new CPUPlayer(PlayerColor.BLUE);
        this.cpuPlayers[1] = new CPUPlayer(PlayerColor.GREEN);
        this.cpuPlayers[2] = new CPUPlayer(PlayerColor.ORANGE);
    }

    private void distributeResources() {
        this.logTextArea.appendText("- Distribute Resources\n");

        this.distributeResourcesPlayer();
        this.distributeResourcesCPU();
        this.updateCardCounts();

        this.gameManager.turnState = TurnState.TURN_PLAYER;
        this.updateGameState();
    }

    private void manageDiceRoll() {
        if (this.gameManager.turnPlayerState == TURN_RED) {
            this.rollDiceButton.setDisable(false);
            this.logTextArea.appendText("- Please Roll Dice: 10 Seconds\n");
            this.animateDiceButton();

            // Start or restart the timer
            setTimeOut(10, this::onClickRollDice);

        } else {
            this.logTextArea.appendText("- Please Wait For Your Turn\n");
            this.rollDiceButton.setDisable(true);

            setTimeOut(2, this::onClickRollDice);
        }
    }


    private void manageDiceUpdate() {
        dice.roll();
        this.updateDiceText();
        this.rollDiceButton.setDisable(true);
        this.logTextArea.appendText("- Player " + this.gameManager.turnPlayerState.toString() + " rolled the dice.\n");
        this.logTextArea.appendText("- Dice Total: " + this.dice.getDiceTotal() + "\n");
        this.gameManager.turnState = TurnState.RESOURCE_DISTRIBUTION;
        this.updateGameState();
    }

    private void playerInitialPlacement() {
        this.logTextArea.appendText("- Player Initial Placement\n");

        placeInitialSettlementAndRoad(player);
        updatePlayerResourceCount();

        for (CPUPlayer cpuPlayer : cpuPlayers) {
            placeInitialSettlementAndRoad(cpuPlayer);
        }

        this.gameManager.turnState = TurnState.ROLL_DICE;
        this.updateGameState();
    }

    private void placeInitialSettlementAndRoad(PlayerAbstract player) {
        List<Circle> circleKeys = new ArrayList<>(circleMap.keySet());
        Collections.shuffle(circleKeys);

        Circle settlementCircle = null;
        CircleVertex settlementCircleVertex = null;

        // Find a valid CircleVertex for the settlement
        for (Circle circle : circleKeys) {
            CircleVertex circleVertex = circleMap.get(circle);
            if (circleVertex != null && !circleVertex.isHasSettlement() && !circleVertex.isHasCity()) {
                settlementCircle = circle;
                settlementCircleVertex = circleVertex;
                break;
            }
        }

        if (settlementCircle == null) {
            return; // No valid location found
        }

        // Place the settlement
        buildSettlement(player, settlementCircle);
        player.settlements.add(settlementCircleVertex);
        player.updateVictoryPoint(1);
        settlementCircleVertex.setHasSettlement(true);
        settlementCircleVertex.setOwner(player);

        // Select and place the road
        if (!settlementCircleVertex.getAdjacentCircles().isEmpty()) {
            List<Circle> adjacentCircles = new ArrayList<>(settlementCircleVertex.getAdjacentCircles());
            Collections.shuffle(adjacentCircles);

            for (Circle roadEndCircle : adjacentCircles) {
                Pair<Circle, Circle> edge = createEdge(settlementCircle, roadEndCircle);
                if (!occupiedEdges.contains(edge)) {
                    Road road = new Road(settlementCircle, roadEndCircle, player.color.getColor(), boardGroup);
                    player.roads.add(new Pair<>(settlementCircleVertex, circleMap.get(roadEndCircle)));
                    occupiedEdges.add(edge);
                    break; // Exit the loop once the road is successfully placed
                }
            }
        }

        // Update resources for initial settlement
        updateInitialResources(player, settlementCircle);
    }

    private void updateInitialResources(PlayerAbstract player, Circle settlementCircle) {
        CircleVertex vertex = circleMap.get(settlementCircle);
        if (vertex == null) {
            return;
        }

        for (Polygon polygon : vertex.adjacentTiles) {
            Tile adjacentTile = polygonTileHashMap.get(polygon);
            if (adjacentTile == null) {
                continue;
            }

            ResourceType resourceType = GeneralConstants.tileTypeToResourceType.get(adjacentTile.getTileType());
            if (resourceType == null) {
                continue;
            }

            player.updateResource(resourceType, 1);
        }
    }

    private Pair<Circle, Circle> createEdge(Circle c1, Circle c2) {
        return c1.getId().compareTo(c2.getId()) <= 0 ? new Pair<>(c1, c2) : new Pair<>(c2, c1);
    }

    private void distributeResourcesPlayer() {
        for (CircleVertex playerSettlement : this.player.settlements) {
            for (Polygon polygon : playerSettlement.adjacentTiles) {
                Tile adjacentTile = this.polygonTileHashMap.get(polygon);

                if (Objects.equals(adjacentTile.getNumberToken(), this.dice.getDiceTotal())) {
                    this.player.updateResource(GeneralConstants.tileTypeToResourceType.get(adjacentTile.getTileType()), 1);
                }
            }
        }

        for (CircleVertex playerCity : this.player.cities) {
            for (Polygon polygon : playerCity.adjacentTiles) {
                Tile adjacentTile = this.polygonTileHashMap.get(polygon);

                if (Objects.equals(adjacentTile.getNumberToken(), this.dice.getDiceTotal())) {
                    this.player.updateResource(GeneralConstants.tileTypeToResourceType.get(adjacentTile.getTileType()), 1);
                }
            }
        }
    }

    private void distributeResourcesCPU() {
        for (CPUPlayer cpuPlayer : this.cpuPlayers) {
            for (CircleVertex cpuSettlement : cpuPlayer.settlements) {
                for (Polygon polygon : cpuSettlement.adjacentTiles) {
                    Tile adjacentTile = this.polygonTileHashMap.get(polygon);

                    if (Objects.equals(adjacentTile.getNumberToken(), this.dice.getDiceTotal())) {
                        this.player.updateResource(GeneralConstants.tileTypeToResourceType.get(adjacentTile.getTileType()), 1);
                    }
                }
            }

            for (CircleVertex cpuCity : cpuPlayer.cities) {
                for (Polygon polygon : cpuCity.adjacentTiles) {
                    Tile adjacentTile = this.polygonTileHashMap.get(polygon);

                    if (Objects.equals(adjacentTile.getNumberToken(), this.dice.getDiceTotal())) {
                        this.player.updateResource(GeneralConstants.tileTypeToResourceType.get(adjacentTile.getTileType()), 1);
                    }
                }
            }
        }
    }

    private void updateDiceText() {
        this.diceText1.setText(dice.getStringDie1());
        this.diceText2.setText(dice.getStringDie2());
        this.diceTotalText.setText(dice.getStringDieTotal());
    }

    private void updateCardCounts() {
        this.updatePlayerResourceCount();
        for (CPUPlayer cpuPlayer : this.cpuPlayers) {
            switch (cpuPlayer.color) {
                case BLUE:
                    this.cpuBlueCardCount.setText(cpuPlayer.getTotalResource().toString());
                    break;
                case ORANGE:
                    this.cpuOrangeCardCount.setText(cpuPlayer.getTotalResource().toString());
                    break;
                case GREEN:
                    this.cpuGreenCardCount.setText(cpuPlayer.getTotalResource().toString());
                    break;
            }
        }
    }

    private void updatePlayerResourceCount() {
        this.playerWoolCount.setText(this.player.getResource(ResourceType.WOOL).toString());
        this.playerLumberCount.setText(this.player.getResource(ResourceType.LUMBER).toString());
        this.playerBrickCount.setText(this.player.getResource(ResourceType.BRICK).toString());
        this.playerGrainCount.setText(this.player.getResource(ResourceType.GRAIN).toString());
        this.playerOreCount.setText(this.player.getResource(ResourceType.ORE).toString());
    }

    private void updateVpCounts() {
        this.playerVpCount.setText(this.player.getVictoryPoint().toString());
        this.cpuBlueVpCount.setText(this.cpuPlayers[0].getVictoryPoint().toString());
        this.cpuGreenVpCount.setText(this.cpuPlayers[1].getVictoryPoint().toString());
        this.cpuOrangeVpCount.setText(this.cpuPlayers[2].getVictoryPoint().toString());
    }

    private void changePlayerBuildingColor(Color color) {
        if (color.equals(Color.RED)) {
            if (this.gameManager.isAnySettlementBuildableByPlayer(this.player, circleMap)) {
                this.settlement.setFill(color);
            } else {
                this.settlement.setFill(Color.GRAY);
            }
            if (this.gameManager.isAnyCityBuildableByPlayer(this.player)) {
                this.city.setFill(color);
            } else {
                this.city.setFill(Color.GRAY);
            }
            if (this.gameManager.isAnyRoadBuildableByPlayer(this.player, circleMap, occupiedEdges)) {
                this.road.setStroke(color);
            } else {
                this.road.setStroke(Color.GRAY);
            }
        } else {
            this.settlement.setFill(color);
            this.city.setFill(color);
            this.road.setStroke(color);
        }
    }

    private void setTimeOut(Integer seconds, Runnable onTimerFinish) {
        if (timer != null) {
            timer.stop(); // Stop any existing timer
        }

        timer = new Timeline(new KeyFrame(
                Duration.seconds(seconds),
                ae -> onTimerFinish.run()
        ));

        timer.setCycleCount(1); // Only run once
        timer.play();
    }

    private void animateDiceButton() {
        ScaleTransition st1 = new ScaleTransition(Duration.millis(500), this.rollDiceButton);
        st1.setByX(0.30);
        st1.setByY(0.30);
        st1.setCycleCount((int) 4f);
        st1.setAutoReverse(true);

        st1.play();
    }

    @FXML
    private void onClickBuildRoad() {
        resetHighlighting();
        if (gameManager.isTurnStateValidForBuilding()) {
            PlayerAbstract player = getPlayerByTurnState(gameManager.turnPlayerState);
            highlightAvailableRoadLocations(player, circleMap);
        }
    }

    @FXML
    private void onClickBuildSettlement() {
        resetHighlighting();
        if (gameManager.isTurnStateValidForBuilding()) {
            PlayerAbstract player = getPlayerByTurnState(gameManager.turnPlayerState);
            highlightAvailableSettlementLocations(player, circleMap);
        }
    }

    @FXML
    private void onClickBuildCity() {
        resetHighlighting();
        if (gameManager.isTurnStateValidForBuilding()) {
            PlayerAbstract player = getPlayerByTurnState(gameManager.turnPlayerState);
            highlightAvailableCityLocations(player, circleMap);
        }
    }


    // Method to highlight circles where a road can be built
    private void highlightAvailableRoadLocations(PlayerAbstract player, HashMap<Circle, CircleVertex> circleMap) {
        if (!gameManager.isPlayerHasResourceForRoad(player)) {
            logTextArea.appendText("- Not Enough Resources To Build Road\n");
            return;
        }
        ArrayList<Circle> validCircles = new ArrayList<>();
        ArrayList<Pair<Circle, Circle>> validRoads = new ArrayList<>();

        ArrayList<Pair<CircleVertex, CircleVertex>> playerRoads = player.roads;

        for (Circle circle : circleMap.keySet()) {
            CircleVertex circleVertex = circleMap.get(circle);

            if (circleVertex.isHasCity()) {
                continue;
            }

            if (circleVertex.isHasSettlement()) {
                continue;
            }

            for (Circle adjacentCircle : circleVertex.getAdjacentCircles()) {
                Pair<Circle, Circle> edge = createEdge(circle, adjacentCircle);
                if (occupiedEdges.contains(edge)) {
                    continue;
                }

                CircleVertex adjacentCircleVertex = circleMap.get(adjacentCircle);
                if (adjacentCircleVertex.getOwner() != null && adjacentCircleVertex.getOwner().equals(player)) {
                    validCircles.add(circle);
                    validRoads.add(edge);
                    break;
                }

                if (isVertexAtRoadEnd(player, adjacentCircleVertex)) {
                    validCircles.add(circle);
                    validRoads.add(createEdge(circle, adjacentCircle));
                    break;
                }
            }
        }

        for (Circle circle : validCircles) {
            highlightCircle(circle, true);
            Pair<Circle, Circle> edge = validRoads.get(validCircles.indexOf(circle));
            circle.setOnMouseClicked(event -> onCircleClickedRoad(edge));
        }
    }

    private boolean isVertexAtRoadEnd(PlayerAbstract player, CircleVertex targetVertex) {
        ArrayList<Pair<CircleVertex, CircleVertex>> roads = player.roads;

        for (Pair<CircleVertex, CircleVertex> road : roads) {
            CircleVertex roadVertex1 = road.getKey();
            CircleVertex roadVertex2 = road.getValue();

            if (roadVertex1.equals(targetVertex)) {
                return true; // Found the targetVertex as an end of a road
            }

            if (roadVertex2.equals(targetVertex)) {
                return true; // Found the targetVertex as an end of a road
            }
        }
        return false; // targetVertex is not an end in any of the roads
    }

    private void buildRoad(Circle circleStart, Circle circleEnd) {
        Road road = new Road(circleStart, circleEnd, player.color.getColor(), boardGroup);
        player.roads.add(new Pair<>(circleMap.get(circleStart), circleMap.get(circleEnd)));
        Pair<Circle, Circle> edge = createEdge(circleStart, circleEnd);
        occupiedEdges.add(edge);
    }


    private void onCircleClickedRoad(Pair<Circle, Circle> roadEdge) {


        // Update the game state to reflect the new road
        buildRoad(roadEdge.getKey(), roadEdge.getValue());

        player.buildRoad(new Pair<>(circleMap.get(roadEdge.getKey()), circleMap.get(roadEdge.getValue())));

        updateLongestRoadPlayerIfEligible(player);

        // Reset the highlighting for buildable locations
        resetHighlighting();
        updatePlayerResourceCount();
        changePlayerBuildingColor(Color.RED);
    }

    private void updateLongestRoadPlayerIfEligible(PlayerAbstract newPlayer) {
        Integer consecutiveRoads = player.calculateLongestRoad();

        if (consecutiveRoads >= 5) {
            if (longestRoadPlayer != null) {
                Integer opponentConsecutiveRoads = longestRoadPlayer.calculateLongestRoad();
                if (consecutiveRoads > opponentConsecutiveRoads) {
                    longestRoadPlayer.setHasLongestRoad(false);

                    // Set the new player as the longest road player
                    longestRoadPlayer = player;
                    longestRoadPlayer.setHasLongestRoad(true);
                    this.updateVpCounts();
                    logTextArea.appendText("- Player " + longestRoadPlayer.color.toString() + " has the longest road.\n");
                }
            } else {
                // Set the new player as the longest road player
                longestRoadPlayer = player;
                longestRoadPlayer.setHasLongestRoad(true);
                this.updateVpCounts();
                logTextArea.appendText("- Player " + longestRoadPlayer.color.toString() + " has the longest road.\n");
            }
        }
    }

    // Method to highlight circles where a settlement can be built
    private void highlightAvailableSettlementLocations(PlayerAbstract player, HashMap<Circle, CircleVertex> circleMap) {
        // Check if the player has enough resources to build a settlement
        if (!gameManager.isPlayerHasResourceForSettlement(player)) {
            logTextArea.appendText("- Not Enough Resources To Build Settlement\n");
            return;
        }
        // If yes, iterate through each circleVertex that player reaches and highlight if buildable
        for (Pair<CircleVertex, CircleVertex> road : player.roads) {
            CircleVertex startVertex = road.getKey();
            CircleVertex endVertex = road.getValue();
            if (gameManager.isSettlementBuildableToVertex(startVertex, circleMap)) {
                Circle circle = gameManager.getCircleFromCircleVertex(startVertex, circleMap);
                highlightCircle(circle, true);
                circle.setOnMouseClicked(event -> onCircleClickedSettlement(circle, player));
            }
            if (gameManager.isSettlementBuildableToVertex(endVertex, circleMap)) {
                Circle circle = gameManager.getCircleFromCircleVertex(endVertex, circleMap);
                highlightCircle(circle, true);
                circle.setOnMouseClicked(event -> onCircleClickedSettlement(circle, player));
            }
        }
    }

    // Method to highlight circles where a city can be built
    private void highlightAvailableCityLocations(PlayerAbstract player, HashMap<Circle, CircleVertex> circleMap) {
        if (!gameManager.isPlayerHasResourceForCity(player)) {
            logTextArea.appendText("- Not Enough Resources To Build City\n");
            return;
        }

        for (Map.Entry<Circle, CircleVertex> entry : circleMap.entrySet()) {
            CircleVertex circleVertex = entry.getValue();
            if (gameManager.isCityBuildableToVertex(player, circleVertex)) {
                Circle circle = entry.getKey();
                highlightCircle(circle, true);

            }
        }
    }

    private void onCircleClickedSettlement(Circle circle, PlayerAbstract player) {
        // Check if the circle is still valid for building (in case of concurrent actions)
        if (gameManager.isSettlementBuildableToVertex(circleMap.get(circle), circleMap)) {
            // Update the game state to reflect the new settlement
            buildSettlement(player, circle);

            // Visual update to indicate the settlement is built
            circle.setFill(Color.RED);

            // Deduct resources from the player
            player.buildSettlement(circleMap.get(circle));

            // Reset the highlighting for buildable locations
            resetHighlighting();
            updatePlayerResourceCount();
            changePlayerBuildingColor(Color.RED);
        }
    }


    private void onCircleClickedCity(Circle circle, PlayerAbstract player) {
        // Check if the circle is still valid for building (in case of concurrent actions)
        if (gameManager.isSettlementBuildableToVertex(circleMap.get(circle), circleMap)) {
            // Update the game state to reflect the new settlement
            buildCity(player, circle);

            // Visual update to indicate the settlement is built
            circle.setFill(Color.RED);

            player.buildCity(circleMap.get(circle));

            // Reset the highlighting for buildable locations
            resetHighlighting();
            updatePlayerResourceCount();
            changePlayerBuildingColor(Color.RED);
        }
    }

    // Helper method to visually highlight a circle
    private void highlightCircle(Circle circle, boolean highlight) {
        if (highlight) {
            // Set some visual properties to highlight the circle
            circle.setRadius(20);
            circle.setStroke(Color.GREEN); // Example: Change the stroke to green to indicate it's selectable
            circle.setStrokeWidth(8);
        } else {
            // Reset the visual properties of the circle
            circle.setRadius(12);
            circle.setStroke(Color.BLACK); // Reset to default stroke color
            circle.setStrokeWidth(1);
        }
    }

    private void buildSettlement(PlayerAbstract player, Circle circle) {
        Settlement settlement = new Settlement(
                circle,
                player.color.getColor(),
                this.boardGroup
        );
        settlement.setMouseTransparent(true);
    }

    private void buildCity(PlayerAbstract player, Circle circle) {
        City city = new City(
                circle,
                player.color.getColor(),
                this.boardGroup
        );
    }

    private void resetHighlighting() {
        for (Map.Entry<Circle, CircleVertex> entry : circleMap.entrySet()) {
            highlightCircle(entry.getKey(), false);
            entry.getKey().setOnMouseClicked(null); // Remove the click event handler
        }
    }


    private PlayerAbstract getPlayerByTurnState(TurnPlayerState turnPlayerState) {
        switch (turnPlayerState) {
            case TURN_RED:
                return this.player;
            case TURN_BLUE:
                return this.cpuPlayers[0];
            case TURN_GREEN:
                return this.cpuPlayers[1];
            case TURN_ORANGE:
                return this.cpuPlayers[2];
            default:
                return null;
        }
    }
}

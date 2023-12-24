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
    // Hexagon
    // Player
    // Dice
    // Card

    Dice dice = new Dice();


    // Initialize Hexagons
    // Initialize Players

    // Roll Dice Action Listener
    // Buy Game Piece Action Listener

    private final GameManager gameManager = GameManager.getInstance();

    private Timeline timer;

    private Player player = new Player(PlayerColor.RED);
    private CPUPlayer[] cpuPlayers = new CPUPlayer[3];

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            this.initializeTiles();
            this.initializeCircles();
            this.initializeCpuPlayers();
            this.rollDiceButton.setDisable(true);
            this.gameManager.turnState = TurnState.INITIALIZATION;
            this.updateGameState();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void onClickHelpButton() {
        this.helpContentTable.setVisible(!this.helpContentTable.isVisible());
    }

    public void onClickRollDice() {
        if (timer != null) {
            timer.stop(); // Stop the timer if the button is clicked
        }

        this.manageDiceUpdate();
    }

    private void updateGameState() {
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
        this.logTextArea.appendText("- Player " + this.gameManager.turnPlayerState.toString() + " Turn\n");
        this.changePlayerBuildingColor(Color.GRAY);

        // Handle the turn based on the current player
        switch (this.gameManager.turnPlayerState) {
            case TURN_RED:
                this.changePlayerBuildingColor(Color.RED);
                this.setTimeOut(60, this::advanceToNextTurn);
                break;
            case TURN_BLUE:
                this.manageCpuTurn(0);
                advanceToNextTurn();
                break;
            case TURN_GREEN:
                this.manageCpuTurn(1);
                advanceToNextTurn();
                break;
            case TURN_ORANGE:
                this.manageCpuTurn(2);
                advanceToNextTurn();
                break;
        }
    }

    private void advanceToNextTurn() {
        this.gameManager.turnPlayerState = this.gameManager.turnPlayerState.next();
        this.gameManager.turnState = TurnState.ROLL_DICE;
        this.updateGameState();
    }


    private void manageCpuTurn(Integer cpuIndex) {
        // TODO: Use game manager inside cpu player
        boolean canBuildRoad = false;  // TODO
        boolean canBuildSettlement = this.gameManager.isAnySettlementBuildableByPlayer(this.cpuPlayers[cpuIndex], circleMap);
        boolean canBuildCity = this.gameManager.isAnyCityBuildableByPlayer(this.cpuPlayers[cpuIndex]);
        this.cpuPlayers[cpuIndex].play(circleMap, canBuildRoad, canBuildSettlement, canBuildCity);
    }


    private void initializeCpuPlayers() {
        System.out.println("Initializing CPU Players");
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

        System.out.println("Placing initial settlement and road for player");
        placeInitialSettlementAndRoad(player);
        updatePlayerResourceCount();

        System.out.println("Placing initial settlements and roads for CPU players");
        System.out.println("CPU Players size: " + cpuPlayers.length);
        for (CPUPlayer cpuPlayer : cpuPlayers) {
            System.out.println("CPU Player Color: " + (cpuPlayer.color != null ? cpuPlayer.color.getColor() : "null"));
            placeInitialSettlementAndRoad(cpuPlayer);
        }

        System.out.println("Exiting playerInitialPlacement");
        this.gameManager.turnState = TurnState.ROLL_DICE;
        this.updateGameState();
    }

    private void placeInitialSettlementAndRoad(PlayerAbstract player) {
        System.out.println("Entering placeInitialSettlementAndRoad for " + player.getClass().getSimpleName());

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
                System.out.println("Found valid CircleVertex for settlement at " + circle);
                break;
            }
        }

        if (settlementCircle == null) {
            System.out.println("No valid CircleVertex found for settlement");
            return; // No valid location found
        }

        // Place the settlement
        buildSettlement(player, settlementCircle);
        player.settlements.add(settlementCircleVertex);
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
                    System.out.println("Road placed between " + settlementCircle + " and " + roadEndCircle);
                    break; // Exit the loop once the road is successfully placed
                }
            }
        }

        // Update resources for initial settlement
        updateInitialResources(player, settlementCircle);
        System.out.println("Exiting placeInitialSettlementAndRoad for " + player.getClass().getSimpleName());
    }

    private void updateInitialResources(PlayerAbstract player, Circle settlementCircle) {
        System.out.println("Updating initial resources for " + player.getClass().getSimpleName() + " with settlement circle: " + settlementCircle);

        CircleVertex vertex = circleMap.get(settlementCircle);
        if (vertex == null) {
            System.out.println("CircleVertex is null for circle: " + settlementCircle);
            return;
        }

        for (Polygon polygon : vertex.adjacentTiles) {
            System.out.println("Processing adjacent tile: " + polygon);
            Tile adjacentTile = polygonTileHashMap.get(polygon);
            if (adjacentTile == null) {
                System.out.println("Adjacent tile is null for polygon: " + polygon);
                continue;
            }

            ResourceType resourceType = GeneralConstants.tileTypeToResourceType.get(adjacentTile.getTileType());
            if (resourceType == null) {
                System.out.println("Resource type is null for tile type: " + adjacentTile.getTileType());
                continue;
            }

            System.out.println("Updating resource " + resourceType + " for player: " + player.getClass().getSimpleName());
            player.updateResource(resourceType, 1);
        }

        System.out.println("Initial resources updated for player: " + player.getClass().getSimpleName());
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
        for (CPUPlayer cpuPlayer : this.cpuPlayers) {
            switch (cpuPlayer.color) {
                case RED:
                    this.updatePlayerResourceCount();
                    break;
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

    private void changePlayerBuildingColor(Color color) {
        this.settlement.setFill(color);
        this.city.setFill(color);
        this.road.setStroke(color);
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
        ScaleTransition st1 = new ScaleTransition(Duration.millis(1000), this.rollDiceButton);
        st1.setByX(1.05);
        st1.setByY(1.05);
        st1.setCycleCount((int) 4f);
        st1.setAutoReverse(true);

        st1.play();
    }

    @FXML
    private void onClickBuildRoad() {

        System.out.println("onClickBuildRoad");

//        Road road = new Road(
//                this.circle1,
//                this.circle14,
//                Color.RED,
//                this.boardGroup
//        );

        if (gameManager.isTurnStateValidForBuilding()) {
            PlayerAbstract player = getPlayerByTurnState(gameManager.turnPlayerState);
            highlightAvailableRoadLocations(player, circleMap);
        }
    }

    @FXML
    private void onClickBuildSettlement() {
        if (gameManager.isTurnStateValidForBuilding()) {
            PlayerAbstract player = getPlayerByTurnState(gameManager.turnPlayerState);
            highlightAvailableSettlementLocations(player, circleMap);
        }
    }

    @FXML
    private void onClickBuildCity() {
        if (gameManager.isTurnStateValidForBuilding()) {
            PlayerAbstract player = getPlayerByTurnState(gameManager.turnPlayerState);
            highlightAvailableCityLocations(player, circleMap);
        }
    }


    // Method to highlight circles where a road can be built
    private void highlightAvailableRoadLocations(PlayerAbstract player, HashMap<Circle, CircleVertex> circleMap) {
        if (!gameManager.isRoadBuildableByPlayer(player)) {
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

        // Reset the highlighting for buildable locations
        resetHighlighting();
        updatePlayerResourceCount();
    }

    // Method to highlight circles where a settlement can be built
    private void highlightAvailableSettlementLocations(PlayerAbstract player, HashMap<Circle, CircleVertex> circleMap) {
        // Check if the player has enough resources to build a settlement
        if (!gameManager.isAnySettlementBuildableByPlayer(player, circleMap)) {
            logTextArea.appendText("- Not Enough Resources To Build Settlement\n");
            return;
        }
        // If yes, iterate through each circleVertex and highlight if buildable
        for (Map.Entry<Circle, CircleVertex> entry : circleMap.entrySet()) {
            CircleVertex circleVertex = entry.getValue();
            if (gameManager.isSettlementBuildableToVertex(circleMap, circleVertex)) {
                Circle circle = entry.getKey();
                highlightCircle(circle, true);
                circle.setOnMouseClicked(event -> onCircleClickedSettlement(circle, player));
            }
        }
    }

    // Method to highlight circles where a city can be built
    private void highlightAvailableCityLocations(PlayerAbstract player, HashMap<Circle, CircleVertex> circleMap) {
        if (!gameManager.isAnyCityBuildableByPlayer(player)) {
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
        if (gameManager.isSettlementBuildableToVertex(circleMap, circleMap.get(circle))) {
            // Update the game state to reflect the new settlement
            buildSettlement(player, circle);

            // Visual update to indicate the settlement is built
            circle.setFill(Color.RED); // Example: Change the color to red

            // Deduct resources from the player
            player.buildSettlement(circleMap.get(circle));

            // Reset the highlighting for buildable locations
            resetHighlighting();
        }
    }


    private void onCircleClickedCity(Circle circle, PlayerAbstract player) {
        // Check if the circle is still valid for building (in case of concurrent actions)
        if (gameManager.isSettlementBuildableToVertex(circleMap, circleMap.get(circle))) {
            // Update the game state to reflect the new settlement
            buildCity(player, circle);

            // Visual update to indicate the settlement is built
            circle.setFill(Color.RED); // Example: Change the color to red

            // Reset the highlighting for buildable locations
            resetHighlighting();
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
    }

    private void buildCity(PlayerAbstract player, Circle circle) {
        // Logic to add a city to the player's properties
    }

    private void deductResourcesForCity(PlayerAbstract player) {
        // Logic to deduct resources from the player
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

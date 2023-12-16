package com.example.ceng453_20231_group11_frontend.controller;

import com.example.ceng453_20231_group11_frontend.constants.GeneralConstants;
import com.example.ceng453_20231_group11_frontend.enums.TurnState;
import com.example.ceng453_20231_group11_frontend.models.*;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

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

    private Timeline diceRollTimer;

    private Player player = new Player();
    private CPUPlayer[] cpuPlayers = new CPUPlayer[3];

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            this.initializeTiles();
            this.initializeCpuPlayers();
            this.rollDiceButton.setDisable(true);
            this.gameManager.turnState = TurnState.ROLL_DICE;
            this.updateGameState();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void onClickHelpButton() {
        this.helpContentTable.setVisible(!this.helpContentTable.isVisible());
    }

    public void onClickRollDice() {
        if (diceRollTimer != null) {
            diceRollTimer.stop(); // Stop the timer if the button is clicked
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

        // TODO: Implement Player Turn
        // TODO: RED Player is the user / Other players are CPU
        switch (this.gameManager.turnPlayerState) {
            case TURN_BLUE:
                break;
            case TURN_RED:
                break;
            case TURN_ORANGE:
                break;
            case TURN_GREEN:
                break;
        }

        this.gameManager.turnPlayerState = this.gameManager.turnPlayerState.next();
        this.gameManager.turnState = TurnState.ROLL_DICE;
        this.updateGameState();
    }


    private void initializeCpuPlayers() {
        this.cpuPlayers[0] = new CPUPlayer();
        this.cpuPlayers[1] = new CPUPlayer();
        this.cpuPlayers[2] = new CPUPlayer();
    }

    private void distributeResources() {
        this.logTextArea.appendText("- Distribute Resources\n");

        this.distributeResourcesPlayer();
        this.distributeResourcesCPU();

        this.gameManager.turnState = TurnState.TURN_PLAYER;
        this.updateGameState();
    }

    private void manageDiceRoll() {
        if (this.gameManager.turnPlayerState == this.gameManager.turnPlayerState.TURN_RED) {
            this.rollDiceButton.setDisable(false);
            this.logTextArea.appendText("- Please Roll Dice: 10 Seconds\n");
            this.animateDiceButton();

            // Start or restart the timer
            startDiceRollTimer(10);

        } else {
            this.logTextArea.appendText("- Please Wait For Your Turn\n");
            this.rollDiceButton.setDisable(true);

            startDiceRollTimer(2);


            // TODO: Implement CPU Dice Roll
        }
    }


    private void manageDiceUpdate() {
        dice.roll();
        this.updateDiceText();
        this.logTextArea.appendText("- Player " + this.gameManager.turnPlayerState.toString() + " rolled the dice\n");
        this.gameManager.turnState = TurnState.RESOURCE_DISTRIBUTION;
        this.updateGameState();
    }

    private void playerInitialPlacement() {
        this.logTextArea.appendText("- Player Initial Placement\n");
        // TODO: Implement Player Initial Placement
        this.gameManager.turnState = TurnState.ROLL_DICE;
    }

    private void distributeResourcesPlayer() {
        for (CircleVertex playerSettlement : this.player.settlements) {
            for (Polygon polygon : playerSettlement.adjacentTiles) {
                Tile adjacentTile = this.polygonTileHashMap.get(polygon);

                if (Objects.equals(adjacentTile.getNumberToken(), this.dice.getDiceTotal())) {
                    this.player.addResource(GeneralConstants.tileTypeToResourceType.get(adjacentTile.getTileType()), 1);
                }
            }
        }

        for (CircleVertex playerCity : this.player.cities) {
            for (Polygon polygon : playerCity.adjacentTiles) {
                Tile adjacentTile = this.polygonTileHashMap.get(polygon);

                if (Objects.equals(adjacentTile.getNumberToken(), this.dice.getDiceTotal())) {
                    this.player.addResource(GeneralConstants.tileTypeToResourceType.get(adjacentTile.getTileType()), 1);
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
                        this.player.addResource(GeneralConstants.tileTypeToResourceType.get(adjacentTile.getTileType()), 1);
                    }
                }
            }

            for (CircleVertex cpuCity : cpuPlayer.cities) {
                for (Polygon polygon : cpuCity.adjacentTiles) {
                    Tile adjacentTile = this.polygonTileHashMap.get(polygon);

                    if (Objects.equals(adjacentTile.getNumberToken(), this.dice.getDiceTotal())) {
                        this.player.addResource(GeneralConstants.tileTypeToResourceType.get(adjacentTile.getTileType()), 1);
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

    private void startDiceRollTimer(Integer seconds) {
        if (diceRollTimer != null) {
            diceRollTimer.stop(); // Stop any existing timer
        }

        diceRollTimer = new Timeline(new KeyFrame(
                Duration.seconds(seconds),
                ae -> onClickRollDice()
        ));

        diceRollTimer.setCycleCount(1); // Only run once
        diceRollTimer.play();
    }

    private void animateDiceButton() {
        ScaleTransition st1 = new ScaleTransition(Duration.millis(1000), this.rollDiceButton);
        st1.setByX(1.05);
        st1.setByY(1.05);
        st1.setCycleCount((int) 4f);
        st1.setAutoReverse(true);

        st1.play();
    }
}

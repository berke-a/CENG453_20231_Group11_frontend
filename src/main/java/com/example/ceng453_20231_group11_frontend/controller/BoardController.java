package com.example.ceng453_20231_group11_frontend.controller;

import com.example.ceng453_20231_group11_frontend.enums.TurnState;
import com.example.ceng453_20231_group11_frontend.models.Dice;
import com.example.ceng453_20231_group11_frontend.models.GameManager;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;

import java.net.URL;
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

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            this.initializeTiles();
            this.rollDiceButton.setDisable(true);
            this.updateGameState();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateGameState() {
        this.updateDiceText();
        switch (gameManager.turnState) {
            case INITIALIZATION:
                this.playerInitialPlacement();
                break;
            case ROLL_DICE:
                this.manageDiceRoll();
                break;
            case TURN_PLAYER:
                this.managePlayerTurn();
                break;
        }
        gameManager.turnState = gameManager.turnState.next();
    }

    public void animateDiceButton() {
        ScaleTransition st1 = new ScaleTransition(Duration.millis(1000), this.rollDiceButton);
        st1.setByX(1.05);
        st1.setByY(1.05);
        st1.setCycleCount((int) 4f);
        st1.setAutoReverse(true);

        st1.play();
    }

    public void managePlayerTurn() {
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

        this.gameManager.turnPlayerState.next();
        this.gameManager.turnState = TurnState.ROLL_DICE;
    }

    public void manageDiceRoll() {
        if (this.gameManager.turnPlayerState == this.gameManager.turnPlayerState.TURN_RED) {
            this.rollDiceButton.setDisable(false);
            this.logTextArea.appendText("- Please Roll Dice\n");
            this.animateDiceButton();
        } else {
            this.logTextArea.appendText("- Please Wait For Your Turn\n");
            this.rollDiceButton.setDisable(true);
            // TODO: Implement CPU Roll Dice and plays
        }
    }

    public void onClickRollDice() {
        dice.roll();
        this.gameManager.turnState = TurnState.TURN_PLAYER;
        updateGameState();
    }

    public void playerInitialPlacement() {
        this.logTextArea.appendText("- Player Initial Placement\n");
        // TODO: Implement Player Initial Placement
        this.gameManager.turnState = TurnState.ROLL_DICE;
    }

    public void onClickHelpButton() {
        this.helpContentTable.setVisible(!this.helpContentTable.isVisible());
    }

    private void updateDiceText() {
        this.diceText1.setText(dice.getDie1());
        this.diceText2.setText(dice.getDie2());
        this.diceTotalText.setText(dice.getDiceTotal());
    }
}

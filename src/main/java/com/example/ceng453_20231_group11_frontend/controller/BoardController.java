package com.example.ceng453_20231_group11_frontend.controller;

import com.example.ceng453_20231_group11_frontend.models.Dice;
import com.example.ceng453_20231_group11_frontend.models.GameManager;

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

    private GameManager gameManager = GameManager.getInstance();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            this.initializeTiles();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void onClickRollDice() {
        dice.roll();
        this.diceText1.setText(dice.getDie1());
        this.diceText2.setText(dice.getDie2());
        this.diceTotalText.setText(dice.getDiceTotal());
    }

    public void onClickHelpButton() {
    }
}

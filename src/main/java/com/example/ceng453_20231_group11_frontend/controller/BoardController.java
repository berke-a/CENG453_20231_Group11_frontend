package com.example.ceng453_20231_group11_frontend.controller;

import com.example.ceng453_20231_group11_frontend.constants.GeneralConstants;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.net.URL;
import java.util.*;

public class BoardController extends BoardControllerAbstract {
    // Hexagon
    // Player
    // Dice
    // Card


    // Initialize Hexagons
    // Initialize Players

    // Roll Dice Action Listener
    // Buy Game Piece Action Listener

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List keys = new ArrayList(this.tileMap.keySet());
        Collections.shuffle(keys);
        for (Object o : keys) {
            this.tileMap.get(o);
        }

        try {
            Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(GeneralConstants.TILE_DESERT)));
            System.out.println(img);
            System.out.println(new ImagePattern(img));
            this.tile1.setFill(new ImagePattern(img));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

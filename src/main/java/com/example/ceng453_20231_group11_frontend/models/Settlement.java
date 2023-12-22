package com.example.ceng453_20231_group11_frontend.models;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Settlement extends Rectangle {
    private final Integer victoryPoint = 1;
    public Settlement(Circle circle, Color color, Group board) {
        super(circle.getLayoutX() - 10, circle.getLayoutY() - 10, 20, 20);
        this.setFill(color);
        board.getChildren().add(this);
    }
}

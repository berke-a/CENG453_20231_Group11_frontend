package com.example.ceng453_20231_group11_frontend.models;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Settlement extends Rectangle {
    private final Integer victoryPoint = 1;

    public Settlement(Circle circle, Color color, Group board) {
        super(circle.getLayoutX() - 12.5, circle.getLayoutY() - 12.5, 25, 25);
        this.setFill(color);
        this.setStroke(Color.BLACK);
        this.setStrokeWidth(2.0);
        board.getChildren().add(this);
    }
}

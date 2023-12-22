package com.example.ceng453_20231_group11_frontend.models;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Road extends Line {
    // Constructor for the Road class
    public Road(Circle circle1, Circle circle2, Color color, Group board) {
        super(circle1.getLayoutX(), circle1.getLayoutY(), circle2.getLayoutX(), circle2.getLayoutY());
        // Get the coordinates of the start and end circles

        // Set the visual properties of the line
        this.setStroke(color); // Color of the road
        this.setStrokeWidth(10); // Width of the road

        board.getChildren().add(this); // Add the road to the board
    }
}

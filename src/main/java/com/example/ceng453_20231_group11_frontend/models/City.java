package com.example.ceng453_20231_group11_frontend.models;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class City extends Polygon {
    private final Integer victoryPoint = 2;

    public City(Circle circle, Color color, Group board) {
        // Calculate the vertices for the triangle
        double x = circle.getLayoutX();
        double y = circle.getLayoutY();
        double size = 20; // Size of the triangle

        // Define the vertices of the triangle
        getPoints().addAll(new Double[]{
                x, y - size / 2, // Top vertex
                x - size / 2, y + size / 2, // Bottom left vertex
                x + size / 2, y + size / 2 // Bottom right vertex
        });

        this.setFill(color);

        // Add the polygon (this city) to the board
        board.getChildren().add(this);
    }
}


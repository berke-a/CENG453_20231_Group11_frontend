package com.example.ceng453_20231_group11_frontend.models;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class City extends Group {
    private final Integer victoryPoint = 2;
    public City() {
        // Create a polygon to represent the city
        Polygon triangle = new Polygon();

        // Add points to form a triangle shape
        triangle.getPoints().addAll(new Double[]{
                0.0, 0.0,   // Point at the top
                20.0, 20.0, // Bottom right
                -20.0, 20.0 // Bottom left
        });

        // Set the fill color for the triangle
        triangle.setFill(Color.RED);

        // Add the triangle to the group
        this.getChildren().add(triangle);
    }
}

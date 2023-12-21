package com.example.ceng453_20231_group11_frontend.models;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Settlement extends Group {
    private final Integer victoryPoint = 1;
    public Settlement() {
        // Create a rectangle to represent the settlement
        Rectangle rectangle = new Rectangle();

        // Set the width and height of the rectangle
        rectangle.setWidth(20.0);
        rectangle.setHeight(20.0);

        // Set the fill color for the rectangle
        rectangle.setFill(Color.BLACK);

        // Add the rectangle to the group
        this.getChildren().add(rectangle);
    }
}

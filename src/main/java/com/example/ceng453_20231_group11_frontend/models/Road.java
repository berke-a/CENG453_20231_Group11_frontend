package com.example.ceng453_20231_group11_frontend.models;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Road extends Group {
    // Constructor for the Road class
    public Road(double startX, double startY, double endX, double endY) {
        // Create a new Line object to represent the road
        Line line = new Line(startX, startY, endX, endY);

        // Set the visual properties of the line
        line.setStroke(Color.GRAY); // Color of the road
        line.setStrokeWidth(3); // Width of the road

        // Add the line to the group
        this.getChildren().add(line);
    }
}

package com.example.ceng453_20231_group11_frontend.models;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Road extends Group {
    public Road() {
        Rectangle rectangle = new Rectangle(50, 10);
        rectangle.setFill(Color.GRAY);
        this.getChildren().add(rectangle);
    }
}

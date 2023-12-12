package com.example.ceng453_20231_group11_frontend.models;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class City extends Group {
    public City() {
        Rectangle rectangle = new Rectangle(20, 20);
        rectangle.setFill(Color.RED);
        this.getChildren().add(rectangle);
    }
}

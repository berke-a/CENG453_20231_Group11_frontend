package com.example.ceng453_20231_group11_frontend.models;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Settlement extends Group {
    public Settlement() {
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(new Double[]{
                0.0, 0.0,
                10.0, 10.0,
                0.0, 20.0,
                -10.0, 10.0
        });
        polygon.setFill(Color.BLACK);
        this.getChildren().add(polygon);
    }
}

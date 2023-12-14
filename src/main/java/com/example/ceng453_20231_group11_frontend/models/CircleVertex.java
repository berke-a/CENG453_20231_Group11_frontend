package com.example.ceng453_20231_group11_frontend.models;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CircleVertex {
    private boolean hasSettlement;
    private boolean hasCity;
    private List<Circle> adjacentCircles;
    private List<Polygon> adjacentTiles;

    public CircleVertex() {
        this.hasSettlement = false;
        this.hasCity = false;
        this.adjacentCircles = new ArrayList<>();
    }

    public CircleVertex(List<Circle> adjacentCircles, List<Polygon> adjacentTiles) {
        this.hasSettlement = false;
        this.hasCity = false;
        this.adjacentCircles = adjacentCircles;
        this.adjacentTiles = adjacentTiles;
    }
}

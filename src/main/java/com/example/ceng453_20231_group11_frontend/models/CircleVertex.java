package com.example.ceng453_20231_group11_frontend.models;

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
    private List<CircleVertex> adjacentCircles;

    public CircleVertex() {
        this.hasSettlement = false;
        this.hasCity = false;
        this.adjacentCircles = new ArrayList<>();
    }

    public CircleVertex(List<CircleVertex> adjacentCircles) {
        this.hasSettlement = false;
        this.hasCity = false;
        this.adjacentCircles = adjacentCircles;
    }
}

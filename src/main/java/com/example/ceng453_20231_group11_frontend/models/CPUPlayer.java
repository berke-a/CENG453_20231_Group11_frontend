package com.example.ceng453_20231_group11_frontend.models;

import javafx.scene.Group;
import javafx.scene.shape.Circle;
import com.example.ceng453_20231_group11_frontend.enums.PlayerColor;
import javafx.util.Pair;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Set;

@Getter
@Setter
public class CPUPlayer extends PlayerAbstract {
    public CPUPlayer(PlayerColor color) {
        super(color);
    }

    public void play(GameManager gameManager, HashMap<Circle, CircleVertex> circleMap, Group boardGroup, Set<Pair<Circle, Circle>> occupiedEdges, HashMap<Circle, Settlement> settlementsMap) {
        if (gameManager.isAnyRoadBuildableByPlayer(this, circleMap, occupiedEdges)) {
            Pair<Circle, Circle> roadEdge = gameManager.getCirclePairToBuildRoad(this, circleMap, occupiedEdges);
            this.buildRoad(roadEdge, circleMap, boardGroup, occupiedEdges);
        }
        if (gameManager.isAnySettlementBuildableByPlayer(this, circleMap)) {
            Circle circleToBuildSettlement = gameManager.getCircleToBuildSettlement(this, circleMap);
            this.buildSettlement(circleToBuildSettlement, circleMap, boardGroup, settlementsMap);
        } else if (gameManager.isAnyCityBuildableByPlayer(this)) {
            Circle circleToBuildCity = gameManager.getCircleToBuildCity(this, circleMap);
            this.buildCity(circleToBuildCity, circleMap, boardGroup, settlementsMap);
        }
    }
}

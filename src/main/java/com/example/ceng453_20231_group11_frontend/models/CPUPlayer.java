package com.example.ceng453_20231_group11_frontend.models;

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

    // TODO in the board call play
    // TODO then check and reassign the longest road boolean
    // TODO then call hasWonTheGame

    public void play(GameManager gameManager, HashMap<Circle, CircleVertex> circleMap, Set<Pair<Circle, Circle>> occupiedEdges) {
        if (gameManager.isAnyRoadBuildableByPlayer(this, circleMap, occupiedEdges)) {
            Pair<CircleVertex, CircleVertex> roadCircleVertexPair = gameManager.getCircleVertexPairToBuildRoad(this, circleMap, occupiedEdges);
            this.buildRoad(roadCircleVertexPair);
        }
        if (gameManager.isAnySettlementBuildableByPlayer(this, circleMap)) {
            CircleVertex vertexToBuildSettlement = gameManager.getVertexToBuildSettlement(this, circleMap);
            this.buildSettlement(vertexToBuildSettlement);
        } else if (gameManager.isAnyCityBuildableByPlayer(this)) {
            CircleVertex vertexToBuildCity = gameManager.getVertexToBuildCity(this);
            this.buildCity(vertexToBuildCity);
        }
    }
}

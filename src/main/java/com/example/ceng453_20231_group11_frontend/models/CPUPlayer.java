package com.example.ceng453_20231_group11_frontend.models;

import javafx.scene.shape.Circle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
public class CPUPlayer extends PlayerAbstract {

    // TODO in the board call play
    // TODO then check and reassign the longest road boolean
    // TODO then call hasWonTheGame

    public void play(HashMap<Circle, CircleVertex> circleMap, boolean canBuildRoad, boolean canBuildSettlement, boolean canBuildCity) {
        if (canBuildRoad) {
            Integer edgeId = this.getEdgeForRoad(circleMap);
            this.buildRoad(edgeId);
        }
        if (canBuildSettlement) {
            CircleVertex vertexToBuildSettlement = this.getVertexForSettlement(circleMap);
            this.buildSettlement(vertexToBuildSettlement);
        } else if (canBuildCity) {
            CircleVertex vertexToBuildCity = this.getVertexForCity();
            this.buildCity(vertexToBuildCity);
        }
    }

    private Integer getEdgeForRoad(HashMap<Circle, CircleVertex> circleMap) {
        return 0; // TODO RETURN a random suitable edgeId for road
    }

    private CircleVertex getVertexForSettlement(HashMap<Circle, CircleVertex> circleMap) {
        for (Map.Entry<Circle, CircleVertex> entry: circleMap.entrySet()) {
            CircleVertex circleVertex = entry.getValue();
            if (circleVertex.isHasSettlement() || circleVertex.isHasCity()) {
                continue;
            }
            boolean isAdjacentCircleHasBuilding = false;
            for (Circle circle: circleVertex.getAdjacentCircles()) {
                CircleVertex adjacentCircleVertex = circleMap.get(circle);
                if (adjacentCircleVertex.isHasSettlement() || adjacentCircleVertex.isHasCity()) {
                    isAdjacentCircleHasBuilding = true;
                }
            }
            if (!isAdjacentCircleHasBuilding) {
                return circleVertex;
            }
        }
        return null; // should not happen since checked before
    }

    private CircleVertex getVertexForCity() {
        Random random = new Random();
        int settlementIndex = random.nextInt(this.settlements.size());
        return this.settlements.get(settlementIndex);
    }
}

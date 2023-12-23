package com.example.ceng453_20231_group11_frontend.models;

import com.example.ceng453_20231_group11_frontend.enums.ResourceType;
import com.example.ceng453_20231_group11_frontend.enums.TurnPlayerState;
import com.example.ceng453_20231_group11_frontend.enums.TurnState;
import javafx.scene.shape.Circle;
import javafx.util.Pair;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

@Getter
@Setter
public class GameManager {

    private static GameManager instance;
    public TurnState turnState = TurnState.INITIALIZATION;
    public TurnPlayerState turnPlayerState = TurnPlayerState.TURN_BLUE;


    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    // TODO: Create Player Class & Pass as a parameter



    public boolean isAnyRoadBuildableByPlayer(PlayerAbstract player, HashMap<Circle, CircleVertex> circleMap, ArrayList<Pair<CircleVertex, CircleVertex>> allRoads) {
        boolean buildable = false;
        if (player.resources.get(ResourceType.LUMBER) >= 1 && player.resources.get(ResourceType.BRICK) >= 1) {
            for (Pair<CircleVertex, CircleVertex> road: player.roads) {
                CircleVertex startVertex = road.getKey();
                CircleVertex endVertex = road.getValue();
                if (isVertexHasEmptyEdge(startVertex, circleMap, allRoads) || isVertexHasEmptyEdge(endVertex, circleMap, allRoads)) {
                    buildable = true;
                }
            }
        }
        return buildable;
    }

    public boolean isVertexHasEmptyEdge(CircleVertex circleVertex, HashMap<Circle, CircleVertex> circleMap, ArrayList<Pair<CircleVertex, CircleVertex>> allRoads) {
        boolean buildable = false;
        for (Circle adjacentCircle : circleVertex.getAdjacentCircles()) {
            CircleVertex adjacentCircleVertex = circleMap.get(adjacentCircle);
            Pair<CircleVertex, CircleVertex> pair1 = new Pair<>(circleVertex, adjacentCircleVertex);
            Pair<CircleVertex, CircleVertex> pair2 = new Pair<>(adjacentCircleVertex, circleVertex);
            if (!allRoads.contains(pair1) && !allRoads.contains(pair2)) {
                buildable = true;
            }
        }
        return buildable;
    }

    public Pair<CircleVertex, CircleVertex> getVertexToBuildRoad(PlayerAbstract player, HashMap<Circle, CircleVertex> circleMap, ArrayList<Pair<CircleVertex, CircleVertex>> allRoads) {
        for (Pair<CircleVertex, CircleVertex> road : player.roads) {
            CircleVertex startVertex = road.getKey();
            CircleVertex endVertex = road.getValue();
            if (isVertexHasEmptyEdge(startVertex, circleMap, allRoads)) {
                for (Circle adjacentCircle : startVertex.getAdjacentCircles()) {
                    CircleVertex adjacentCircleVertex = circleMap.get(adjacentCircle);
                    Pair<CircleVertex, CircleVertex> pair1 = new Pair<>(startVertex, adjacentCircleVertex);
                    Pair<CircleVertex, CircleVertex> pair2 = new Pair<>(adjacentCircleVertex, startVertex);
                    if (!allRoads.contains(pair1) && !allRoads.contains(pair2)) {
                        return pair1;
                    }
                }
            }
            if (isVertexHasEmptyEdge(endVertex, circleMap, allRoads)) {
                for (Circle adjacentCircle : endVertex.getAdjacentCircles()) {
                    CircleVertex adjacentCircleVertex = circleMap.get(adjacentCircle);
                    Pair<CircleVertex, CircleVertex> pair1 = new Pair<>(endVertex, adjacentCircleVertex);
                    Pair<CircleVertex, CircleVertex> pair2 = new Pair<>(adjacentCircleVertex, endVertex);
                    if (!allRoads.contains(pair1) && !allRoads.contains(pair2)) {
                        return pair1;
                    }
                }
            }
        }
        return null; // should not happen since checked before
    }




    public boolean isAnySettlementBuildableByPlayer(PlayerAbstract player, HashMap<Circle, CircleVertex> circleMap) {
        boolean buildable = false;
        if (player.resources.get(ResourceType.LUMBER) >= 1 && player.resources.get(ResourceType.BRICK) >= 1 &&
                player.resources.get(ResourceType.GRAIN) >= 1 && player.resources.get(ResourceType.WOOL) >= 1) {
            for (Pair<CircleVertex, CircleVertex> road: player.roads) {
                CircleVertex startVertex = road.getKey();
                CircleVertex endVertex = road.getValue();
                if (isSettlementBuildableToVertex(startVertex, circleMap) || isSettlementBuildableToVertex(endVertex, circleMap)) {
                    buildable = true;
                }
            }
        }
        return buildable;
    }

    public boolean isSettlementBuildableToVertex(CircleVertex circleVertex, HashMap<Circle, CircleVertex> circleMap) {
        if (!circleVertex.isHasSettlement() && !circleVertex.isHasCity()) {
            for (Circle circle : circleVertex.getAdjacentCircles()) {
                CircleVertex adjacentCircleVertex = circleMap.get(circle);
                if (adjacentCircleVertex.isHasSettlement() || adjacentCircleVertex.isHasCity()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public CircleVertex getVertexToBuildSettlement(PlayerAbstract player, HashMap<Circle, CircleVertex> circleMap) {
        for (Pair<CircleVertex, CircleVertex> road: player.roads) {
            CircleVertex startVertex = road.getKey();
            CircleVertex endVertex = road.getValue();
            if (isSettlementBuildableToVertex(startVertex, circleMap)) {
                return startVertex;
            }
            if (isSettlementBuildableToVertex(endVertex, circleMap)) {
                return endVertex;
            }
        }
        return null; // should not happen since checked before
    }




    public boolean isAnyCityBuildableByPlayer(PlayerAbstract player) {
        return (player.resources.get(ResourceType.ORE) >= 3 && player.resources.get(ResourceType.GRAIN) >= 2 && !player.settlements.isEmpty());
    }

    public boolean isCityBuildableToVertex(PlayerAbstract player, CircleVertex circleVertex) {
        // Check if the player has enough resources to build a city
        if (player.resources.get(ResourceType.ORE) >= 3 && player.resources.get(ResourceType.GRAIN) >= 2) {
            // Check if the circleVertex has a settlement that belongs to the player
            return circleVertex.isHasSettlement() && circleVertex.getOwner() == player;
        }
        return false;
    }

    public CircleVertex getVertexToBuildCity(PlayerAbstract player) {
        Random random = new Random();
        int settlementIndex = random.nextInt(player.settlements.size());
        return player.settlements.get(settlementIndex);
    }




    public boolean isTurnStateValidForBuilding() {
        return (this.turnState == TurnState.TURN_PLAYER && this.turnPlayerState == TurnPlayerState.TURN_RED);
    }

    public boolean isTurnStateValidForRolling() {
        return this.turnState == TurnState.ROLL_DICE;
    }

    public TurnPlayerState getCurrentTurnPlayerState() {
        return this.turnPlayerState;
    }
}

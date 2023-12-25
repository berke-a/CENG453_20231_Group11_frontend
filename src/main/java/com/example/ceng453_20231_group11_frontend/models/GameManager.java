package com.example.ceng453_20231_group11_frontend.models;

import com.example.ceng453_20231_group11_frontend.enums.ResourceType;
import com.example.ceng453_20231_group11_frontend.enums.TurnPlayerState;
import com.example.ceng453_20231_group11_frontend.enums.TurnState;
import javafx.scene.shape.Circle;
import javafx.util.Pair;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

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
    public boolean isPlayerHasResourceForRoad(PlayerAbstract player) {
        return (player.resources.get(ResourceType.LUMBER) >= 1 && player.resources.get(ResourceType.BRICK) >= 1);
    }

    public boolean isAnyRoadBuildableByPlayer(PlayerAbstract player, HashMap<Circle, CircleVertex> circleMap, Set<Pair<Circle, Circle>> occupiedEdges) {
        boolean buildable = false;
        if (player.resources.get(ResourceType.LUMBER) >= 1 && player.resources.get(ResourceType.BRICK) >= 1) {
            for (Pair<CircleVertex, CircleVertex> road: player.roads) {
                CircleVertex startVertex = road.getKey();
                CircleVertex endVertex = road.getValue();
                if (isVertexHasEmptyEdge(startVertex, circleMap, occupiedEdges) || isVertexHasEmptyEdge(endVertex, circleMap, occupiedEdges)) {
                    buildable = true;
                }
            }
        }
        return buildable;
    }

    public boolean isVertexHasEmptyEdge(CircleVertex circleVertex, HashMap<Circle, CircleVertex> circleMap, Set<Pair<Circle, Circle>> occupiedEdges) {
        boolean buildable = false;
        Circle circleOfVertex = getCircleFromCircleVertex(circleVertex, circleMap);
        for (Circle adjacentCircle : circleVertex.getAdjacentCircles()) {
            Pair<Circle, Circle> circlePair = new Pair<>(circleOfVertex, adjacentCircle);
            Pair<Circle, Circle> circlePairReverse = new Pair<>(adjacentCircle, circleOfVertex);
            if (!(occupiedEdges.contains(circlePair) || occupiedEdges.contains(circlePairReverse))) {
                buildable = true;
            }
        }
        return buildable;
    }

    public Circle getCircleFromCircleVertex(CircleVertex circleVertex, HashMap<Circle, CircleVertex> circleMap) {
        for (Map.Entry<Circle, CircleVertex> entry : circleMap.entrySet()) {
            if (circleVertex.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null; // should not happen
    }

    public Pair<CircleVertex, CircleVertex> getCircleVertexPairToBuildRoad(PlayerAbstract player, HashMap<Circle, CircleVertex> circleMap, Set<Pair<Circle, Circle>> occupiedEdges) {
        for (Pair<CircleVertex, CircleVertex> road : player.roads) {
            CircleVertex startVertex = road.getKey();
            CircleVertex endVertex = road.getValue();
            Circle startCircle = getCircleFromCircleVertex(startVertex, circleMap);
            Circle endCircle = getCircleFromCircleVertex(endVertex, circleMap);
            if (isVertexHasEmptyEdge(startVertex, circleMap, occupiedEdges)) {
                for (Circle adjacentCircle : startVertex.getAdjacentCircles()) {
                    Pair<Circle, Circle> circlePair = new Pair<>(startCircle, adjacentCircle);
                    Pair<Circle, Circle> circlePairReverse = new Pair<>(adjacentCircle, startCircle);
                    if (!(occupiedEdges.contains(circlePair) || occupiedEdges.contains(circlePairReverse))) {
                        return new Pair<>(circleMap.get(circlePair.getKey()), circleMap.get(circlePair.getValue()));
                    }
                }
            }
            if (isVertexHasEmptyEdge(endVertex, circleMap, occupiedEdges)) {
                for (Circle adjacentCircle : endVertex.getAdjacentCircles()) {
                    Pair<Circle, Circle> circlePair = new Pair<>(endCircle, adjacentCircle);
                    Pair<Circle, Circle> circlePairReverse = new Pair<>(adjacentCircle, endCircle);
                    if (!(occupiedEdges.contains(circlePair) || occupiedEdges.contains(circlePairReverse))) {
                        return new Pair<>(circleMap.get(circlePair.getKey()), circleMap.get(circlePair.getValue()));
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

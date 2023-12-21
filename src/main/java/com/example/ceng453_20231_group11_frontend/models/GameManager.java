package com.example.ceng453_20231_group11_frontend.models;

import com.example.ceng453_20231_group11_frontend.enums.ResourceType;
import com.example.ceng453_20231_group11_frontend.enums.TurnPlayerState;
import com.example.ceng453_20231_group11_frontend.enums.TurnState;
import javafx.scene.shape.Circle;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

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
    public static boolean isRoadBuildable(String PlayerColor) {
        return true;
    }

    public boolean isAnySettlementBuildableByPlayer(PlayerAbstract player, HashMap<Circle, CircleVertex> circleMap) {
        if (player.resources.get(ResourceType.LUMBER) >= 1 && player.resources.get(ResourceType.BRICK) >= 1 &&
                player.resources.get(ResourceType.GRAIN) >= 1 && player.resources.get(ResourceType.WOOL) >= 1) {
            for (Map.Entry<Circle, CircleVertex> entry: circleMap.entrySet()) {
                CircleVertex circleVertex = entry.getValue();
                if (isSettlementBuildableToVertex(circleMap, circleVertex)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public boolean isSettlementBuildableToVertex(HashMap<Circle, CircleVertex> circleMap, CircleVertex circleVertex) {
        if (!circleVertex.isHasSettlement() && !circleVertex.isHasCity()) {
            for (Circle circle: circleVertex.getAdjacentCircles()) {
                CircleVertex adjacentCircleVertex = circleMap.get(circle);
                if (adjacentCircleVertex.isHasSettlement() || adjacentCircleVertex.isHasCity()) {
                    return false;
                }
            }
            return true;
        }
        return false;
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

    public boolean isTurnStateValidForBuilding() {
        return this.turnState == TurnState.TURN_PLAYER;
    }

    public boolean isTurnStateValidForRolling() {
        return this.turnState == TurnState.ROLL_DICE;
    }

    public TurnPlayerState getCurrentTurnPlayerState() {
        return this.turnPlayerState;
    }
}

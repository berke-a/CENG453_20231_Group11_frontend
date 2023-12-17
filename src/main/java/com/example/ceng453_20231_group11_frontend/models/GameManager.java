package com.example.ceng453_20231_group11_frontend.models;

import com.example.ceng453_20231_group11_frontend.enums.ResourceType;
import com.example.ceng453_20231_group11_frontend.enums.TurnPlayerState;
import com.example.ceng453_20231_group11_frontend.enums.TurnState;
import javafx.scene.shape.Circle;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

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

    public boolean isAnySettlementBuildable(PlayerAbstract Player, HashMap<Circle, CircleVertex> circleMap) {
        // TODO for loop icinde vertexleri dene alttaki helperla
        return false;
    }

    private boolean isSettlementBuildable(PlayerAbstract Player, HashMap<Circle, CircleVertex> circleMap, CircleVertex circleVertex) {
        if (Player.resources.get(ResourceType.LUMBER) >= 1 && Player.resources.get(ResourceType.BRICK) >= 1 &&
                Player.resources.get(ResourceType.GRAIN) >= 1 && Player.resources.get(ResourceType.WOOL) >= 1 &&
                !circleVertex.isHasCity() && !circleVertex.isHasSettlement()) {
            boolean isAdjacentCircleHasBuilding = false;
            for (Circle circle: circleVertex.getAdjacentCircles()) {
                CircleVertex adjacentCircleVertex = circleMap.get(circle);
                if (adjacentCircleVertex.isHasSettlement() || adjacentCircleVertex.isHasCity()) {
                    isAdjacentCircleHasBuilding = true;
                }
            }
            return !isAdjacentCircleHasBuilding;
        }
        return false;
    }

    public boolean isAnyCityBuildable(PlayerAbstract Player) {
        // TODO for donecek alttaki helperla
        return true;
    }

    private boolean isCityBuildable(PlayerAbstract Player, CircleVertex circleVertex) {
        System.out.println("build " + circleVertex);
        if (Player.resources.get(ResourceType.ORE) >= 3 && Player.resources.get(ResourceType.GRAIN) >= 2) {
            for (CircleVertex settlement: Player.settlements) {
                if (settlement.equals(circleVertex)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

}

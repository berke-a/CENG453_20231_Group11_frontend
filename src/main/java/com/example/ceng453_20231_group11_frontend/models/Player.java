package com.example.ceng453_20231_group11_frontend.models;

import com.example.ceng453_20231_group11_frontend.enums.ResourceType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
public class Player {
    private int score = 0;
    private boolean hasLongestRoad = false;

    private HashMap<ResourceType, Integer> resources = new HashMap<>() {{
        put(ResourceType.LUMBER, 0);
        put(ResourceType.BRICK, 0);
        put(ResourceType.GRAIN, 0);
        put(ResourceType.WOOL, 0);
        put(ResourceType.ORE, 0);
    }};

    // TODO: Update type to circles
    private int roads; //TODO
    private int settlements; //TODO
    private int cities; //TODO

    public boolean hasWonTheGame() {
        if (hasLongestRoad) {
            return score >= 6;
        } else {
            return score >= 8;
        }
    }

    private boolean canBuildRoad() {
        boolean isSpaceExists = true; // TODO road available logic
        return true;
        // return (lumbers > 0 && bricks > 0 && isSpaceExists);
    }

    private boolean isEdgeRoadBuildable(Integer edgeId) {
        return true; // TODO
    }

    private boolean buildRoad(Integer edgeId) { // TODO true builded (skip turn) / false continue to the while loop after warning
        if (isEdgeRoadBuildable(edgeId)) {
            // lumbers--;
            // bricks--;
            // TODO add road to board and roads of this class
            return true;
        } else {
            // TODO warning message (popup ?)
            return false;
        }
    }

    private boolean canBuildSettlement() {
        boolean isSpaceExists = true; // TODO settlement available logic
        return true;
        //return (lumbers > 0 && bricks > 0 && grains > 0 && wools > 0 && isSpaceExists);
    }

    private boolean isSettlementBuildable(Integer vertexId) {
        return true; // TODO
    }

    private boolean buildSettlement(Integer vertexId) {
        if (isSettlementBuildable(vertexId)) {
            // lumbers--;
            // bricks--;
            // grains--;
            // wools--;
            score++;
            // TODO add settlement to board and settlements of this class
            return true;
        } else {
            // TODO warning
            return false;
        }
    }

    private boolean canBuildCity() {
        boolean isSettlementExists = true; // TODO
        return true;
        // return (ores > 2 && grains > 1 && isSettlementExists);
    }

    private boolean isCityBuildable(Integer vertexId) {
        return true; // TODO
    }

    private boolean buildCity(Integer vertexId) {
        if (isCityBuildable(vertexId)) {
            // ores -= 3;
            // grains -= 2;
            score++;
            // TODO add settlement to board and settlements of this class
            return true;
        } else {
            // TODO warning
            return false;
        }
    }
}

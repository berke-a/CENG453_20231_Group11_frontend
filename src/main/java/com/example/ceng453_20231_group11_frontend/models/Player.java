package com.example.ceng453_20231_group11_frontend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Player {
    private int score = 0;
    private boolean hasLongestRoad = false;

    private int bricks = 0;
    private int lumbers = 0;
    private int ores = 0;
    private int grains = 0;
    private int wools = 0;

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
        return (lumbers > 0 && bricks > 0 && isSpaceExists);
    }

    private boolean isEdgeRoadBuildable(Integer edgeId) {
        return true; // TODO
    }

    private boolean buildRoad(Integer edgeId) { // TODO true builded (skip turn) / false continue to the while loop after warning
        if (isEdgeRoadBuildable(edgeId)) {
            lumbers--;
            bricks--;
            // TODO add road to board and roads of this class
            return true;
        } else {
            // TODO warning message (popup ?)
            return false;
        }
    }

    private boolean canBuildSettlement() {
        boolean isSpaceExists = true; // TODO settlement available logic
        return (lumbers > 0 && bricks > 0 && grains > 0 && wools > 0 && isSpaceExists);
    }

    private boolean isSettlementBuildable(Integer vertexId) {
        return true; // TODO
    }

    private boolean buildSettlement(Integer vertexId) {
        if (isSettlementBuildable(vertexId)) {
            lumbers--;
            bricks--;
            grains--;
            wools--;
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
        return (ores > 2 && grains > 1 && isSettlementExists);
    }

    private boolean isCityBuildable(Integer vertexId) {
        return true; // TODO
    }

    private boolean buildCity(Integer vertexId) {
        if (isCityBuildable(vertexId)) {
            ores -= 3;
            grains -= 2;
            score++;
            // TODO add settlement to board and settlements of this class
            return true;
        } else {
            // TODO warning
            return false;
        }
    }
}

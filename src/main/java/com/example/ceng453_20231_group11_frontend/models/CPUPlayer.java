package com.example.ceng453_20231_group11_frontend.models;

import com.example.ceng453_20231_group11_frontend.enums.PlayerColor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CPUPlayer extends PlayerAbstract {
    public CPUPlayer(PlayerColor color) {
        super(color);
    }

    // TODO in the board call play
    // TODO then check and reassign the longest road boolean
    // TODO then call hasWonTheGame

    public boolean hasWonTheGame() {
        if (hasLongestRoad) {
            return score >= 6;
        } else {
            return score >= 8;
        }
    }

    @Override
    boolean isRoadBuildable(Integer edgeId) {
        return false;
    }

    @Override
    boolean isCityBuildable(Integer edgeId) {
        return false;
    }

    @Override
    boolean isSettlementBuildable(Integer edgeId) {
        return false;
    }
}

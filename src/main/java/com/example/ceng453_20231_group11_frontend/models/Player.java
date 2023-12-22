package com.example.ceng453_20231_group11_frontend.models;

import com.example.ceng453_20231_group11_frontend.enums.PlayerColor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player extends PlayerAbstract {

    public Player(PlayerColor color) {
        super(color);
    }

    private boolean canBuildRoad() {
        boolean isSpaceExists = true; // TODO road available logic
        return true;
        // return (lumbers > 0 && bricks > 0 && isSpaceExists);
    }

    private boolean canBuildSettlement() {
        boolean isSpaceExists = true; // TODO settlement available logic
        return true;
        //return (lumbers > 0 && bricks > 0 && grains > 0 && wools > 0 && isSpaceExists);
    }

    private boolean canBuildCity() {
        boolean isSettlementExists = true; // TODO
        return true;
        // return (ores > 2 && grains > 1 && isSettlementExists);
    }
}

package com.example.ceng453_20231_group11_frontend.models;

import com.example.ceng453_20231_group11_frontend.enums.TurnState;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameManager {

    private static GameManager instance;
    private static TurnState turnState = TurnState.INITIALIZATION;


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

    // TODO: Create Player Class & Pass as a parameter
    public static boolean isSettlementBuildable(String PlayerColor) {
        return true;
    }

    // TODO: Create Player Class & Pass as a parameter
    public static boolean isCityBuildable(String PlayerColor) {
        return true;
    }

}

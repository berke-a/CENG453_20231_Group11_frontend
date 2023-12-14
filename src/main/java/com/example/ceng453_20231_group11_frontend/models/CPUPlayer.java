package com.example.ceng453_20231_group11_frontend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CPUPlayer {
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

    public void play() { // TODO play'in argumani olarak bir seyler verip road availablitye mi bakalim
        if (canBuildRoad()) {
            buildRoad();
        } else if (canBuildSettlement()) {
            buildSettlement();
        } else if (canBuildCity()) {
            buildCity();
        }
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

    private boolean canBuildRoad() {
        boolean isSpaceExists = true; // TODO road available logic
        return (lumbers > 0 && bricks > 0 && isSpaceExists);
    }

    private void buildRoad() {
        lumbers--;
        bricks--;
        // TODO add random road to board and roads of this class
    }

    private boolean canBuildSettlement() {
        boolean isSpaceExists = true; // TODO settlement available logic
        return (lumbers > 0 && bricks > 0 && grains > 0 && wools > 0 && isSpaceExists);
    }

    private void buildSettlement() {
        lumbers--;
        bricks--;
        grains--;
        wools--;
        score++;
        // TODO add random settlement to board and settlements of this class
    }

    private boolean canBuildCity() {
        boolean isSettlementExists = true; // TODO
        return (ores > 2 && grains > 1 && isSettlementExists);
    }

    private void buildCity() {
        ores -= 3;
        grains -= 2;
        score++;
        // TODO select a random settlement and transform it into a city
    }

}

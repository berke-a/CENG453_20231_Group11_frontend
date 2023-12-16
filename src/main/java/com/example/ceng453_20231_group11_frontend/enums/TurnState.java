package com.example.ceng453_20231_group11_frontend.enums;

public enum TurnState {
    INITIALIZATION,
    ROLL_DICE,
    TURN_PLAYER;

    public TurnState next() {
        // Find the index of the next state and wrap around if it reaches the end
        int nextIndex = (this.ordinal() + 1) % TurnState.values().length;
        return TurnState.values()[nextIndex];
    }
}

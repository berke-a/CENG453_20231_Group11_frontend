package com.example.ceng453_20231_group11_frontend.enums;

public enum TurnPlayerState {
    TURN_BLUE,
    TURN_RED,
    TURN_ORANGE,
    TURN_GREEN;

    @Override
    public String toString() {
        switch (this) {
            case TURN_BLUE:
                return "Blue";
            case TURN_RED:
                return "Red";
            case TURN_ORANGE:
                return "Orange";
            case TURN_GREEN:
                return "Green";
            default:
                return "Unknown";
        }
    }

    public TurnState next() {
        int nextIndex = (this.ordinal() + 1) % TurnState.values().length;
        return TurnState.values()[nextIndex];
    }
}

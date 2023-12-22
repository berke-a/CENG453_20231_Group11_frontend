package com.example.ceng453_20231_group11_frontend.enums;

import javafx.scene.paint.Color;
import lombok.Getter;

@Getter
public enum PlayerColor {
    RED(Color.RED),
    BLUE(Color.BLUE),
    GREEN(Color.GREEN),
    ORANGE(Color.ORANGE);  // Define more colors if needed

    private final Color color;

    PlayerColor(Color color) {
        this.color = color;
    }
}

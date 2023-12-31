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

}

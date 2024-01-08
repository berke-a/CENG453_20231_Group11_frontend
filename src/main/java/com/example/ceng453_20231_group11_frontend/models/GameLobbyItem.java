package com.example.ceng453_20231_group11_frontend.models;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameLobbyItem {
    private String lobbyName;
    private String playerNumber;
    private String gameState;
}

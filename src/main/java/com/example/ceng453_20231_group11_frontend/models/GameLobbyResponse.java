package com.example.ceng453_20231_group11_frontend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GameLobbyResponse {
    private List<Object> data;
    private String message;
    private String result;
}

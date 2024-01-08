package com.example.ceng453_20231_group11_frontend.service;

import com.example.ceng453_20231_group11_frontend.constants.GeneralConstants;
import com.example.ceng453_20231_group11_frontend.models.GameLobbyResponse;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class GameLobbyService {

    public static List<Object> getGameLobbies() {
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get(GeneralConstants.BACKEND_BASE_URL + "/gamelobbies").asJson();
            GameLobbyResponse gameLobbyResponse = new Gson().fromJson(apiResponse.getBody().toString(), GameLobbyResponse.class);
            return gameLobbyResponse.getData();
        } catch (UnirestException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }
}
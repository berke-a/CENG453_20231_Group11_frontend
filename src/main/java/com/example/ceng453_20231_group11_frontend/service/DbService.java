package com.example.ceng453_20231_group11_frontend.service;

import com.example.ceng453_20231_group11_frontend.controller.LeaderboardController;
import com.example.ceng453_20231_group11_frontend.models.LeaderboardResponse;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DbService {

    public static List<Map<String, Object>> getWeeklyLeaderboard() {
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/api/scoreboard?interval=WEEKLY").asJson();
            LeaderboardResponse leaderboardResponse = new Gson().fromJson(apiResponse.getBody().toString(), LeaderboardResponse.class);
            return (List<Map<String, Object>>) leaderboardResponse.getData();
        } catch (UnirestException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
//
    public static List<Map<String, Object>> getMonthlyLeaderboard() {
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/api/scoreboard?interval=MONTHLY").asJson();
            LeaderboardResponse leaderboardResponse = new Gson().fromJson(apiResponse.getBody().toString(), LeaderboardResponse.class);
            return (List<Map<String, Object>>) leaderboardResponse.getData();
        } catch (UnirestException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static List<Map<String, Object>> getAlltimeLeaderboard() {
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("http://localhost:8080/api/scoreboard?interval=ALLTIME").asJson();
            LeaderboardResponse leaderboardResponse = new Gson().fromJson(apiResponse.getBody().toString(), LeaderboardResponse.class);
            return (List<Map<String, Object>>) leaderboardResponse.getData();
        } catch (UnirestException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

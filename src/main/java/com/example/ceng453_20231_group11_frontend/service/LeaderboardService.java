package com.example.ceng453_20231_group11_frontend.service;

import com.example.ceng453_20231_group11_frontend.constants.GeneralConstants;
import com.example.ceng453_20231_group11_frontend.models.LeaderboardResponse;
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
public class LeaderboardService {

    public static List<Map<String, Object>> getWeeklyLeaderboard() {
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get(GeneralConstants.BACKEND_BASE_URL + "/scoreboard?interval=WEEKLY").asJson();
            LeaderboardResponse leaderboardResponse = new Gson().fromJson(apiResponse.getBody().toString(), LeaderboardResponse.class);
            return (List<Map<String, Object>>) leaderboardResponse.getData();
        } catch (UnirestException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static List<Map<String, Object>> getMonthlyLeaderboard() {
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get(GeneralConstants.BACKEND_BASE_URL + "/scoreboard?interval=MONTHLY").asJson();
            LeaderboardResponse leaderboardResponse = new Gson().fromJson(apiResponse.getBody().toString(), LeaderboardResponse.class);
            return (List<Map<String, Object>>) leaderboardResponse.getData();
        } catch (UnirestException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static List<Map<String, Object>> getAlltimeLeaderboard() {
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get(GeneralConstants.BACKEND_BASE_URL + "/scoreboard?interval=ALLTIME").asJson();
            LeaderboardResponse leaderboardResponse = new Gson().fromJson(apiResponse.getBody().toString(), LeaderboardResponse.class);
            return (List<Map<String, Object>>) leaderboardResponse.getData();
        } catch (UnirestException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

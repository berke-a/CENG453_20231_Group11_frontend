package com.example.ceng453_20231_group11_frontend.models;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeaderboardItem {
    private String username;
    private Integer score;

    public LeaderboardItem(Map<String, Object> map) {
        this.username = (String) map.get("username");
        this.score = ((Double) map.get("score")).intValue();
    }
}

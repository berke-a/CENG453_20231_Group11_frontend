package com.example.ceng453_20231_group11_frontend.models;

import com.example.ceng453_20231_group11_frontend.enums.PlayerColor;
import com.example.ceng453_20231_group11_frontend.enums.ResourceType;

import java.util.HashMap;

abstract class PlayerAbstract {
    Integer score = 0;
    boolean hasLongestRoad = false;
    PlayerColor color;

    HashMap<ResourceType, Integer> resources = new HashMap<>() {{
        put(ResourceType.LUMBER, 0);
        put(ResourceType.BRICK, 0);
        put(ResourceType.GRAIN, 0);
        put(ResourceType.WOOL, 0);
        put(ResourceType.ORE, 0);
    }};

    // TODO: Update type to circles
    Integer roads = 0; //TODO
    public CircleVertex[] settlements = new CircleVertex[0];
    public CircleVertex[] cities = new CircleVertex[0];

    abstract boolean hasWonTheGame();

    abstract boolean isRoadBuildable(Integer edgeId);

    abstract boolean isCityBuildable(Integer edgeId);

    abstract boolean isSettlementBuildable(Integer edgeId);

    public void addResource(ResourceType resourceType, Integer amount) {
        resources.put(resourceType, resources.get(resourceType) + amount);
    }

}

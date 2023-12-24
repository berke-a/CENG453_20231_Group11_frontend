package com.example.ceng453_20231_group11_frontend.models;

import com.example.ceng453_20231_group11_frontend.enums.PlayerColor;
import com.example.ceng453_20231_group11_frontend.enums.ResourceType;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

abstract public class PlayerAbstract {
    Integer victoryPoint = 0;
    boolean hasLongestRoad = false;
    public PlayerColor color;

    public PlayerAbstract(PlayerColor color) {
        this.color = color;
    }

    HashMap<ResourceType, Integer> resources = new HashMap<>() {{
        put(ResourceType.LUMBER, 0);
        put(ResourceType.BRICK, 0);
        put(ResourceType.GRAIN, 0);
        put(ResourceType.WOOL, 0);
        put(ResourceType.ORE, 0);
    }};


    public ArrayList<Pair<CircleVertex, CircleVertex>> roads = new ArrayList<>(); // circleStart - circleEnd
    public ArrayList<CircleVertex> settlements = new ArrayList<>();
    public ArrayList<CircleVertex> cities = new ArrayList<>();

    boolean hasWonTheGame() {
        if (hasLongestRoad) {
            return victoryPoint >= 6;
        } else {
            return victoryPoint >= 8;
        }
    }

    public void updateResource(ResourceType resourceType, Integer amount) {
        resources.put(resourceType, resources.get(resourceType) + amount);
    }

    public void buildRoad(Pair<CircleVertex, CircleVertex> road) {
        this.updateResource(ResourceType.LUMBER, -1);
        this.updateResource(ResourceType.BRICK, -1);

        this.roads.add(road);
    }

    public void buildSettlement(CircleVertex circleVertex) {
        this.updateResource(ResourceType.LUMBER, -1);
        this.updateResource(ResourceType.BRICK, -1);
        this.updateResource(ResourceType.GRAIN, -1);
        this.updateResource(ResourceType.WOOL, -1);

        this.settlements.add(circleVertex);
        circleVertex.setHasSettlement(true);
        circleVertex.setOwner(this);

        this.victoryPoint++;
    }

    public void buildCity(CircleVertex circleVertex) {
        this.updateResource(ResourceType.ORE, -3);
        this.updateResource(ResourceType.GRAIN, -2);

        this.settlements.remove(circleVertex);
        this.cities.add(circleVertex);
        circleVertex.setHasSettlement(false);
        circleVertex.setHasCity(true);
        circleVertex.setOwner(this);

        victoryPoint++;
    }

    public Integer getResource(ResourceType resourceType) {
        return resources.get(resourceType);
    }

    public Integer getTotalResource() {
        return resources.values().stream().reduce(0, Integer::sum);
    }

}

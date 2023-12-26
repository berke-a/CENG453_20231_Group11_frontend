package com.example.ceng453_20231_group11_frontend.models;

import com.example.ceng453_20231_group11_frontend.enums.PlayerColor;
import com.example.ceng453_20231_group11_frontend.enums.ResourceType;
import javafx.util.Pair;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

@Getter
@Setter
abstract public class PlayerAbstract {
    @Getter
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

    public Integer getVictoryPoint() {
        return hasLongestRoad ? victoryPoint + 2 : victoryPoint;
    }


    public ArrayList<Pair<CircleVertex, CircleVertex>> roads = new ArrayList<>(); // circleStart - circleEnd
    public ArrayList<CircleVertex> settlements = new ArrayList<>();
    public ArrayList<CircleVertex> cities = new ArrayList<>();

    public boolean hasWonTheGame() {
        if (hasLongestRoad) {
            return victoryPoint >= 6;
        } else {
            return victoryPoint >= 8;
        }
    }

    public Integer calculateLongestRoad() {
        HashMap<CircleVertex, ArrayList<CircleVertex>> graph = convertRoadsToGraph();
        int longestRoadLength = 0;

        for (Pair<CircleVertex, CircleVertex> road : roads) {
            // Start DFS from both ends of each road segment
            int lengthFromStart = longestRoadDFS(road.getKey(), null, graph, new HashSet<>());
            int lengthFromEnd = longestRoadDFS(road.getValue(), null, graph, new HashSet<>());

            // Update the longest road length if a longer one is found
            longestRoadLength = Math.max(longestRoadLength, Math.max(lengthFromStart, lengthFromEnd));
        }
        return longestRoadLength;
    }

    public void updateResource(ResourceType resourceType, Integer amount) {
        resources.put(resourceType, resources.get(resourceType) + amount);
    }

    public void updateVictoryPoint(Integer amount) {
        victoryPoint += amount;
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

    private HashMap<CircleVertex, ArrayList<CircleVertex>> convertRoadsToGraph() {
        HashMap<CircleVertex, ArrayList<CircleVertex>> graph = new HashMap<>();
        for (Pair<CircleVertex, CircleVertex> road : roads) {
            graph.putIfAbsent(road.getKey(), new ArrayList<>());
            graph.putIfAbsent(road.getValue(), new ArrayList<>());

            graph.get(road.getKey()).add(road.getValue());
            graph.get(road.getValue()).add(road.getKey());
        }
        return graph;
    }

    private int longestRoadDFS(CircleVertex current, CircleVertex parent, HashMap<CircleVertex, ArrayList<CircleVertex>> graph, HashSet<CircleVertex> visited) {
        if (visited.contains(current)) return 0; // Already visited this vertex
        visited.add(current);

        int longest = 0;
        for (CircleVertex neighbor : graph.getOrDefault(current, new ArrayList<>())) {
            if (neighbor.equals(parent)) continue; // Avoid going back to parent
            int length = longestRoadDFS(neighbor, current, graph, visited);
            longest = Math.max(longest, length);
        }
        return longest + 1;
    }
}

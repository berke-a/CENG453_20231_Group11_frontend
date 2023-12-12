package com.example.ceng453_20231_group11_frontend.controller;

import com.example.ceng453_20231_group11_frontend.models.Tile;
import com.example.ceng453_20231_group11_frontend.models.TileType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Polygon;

import java.util.HashMap;

abstract class BoardControllerAbstract implements Initializable {
    @FXML
    protected Polygon tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9, tile10,
            tile11, tile12, tile13, tile14, tile15, tile16, tile17, tile18, tile19;

    protected HashMap<Polygon, Tile> tileMap = new HashMap<Polygon, Tile>();

    protected TileType[] tileTypes = {TileType.DESERT, TileType.LUMBER, TileType.LUMBER, TileType.LUMBER, TileType.LUMBER, TileType.BRICK, TileType.BRICK, TileType.BRICK, TileType.GRAIN, TileType.GRAIN, TileType.GRAIN, TileType.GRAIN,
            TileType.WOOL, TileType.WOOL, TileType.WOOL, TileType.ORE, TileType.ORE, TileType.ORE};

    protected int[] tileNumberTokens = {2, 3, 3, 4, 4, 5, 5, 6, 6, 8,
            8, 9, 9, 10, 10, 11, 11, 12};


    BoardControllerAbstract() {
        tileMap.put(tile1, new Tile());
        tileMap.put(tile2, new Tile());
        tileMap.put(tile3, new Tile());
        tileMap.put(tile4, new Tile());
        tileMap.put(tile5, new Tile());
        tileMap.put(tile6, new Tile());
        tileMap.put(tile7, new Tile());
        tileMap.put(tile8, new Tile());
        tileMap.put(tile9, new Tile());
        tileMap.put(tile10, new Tile());
        tileMap.put(tile11, new Tile());
        tileMap.put(tile12, new Tile());
        tileMap.put(tile13, new Tile());
        tileMap.put(tile14, new Tile());
        tileMap.put(tile15, new Tile());
        tileMap.put(tile16, new Tile());
        tileMap.put(tile17, new Tile());
        tileMap.put(tile18, new Tile());
        tileMap.put(tile19, new Tile());
    }
}

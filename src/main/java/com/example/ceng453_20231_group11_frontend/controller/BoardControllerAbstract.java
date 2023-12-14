package com.example.ceng453_20231_group11_frontend.controller;

import com.example.ceng453_20231_group11_frontend.constants.GeneralConstants;
import com.example.ceng453_20231_group11_frontend.enums.TileType;
import com.example.ceng453_20231_group11_frontend.models.CircleVertex;
import com.example.ceng453_20231_group11_frontend.models.Tile;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

import java.util.*;

abstract class BoardControllerAbstract implements Initializable {
    @FXML
    protected Polygon tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9, tile10,
            tile11, tile12, tile13, tile14, tile15, tile16, tile17, tile18, tile19;

    @FXML
    protected Circle circle1, circle2, circle3, circle4, circle5, circle6, circle7, circle8, circle9, circle10,
            circle11, circle12, circle13, circle14, circle15, circle16, circle17, circle18, circle19, circle20,
            circle21, circle22, circle23, circle24, circle25, circle26, circle27, circle28, circle29, circle30,
            circle31, circle32, circle33, circle34, circle35, circle36, circle37, circle38, circle39, circle40,
            circle41, circle42, circle43, circle44, circle45, circle46, circle47, circle48, circle49, circle50,
            circle51, circle52, circle53, circle54;

    protected HashMap<Polygon, Tile> tileMap = new HashMap<Polygon, Tile>();

    protected HashMap<Circle, CircleVertex> circleMap = new HashMap<>();

    protected HashMap<TileType, String> tileTypeToImage = new HashMap<TileType, String>() {{
        put(TileType.DESERT, GeneralConstants.TILE_DESERT);
        put(TileType.LUMBER, GeneralConstants.TILE_LUMBER);
        put(TileType.BRICK, GeneralConstants.TILE_BRICK);
        put(TileType.GRAIN, GeneralConstants.TILE_GRAIN);
        put(TileType.WOOL, GeneralConstants.TILE_WOOL);
        put(TileType.ORE, GeneralConstants.TILE_ORE);
    }};

    protected TileType[] tileTypes = {TileType.DESERT, TileType.LUMBER, TileType.LUMBER, TileType.LUMBER, TileType.LUMBER, TileType.BRICK, TileType.BRICK, TileType.BRICK, TileType.GRAIN, TileType.GRAIN, TileType.GRAIN, TileType.GRAIN,
            TileType.WOOL, TileType.WOOL, TileType.WOOL, TileType.ORE, TileType.ORE, TileType.ORE};

    protected Integer[] tileNumberTokens = {2, 3, 3, 4, 4, 5, 5, 6, 6, 8,
            8, 9, 9, 10, 10, 11, 11, 12};

    protected void initializeTiles() {
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

        this.populateRandomTiles();
    }

    protected void populateRandomTiles() {
        List<Polygon> keys = new ArrayList<>(this.tileMap.keySet());
        Collections.shuffle(keys);
        this.tileMap.get(keys.get(0)).setTileType(TileType.DESERT);

        for (int i = 1; i < keys.size(); i++) {
            this.tileMap.get(keys.get(i)).setTileType(this.tileTypes[i]);
            this.tileMap.get(keys.get(i)).setNumberToken(this.tileNumberTokens[i - 1]);
            Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(this.tileTypeToImage.get(this.tileTypes[i]))));
            keys.get(i).setFill(new ImagePattern(img));
        }

    }

    protected void initializeCircles() {
        circleMap.put(circle1, new CircleVertex());
        circleMap.put(circle2, new CircleVertex());
        circleMap.put(circle3, new CircleVertex());
        circleMap.put(circle4, new CircleVertex());
        circleMap.put(circle5, new CircleVertex());
        circleMap.put(circle6, new CircleVertex());
        circleMap.put(circle7, new CircleVertex());
        circleMap.put(circle8, new CircleVertex());
        circleMap.put(circle9, new CircleVertex());
        circleMap.put(circle10, new CircleVertex());
        circleMap.put(circle11, new CircleVertex());
        circleMap.put(circle12, new CircleVertex());
        circleMap.put(circle13, new CircleVertex());
        circleMap.put(circle14, new CircleVertex());
        circleMap.put(circle15, new CircleVertex());
        circleMap.put(circle16, new CircleVertex());
        circleMap.put(circle17, new CircleVertex());
        circleMap.put(circle18, new CircleVertex());
        circleMap.put(circle19, new CircleVertex());
        circleMap.put(circle20, new CircleVertex());
        circleMap.put(circle21, new CircleVertex());
        circleMap.put(circle22, new CircleVertex());
        circleMap.put(circle23, new CircleVertex());
        circleMap.put(circle24, new CircleVertex());
        circleMap.put(circle25, new CircleVertex());
        circleMap.put(circle26, new CircleVertex());
        circleMap.put(circle27, new CircleVertex());
        circleMap.put(circle28, new CircleVertex());
        circleMap.put(circle29, new CircleVertex());
        circleMap.put(circle30, new CircleVertex());
        circleMap.put(circle31, new CircleVertex());
        circleMap.put(circle32, new CircleVertex());
        circleMap.put(circle33, new CircleVertex());
        circleMap.put(circle34, new CircleVertex());
        circleMap.put(circle35, new CircleVertex());
        circleMap.put(circle36, new CircleVertex());
        circleMap.put(circle37, new CircleVertex());
        circleMap.put(circle38, new CircleVertex());
        circleMap.put(circle39, new CircleVertex());
        circleMap.put(circle40, new CircleVertex());
        circleMap.put(circle41, new CircleVertex());
        circleMap.put(circle42, new CircleVertex());
        circleMap.put(circle43, new CircleVertex());
        circleMap.put(circle44, new CircleVertex());
        circleMap.put(circle45, new CircleVertex());
        circleMap.put(circle46, new CircleVertex());
        circleMap.put(circle47, new CircleVertex());
        circleMap.put(circle48, new CircleVertex());
        circleMap.put(circle49, new CircleVertex());
        circleMap.put(circle50, new CircleVertex());
        circleMap.put(circle51, new CircleVertex());
        circleMap.put(circle52, new CircleVertex());
        circleMap.put(circle53, new CircleVertex());
        circleMap.put(circle54, new CircleVertex());
    }
}

package com.example.ceng453_20231_group11_frontend.controller;

import com.example.ceng453_20231_group11_frontend.Utils;
import com.example.ceng453_20231_group11_frontend.constants.GeneralConstants;
import com.example.ceng453_20231_group11_frontend.enums.TileType;
import com.example.ceng453_20231_group11_frontend.models.CircleVertex;
import com.example.ceng453_20231_group11_frontend.models.Tile;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import kotlin.Pair;

import java.util.*;

abstract class BoardControllerAbstract implements Initializable {
    @FXML
    protected Group boardGroup;

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

    @FXML
    protected Label tileLabel1, tileLabel2, tileLabel3, tileLabel4, tileLabel5, tileLabel6, tileLabel7, tileLabel8, tileLabel9, tileLabel10,
            tileLabel11, tileLabel12, tileLabel13, tileLabel14, tileLabel15, tileLabel16, tileLabel17, tileLabel18, tileLabel19;

    @FXML
    protected Text tileText1, tileText2, tileText3, tileText4, tileText5, tileText6, tileText7, tileText8, tileText9, tileText10,
            tileText11, tileText12, tileText13, tileText14, tileText15, tileText16, tileText17, tileText18, tileText19;

    @FXML
    protected Text playerWoolCount, playerLumberCount, playerBrickCount, playerGrainCount, playerOreCount;

    @FXML
    protected Text cpuBlueCardCount, cpuOrangeCardCount, cpuGreenCardCount;

    @FXML
    protected Pane helpContentTable;

    @FXML
    protected Text diceText1, diceText2, diceTotalText;

    @FXML
    protected Rectangle diceRectangle1, diceRectangle2;

    @FXML
    protected Button rollDiceButton;

    @FXML
    protected TextArea logTextArea;

    @FXML
    protected Rectangle settlement;

    @FXML
    protected Polygon city;

    @FXML
    protected Line road;

    protected Pair<Label, Text>[] tileLabelTextPairs;

    protected HashMap<Polygon, Tile> polygonTileHashMap = new HashMap<Polygon, Tile>();

    protected HashMap<Polygon, Pair<Label, Text>> tileLabelMap = new HashMap<Polygon, Pair<Label, Text>>();
    protected HashMap<Circle, CircleVertex> circleMap = new HashMap<>();


    protected void initializeTiles() {
        tileLabelTextPairs = new Pair[]{new Pair<>(tileLabel1, tileText1), new Pair<>(tileLabel2, tileText2), new Pair<>(tileLabel3, tileText3), new Pair<>(tileLabel4, tileText4), new Pair<>(tileLabel5, tileText5), new Pair<>(tileLabel6, tileText6), new Pair<>(tileLabel7, tileText7), new Pair<>(tileLabel8, tileText8), new Pair<>(tileLabel9, tileText9), new Pair<>(tileLabel10, tileText10),
                new Pair<>(tileLabel11, tileText11), new Pair<>(tileLabel12, tileText12), new Pair<>(tileLabel13, tileText13), new Pair<>(tileLabel14, tileText14), new Pair<>(tileLabel15, tileText15), new Pair<>(tileLabel16, tileText16), new Pair<>(tileLabel17, tileText17), new Pair<>(tileLabel18, tileText18), new Pair<>(tileLabel19, tileText19)};

        tileLabelMap = new HashMap<Polygon, Pair<Label, Text>>() {{
            put(tile1, new Pair<>(tileLabel1, tileText1));
            put(tile2, new Pair<>(tileLabel2, tileText2));
            put(tile3, new Pair<>(tileLabel3, tileText3));
            put(tile4, new Pair<>(tileLabel4, tileText4));
            put(tile5, new Pair<>(tileLabel5, tileText5));
            put(tile6, new Pair<>(tileLabel6, tileText6));
            put(tile7, new Pair<>(tileLabel7, tileText7));
            put(tile8, new Pair<>(tileLabel8, tileText8));
            put(tile9, new Pair<>(tileLabel9, tileText9));
            put(tile10, new Pair<>(tileLabel10, tileText10));
            put(tile11, new Pair<>(tileLabel11, tileText11));
            put(tile12, new Pair<>(tileLabel12, tileText12));
            put(tile13, new Pair<>(tileLabel13, tileText13));
            put(tile14, new Pair<>(tileLabel14, tileText14));
            put(tile15, new Pair<>(tileLabel15, tileText15));
            put(tile16, new Pair<>(tileLabel16, tileText16));
            put(tile17, new Pair<>(tileLabel17, tileText17));
            put(tile18, new Pair<>(tileLabel18, tileText18));
            put(tile19, new Pair<>(tileLabel19, tileText19));
        }};
        polygonTileHashMap = new HashMap<Polygon, Tile>() {{
            put(tile1, new Tile());
            put(tile2, new Tile());
            put(tile3, new Tile());
            put(tile4, new Tile());
            put(tile5, new Tile());
            put(tile6, new Tile());
            put(tile7, new Tile());
            put(tile8, new Tile());
            put(tile9, new Tile());
            put(tile10, new Tile());
            put(tile11, new Tile());
            put(tile12, new Tile());
            put(tile13, new Tile());
            put(tile14, new Tile());
            put(tile15, new Tile());
            put(tile16, new Tile());
            put(tile17, new Tile());
            put(tile18, new Tile());
            put(tile19, new Tile());
        }};

        this.populateRandomTiles();
    }

    protected void populateRandomTiles() {
        List<Polygon> keys = new ArrayList<>(this.polygonTileHashMap.keySet());
        Collections.shuffle(keys);

        Polygon desertTile = keys.get(0);
        this.polygonTileHashMap.get(desertTile).setTileType(TileType.DESERT);
        this.tileLabelMap.get(desertTile).getFirst().setText("D");
        this.tileLabelMap.get(desertTile).getSecond().setText("━");
        desertTile.setFill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResourceAsStream(GeneralConstants.tileTypeToImage.get(TileType.DESERT))))));

        for (int i = 1; i < keys.size(); i++) {
            TileType tileType = GeneralConstants.tileTypesOnBoard[i - 1];
            Image tileImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(GeneralConstants.tileTypeToImage.get(tileType))));
            Integer tileNumberToken = GeneralConstants.tileNumberTokensOnBoard[i - 1];
            String tileNumberPoints = Utils.generateTileNumberPoints(tileNumberToken);
            Polygon tile = keys.get(i);

            this.polygonTileHashMap.get(tile).setTileType(tileType);
            this.polygonTileHashMap.get(tile).setNumberToken(tileNumberToken);
            tile.setFill(new ImagePattern(tileImage));

            this.tileLabelMap.get(tile).getFirst().setText(String.valueOf(tileNumberToken));
            this.tileLabelMap.get(tile).getSecond().setText(tileNumberPoints);
        }

    }

    protected void initializeCircles() {
        circleMap.put(circle1, new CircleVertex(List.of(circle14, circle26, circle40), List.of(tile9, tile10, tile14)));
        circleMap.put(circle2, new CircleVertex(List.of(circle32, circle24, circle27), List.of(tile11, tile12, tile16)));
        circleMap.put(circle3, new CircleVertex(List.of(circle25, circle34, circle49), List.of(tile17, tile18)));
        circleMap.put(circle4, new CircleVertex(List.of(circle42, circle18, circle51), List.of(tile3, tile7)));
        circleMap.put(circle5, new CircleVertex(List.of(circle9, circle30, circle7), List.of(tile1, tile4, tile5)));
        circleMap.put(circle6, new CircleVertex(List.of(circle49, circle35), List.of(tile17)));
        circleMap.put(circle7, new CircleVertex(List.of(circle5, circle44, circle38), List.of(tile1, tile2, tile5)));
        circleMap.put(circle8, new CircleVertex(List.of(circle33, circle10, circle37), List.of(tile19, tile15, tile18)));
        circleMap.put(circle9, new CircleVertex(List.of(circle13, circle47, circle5), List.of(tile1, tile4)));
        circleMap.put(circle10, new CircleVertex(List.of(circle8, circle28, circle21), List.of(tile19, tile18)));
        circleMap.put(circle11, new CircleVertex(List.of(circle50, circle38, circle18), List.of(tile2, tile3, tile6)));
        circleMap.put(circle12, new CircleVertex(List.of(circle29, circle48, circle52), List.of(tile8, tile13)));
        circleMap.put(circle13, new CircleVertex(List.of(circle9, circle31), List.of(tile4)));
        circleMap.put(circle14, new CircleVertex(List.of(circle1, circle21, circle37), List.of(tile10, tile14, tile15)));
        circleMap.put(circle15, new CircleVertex(List.of(circle29, circle31), List.of(tile8)));
        circleMap.put(circle16, new CircleVertex(List.of(circle26, circle25, circle35), List.of(tile17, tile14, tile13)));
        circleMap.put(circle17, new CircleVertex(List.of(circle47, circle44), List.of(tile1)));
        circleMap.put(circle18, new CircleVertex(List.of(circle11, circle19, circle4), List.of(tile3, tile6, tile7)));
        circleMap.put(circle19, new CircleVertex(List.of(circle18, circle32, circle46), List.of(tile6, tile7, tile11)));
        circleMap.put(circle20, new CircleVertex(List.of(circle31, circle52, circle30), List.of(tile4, tile8, tile9)));
        circleMap.put(circle21, new CircleVertex(List.of(circle14, circle27, circle46), List.of(tile10, tile11, tile15)));
        circleMap.put(circle22, new CircleVertex(List.of(circle38, circle40, circle46), List.of(tile5, tile6, tile10)));
        circleMap.put(circle23, new CircleVertex(List.of(circle32, circle43, circle51), List.of(tile7, tile12)));
        circleMap.put(circle24, new CircleVertex(List.of(circle53, circle36, circle2), List.of(tile12, tile16)));
        circleMap.put(circle25, new CircleVertex(List.of(circle37, circle16, circle3), List.of(tile18, tile14, tile17)));
        circleMap.put(circle26, new CircleVertex(List.of(circle1, circle16, circle52), List.of(tile9, tile13, tile14)));
        circleMap.put(circle27, new CircleVertex(List.of(circle2, circle21, circle33), List.of(tile16, tile15, tile11)));
        circleMap.put(circle28, new CircleVertex(List.of(circle41, circle10), List.of(tile19)));
        circleMap.put(circle29, new CircleVertex(List.of(circle12, circle15), List.of(tile8)));
        circleMap.put(circle30, new CircleVertex(List.of(circle5, circle20, circle40), List.of(tile4, tile5, tile9)));
        circleMap.put(circle31, new CircleVertex(List.of(circle13, circle15, circle20), List.of(tile4, tile8)));
        circleMap.put(circle32, new CircleVertex(List.of(circle2, circle19, circle23), List.of(tile7, tile11, tile12)));
        circleMap.put(circle33, new CircleVertex(List.of(circle27, circle39, circle8), List.of(tile16, tile19, tile15)));
        circleMap.put(circle34, new CircleVertex(List.of(circle3, circle10), List.of(tile18)));
        circleMap.put(circle35, new CircleVertex(List.of(circle6, circle16, circle48), List.of(tile17, tile13)));
        circleMap.put(circle36, new CircleVertex(List.of(circle24, circle39), List.of(tile16)));
        circleMap.put(circle37, new CircleVertex(List.of(circle8, circle14, circle25), List.of(tile18, tile15, tile14)));
        circleMap.put(circle38, new CircleVertex(List.of(circle7, circle22, circle11), List.of(tile2, tile5, tile6)));
        circleMap.put(circle39, new CircleVertex(List.of(circle36, circle41, circle33), List.of(tile16, tile19)));
        circleMap.put(circle40, new CircleVertex(List.of(circle1, circle22, circle30), List.of(tile5, tile9, tile10)));
        circleMap.put(circle41, new CircleVertex(List.of(circle39, circle28), List.of(tile19)));
        circleMap.put(circle42, new CircleVertex(List.of(circle45, circle4), List.of(tile3)));
        circleMap.put(circle43, new CircleVertex(List.of(circle23, circle53), List.of(tile12)));
        circleMap.put(circle44, new CircleVertex(List.of(circle17, circle7, circle54), List.of(tile1, tile2)));
        circleMap.put(circle45, new CircleVertex(List.of(circle50, circle42), List.of(tile3)));
        circleMap.put(circle46, new CircleVertex(List.of(circle19, circle21, circle22), List.of(tile6, tile10, tile11)));
        circleMap.put(circle47, new CircleVertex(List.of(circle9, circle17), List.of(tile1)));
        circleMap.put(circle48, new CircleVertex(List.of(circle12, circle35), List.of(tile13)));
        circleMap.put(circle49, new CircleVertex(List.of(circle3, circle6), List.of(tile17)));
        circleMap.put(circle50, new CircleVertex(List.of(circle54, circle45), List.of(tile2, tile3)));
        circleMap.put(circle51, new CircleVertex(List.of(circle4, circle23), List.of(tile7)));
        circleMap.put(circle52, new CircleVertex(List.of(circle12, circle20, circle26), List.of(tile8, tile9, tile13)));
        circleMap.put(circle53, new CircleVertex(List.of(circle24, circle43), List.of(tile12)));
        circleMap.put(circle54, new CircleVertex(List.of(circle44, circle50), List.of(tile2)));
    }
}

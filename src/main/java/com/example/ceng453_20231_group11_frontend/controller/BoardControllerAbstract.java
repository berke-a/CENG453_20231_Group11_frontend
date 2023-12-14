package com.example.ceng453_20231_group11_frontend.controller;

import com.example.ceng453_20231_group11_frontend.Utils;
import com.example.ceng453_20231_group11_frontend.constants.GeneralConstants;
import com.example.ceng453_20231_group11_frontend.enums.TileType;
import com.example.ceng453_20231_group11_frontend.models.Tile;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import kotlin.Pair;

import java.util.*;

abstract class BoardControllerAbstract implements Initializable {
    @FXML
    protected Polygon tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9, tile10,
            tile11, tile12, tile13, tile14, tile15, tile16, tile17, tile18, tile19;

    @FXML
    protected Label tileLabel1, tileLabel2, tileLabel3, tileLabel4, tileLabel5, tileLabel6, tileLabel7, tileLabel8, tileLabel9, tileLabel10,
            tileLabel11, tileLabel12, tileLabel13, tileLabel14, tileLabel15, tileLabel16, tileLabel17, tileLabel18, tileLabel19;

    @FXML
    protected Text tileText1, tileText2, tileText3, tileText4, tileText5, tileText6, tileText7, tileText8, tileText9, tileText10,
            tileText11, tileText12, tileText13, tileText14, tileText15, tileText16, tileText17, tileText18, tileText19;

    protected Pair<Label, Text>[] tileLabelTextPairs;

    protected HashMap<Polygon, Tile> tileMap = new HashMap<Polygon, Tile>();

    protected HashMap<Polygon, Pair<Label, Text>> tileLabelMap = new HashMap<Polygon, Pair<Label, Text>>();


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
        tileMap = new HashMap<Polygon, Tile>() {{
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
        List<Polygon> keys = new ArrayList<>(this.tileMap.keySet());
        Collections.shuffle(keys);

        Polygon desertTile = keys.get(0);
        this.tileMap.get(desertTile).setTileType(TileType.DESERT);
        this.tileLabelMap.get(desertTile).getFirst().setVisible(false);
        this.tileLabelMap.get(desertTile).getSecond().setVisible(false);
        desertTile.setFill(new ImagePattern(new Image(Objects.requireNonNull(getClass().getResourceAsStream(GeneralConstants.tileTypeToImage.get(TileType.DESERT))))));

        for (int i = 1; i < keys.size(); i++) {
            TileType tileType = GeneralConstants.tileTypesOnBoard[i - 1];
            Image tileImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(GeneralConstants.tileTypeToImage.get(tileType))));
            Integer tileNumberToken = GeneralConstants.tileNumberTokensOnBoard[i - 1];
            String tileNumberPoints = Utils.generateTileNumberPoints(tileNumberToken);
            Polygon tile = keys.get(i);

            this.tileMap.get(tile).setTileType(tileType);
            this.tileMap.get(tile).setNumberToken(tileNumberToken);
            tile.setFill(new ImagePattern(tileImage));

            this.tileLabelMap.get(tile).getFirst().setText(String.valueOf(tileNumberToken));
            this.tileLabelMap.get(tile).getSecond().setText(tileNumberPoints);
        }

    }
}

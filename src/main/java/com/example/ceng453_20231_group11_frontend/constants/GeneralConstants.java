package com.example.ceng453_20231_group11_frontend.constants;

import com.example.ceng453_20231_group11_frontend.enums.TileType;

import java.util.HashMap;

public class GeneralConstants {

    // App Properties
    public static final String BACKEND_BASE_URL = "https://ceng453-20231-group11-backend.onrender.com/api";
    public static final String WINDOW_TITLE = "HexaLands";
    public static final Integer WINDOW_WIDTH = 900;
    public static final Integer WINDOW_HEIGHT = 800;


    // FXML Locations
    public static final String HOME_PAGE = "fxml/home.fxml";
    public static final String REGISTER_PAGE = "fxml/register.fxml";
    public static final String LOGIN_PAGE = "fxml/login.fxml";
    public static final String LEADERBOARD_PAGE = "fxml/leaderboard.fxml";
    public static final String FORGOT_PASSWORD_PAGE = "fxml/forgot-password.fxml";
    public static final String RESET_PASSWORD_PAGE = "fxml/reset-password.fxml";
    public static final String BOARD_PAGE = "fxml/board.fxml";

    // Image Locations
    public static final String TILE_BRICK = "/com/example/ceng453_20231_group11_frontend/images/tile_brick.png";
    public static final String TILE_DESERT = "/com/example/ceng453_20231_group11_frontend/images/tile_desert.png";
    public static final String TILE_LUMBER = "/com/example/ceng453_20231_group11_frontend/images/tile_lumber.png";
    public static final String TILE_ORE = "/com/example/ceng453_20231_group11_frontend/images/tile_ore.png";
    public static final String TILE_WOOL = "/com/example/ceng453_20231_group11_frontend/images/tile_wool.png";
    public static final String TILE_GRAIN = "/com/example/ceng453_20231_group11_frontend/images/tile_grain.png";
    public static final String TILE_WATER = "/com/example/ceng453_20231_group11_frontend/images/tile_water.png";
    public static final String RESOURCE_BRICK = "/com/example/ceng453_20231_group11_frontend/images/resource_brick.png";
    public static final String RESOURCE_LUMBER = "/com/example/ceng453_20231_group11_frontend/images/resource_lumber.png";
    public static final String RESOURCE_ORE = "/com/example/ceng453_20231_group11_frontend/images/resource_ore.png";
    public static final String RESOURCE_WOOL = "/com/example/ceng453_20231_group11_frontend/images/resource_wool.png";
    public static final String RESOURCE_GRAIN = "/com/example/ceng453_20231_group11_frontend/images/resource_grain.png";


    // Constant Data
    public static final HashMap<TileType, String> tileTypeToImage = new HashMap<TileType, String>() {{
        put(TileType.DESERT, GeneralConstants.TILE_DESERT);
        put(TileType.LUMBER, GeneralConstants.TILE_LUMBER);
        put(TileType.BRICK, GeneralConstants.TILE_BRICK);
        put(TileType.GRAIN, GeneralConstants.TILE_GRAIN);
        put(TileType.WOOL, GeneralConstants.TILE_WOOL);
        put(TileType.ORE, GeneralConstants.TILE_ORE);
    }};

    public static final TileType[] tileTypesOnBoard = {TileType.DESERT, TileType.LUMBER, TileType.LUMBER, TileType.LUMBER, TileType.LUMBER, TileType.BRICK, TileType.BRICK, TileType.BRICK, TileType.GRAIN, TileType.GRAIN, TileType.GRAIN, TileType.GRAIN,
            TileType.WOOL, TileType.WOOL, TileType.WOOL, TileType.ORE, TileType.ORE, TileType.ORE};


}

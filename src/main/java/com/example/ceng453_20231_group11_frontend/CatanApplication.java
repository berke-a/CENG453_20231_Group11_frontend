package com.example.ceng453_20231_group11_frontend;

import com.example.ceng453_20231_group11_frontend.constants.GeneralConstants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CatanApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent homePage = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(GeneralConstants.HOME_PAGE)));
        Scene scene = new Scene(homePage, GeneralConstants.WINDOW_WIDTH, GeneralConstants.WINDOW_HEIGHT);
        stage.setTitle(GeneralConstants.WINDOW_TITLE);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
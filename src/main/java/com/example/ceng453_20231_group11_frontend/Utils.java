package com.example.ceng453_20231_group11_frontend;

import com.example.ceng453_20231_group11_frontend.constants.GeneralConstants;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Utils {
    public static void routeToPage(ActionEvent event, Parent newPage) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene newScene = new Scene(newPage, GeneralConstants.WINDOW_WIDTH, GeneralConstants.WINDOW_HEIGHT);

        currentStage.setScene(newScene);
        currentStage.show();
    }
}

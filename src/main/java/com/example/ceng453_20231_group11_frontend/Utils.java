package com.example.ceng453_20231_group11_frontend;

import com.example.ceng453_20231_group11_frontend.constants.GeneralConstants;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Utils {


    public static void routeToPage(ActionEvent event, String Page) {
        try {
            Parent newPage = CatanApplication.loadFXML(Page);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent currentRoot = currentStage.getScene().getRoot();
            NavigationHistoryManager.push(currentRoot);
            Scene newScene = new Scene(newPage, GeneralConstants.WINDOW_WIDTH, GeneralConstants.WINDOW_HEIGHT);

            currentStage.setScene(newScene);
            currentStage.show();
        } catch (Exception e) {
            Utils.showAlert(Alert.AlertType.ERROR, "Navigation Error", "Error while navigating.");
        }

    }

    public static void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static Boolean isLoggedIn() {
        return SessionStorage.getInstance().getUsername() != null;
    }

    public static String getUsername() {
        return SessionStorage.getInstance().getUsername();
    }

    public static void logout() {
        SessionStorage.getInstance().clear();
    }
}

package com.example.ceng453_20231_group11_frontend.controller;

import com.example.ceng453_20231_group11_frontend.NavigationHistoryManager;
import com.example.ceng453_20231_group11_frontend.Utils;
import com.example.ceng453_20231_group11_frontend.constants.GeneralConstants;
import com.example.ceng453_20231_group11_frontend.service.UserService;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.application.Platform;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ForgotPasswordController implements Initializable {
    @FXML
    private TextField emailField;

    @FXML
    private Pane pane;

    @FXML
    private ProgressIndicator loader;

    @FXML
    private ImageView backgroundImage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> pane.requestFocus());
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(GeneralConstants.FORGOT_PASSWORD_BACKGROUND_IMAGE)));
        backgroundImage.setImage(image);
    }

    @FXML
    private void onClickSubmitButton(ActionEvent event) {
        String email = emailField.getText();

        // Show the loader
        loader.setVisible(true);

        // Run the password reset request in a background task
        Task<Pair<Integer, String>> task = new Task<Pair<Integer, String>>() {
            @Override
            protected Pair<Integer, String> call() {
                return UserService.requestPasswordReset(email);
            }
        };

        task.setOnSucceeded(e -> {
            // Hide the loader when the task is done
            loader.setVisible(false);

            Pair<Integer, String> response = task.getValue();

            if (response.getKey() == 200) {
                Utils.showAlert(Alert.AlertType.INFORMATION, "Success", "Password reset email sent successfully.");
                Utils.routeToPage(event, GeneralConstants.RESET_PASSWORD_PAGE);
            } else {
                Utils.showAlert(Alert.AlertType.ERROR, "Error", "Failed to send password reset email. " + response.getValue());
            }
        });

        task.setOnFailed(e -> {
            // Hide the loader and handle errors
            loader.setVisible(false);
            Utils.showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while attempting to send the password reset email.");
        });

        // Start the task on a background thread
        new Thread(task).start();
    }

    @FXML
    public void onClickBackButton(ActionEvent event) {
        Parent previousPage = NavigationHistoryManager.pop();
        if (previousPage != null) {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene existingScene = previousPage.getScene();

            if (existingScene == null) {
                existingScene = new Scene(previousPage);
            }

            currentStage.setScene(existingScene);
            currentStage.show();
        }
    }
}

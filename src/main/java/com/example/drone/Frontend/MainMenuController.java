package com.example.drone.Frontend;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void goToDrones() throws IOException {
        navigateTo("/com/example/drone/DronesCatalog.fxml", "Drones");
    }

    @FXML
    public void goToDroneTypes() throws IOException {
        navigateTo("/com/example/drone/DroneTypesCatalog.fxml", "Drone Types");
    }

    @FXML
    public void goToDroneDynamics() throws IOException {
        navigateTo("/com/example/drone/DroneDynamicCatalog.fxml", "Drone Dynamics");
    }

    private void navigateTo(String fxmlPath, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);  // This causes the NullPointerException
        stage.setTitle(title);
        stage.show();

        // Optionally pass the stage to the new scene's controller
        Object controller = fxmlLoader.getController();
        if (controller instanceof MainMenuController nextController) {
            nextController.setStage(stage);  // Make sure this is being done
        }
    }
}

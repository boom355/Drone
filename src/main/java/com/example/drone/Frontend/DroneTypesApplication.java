package com.example.drone.Frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DroneTypesApplication extends Application {

    @Override
    public void start(Stage stage) {
        try {
            // Load the FXML file for Drone Types
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/drone/DroneTypesCatalog.fxml"));
            Parent root = fxmlLoader.load();

            // Create the Scene and set it on the stage
            Scene scene = new Scene(root);
            stage.setTitle("Drone Types Catalog");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error starting Drone Types Application: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

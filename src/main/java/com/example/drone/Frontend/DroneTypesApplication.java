package com.example.drone.Frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DroneTypesApplication extends Application {

    @Override
    public void start(Stage stage) {
        try {
            // Load the FXML file for Drone Types
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/drone/DroneTypesCatalog.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            // Optional: Add a CSS file for styling
            // scene.getStylesheets().add(getClass().getResource("/com/example/drone/styles.css").toExternalForm());

            // Set stage properties and display
            stage.setTitle("Drone Types Catalog");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.err.println("Error starting Drone Types Application: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(); // Start the JavaFX application lifecycle
    }
}

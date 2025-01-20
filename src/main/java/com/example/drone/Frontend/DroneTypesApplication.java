// DroneTypesApplication.java

package com.example.drone.Frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DroneTypesApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load FXML file from the correct path
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/drone/DroneTypesCatalog.fxml"));
            Parent root = loader.load();

            // Set application title and scene
            primaryStage.setTitle("Drone Types");
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.show();
        } catch (Exception e) {
            System.err.println("Error loading FXML file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

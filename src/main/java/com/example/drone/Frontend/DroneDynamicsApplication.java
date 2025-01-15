package com.example.drone.Frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DroneDynamicsApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/drone/DroneDynamicCatalog.fxml"));
        StackPane root = loader.load(); // Use StackPane instead of VBox

        // Set the stage
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Drone Dynamics Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

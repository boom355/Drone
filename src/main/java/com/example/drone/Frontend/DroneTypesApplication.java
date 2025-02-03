package com.example.drone.Frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The entry point of the Drone Types application. This class extends the JavaFX 
 * {@link Application} class and is responsible for launching the Drone Types UI.
 * It loads the FXML file that defines the layout of the Drone Types Catalog view.
 * <p>
 * This application displays a UI that allows users to interact with the Drone Types Catalog.
 * The main window is initialized and displayed upon launching the application.
 * </p>
 *
 * @author [Your Name]
 * @version 1.0
 */
public class DroneTypesApplication extends Application {

    /**
     * Initializes the application by loading the FXML file and displaying the main window.
     * This method sets the title and the scene of the primary stage.
     *
     * @param primaryStage The primary stage for this application, onto which the scene is set.
     *                     This is the main window of the application.
     */
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
            // Print error if FXML file loading fails
            System.err.println("Error loading FXML file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * The main method to launch the JavaFX application.
     * This method is called when the application is executed.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        launch(args);
    }
}

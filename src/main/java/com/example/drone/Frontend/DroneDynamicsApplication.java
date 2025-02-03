package com.example.drone.Frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * This class is the main entry point for the Drone Dynamics application. It extends the JavaFX {@link Application} class
 * and serves as the starting point for the graphical user interface (GUI).
 * <p>
 * The class is responsible for loading the FXML layout file, setting up the primary stage (window),
 * and displaying the user interface of the application.
 * </p>
 *
 * @author [Your Name]
 * @version 1.0
 */
public class DroneDynamicsApplication extends Application {

    /**
     * The entry point to start the JavaFX application.
     * <p>
     * This method loads the FXML layout file, sets the scene, and displays the main window (stage) for the application.
     * </p>
     *
     * @param primaryStage The primary stage (window) for the JavaFX application.
     * @throws Exception If an error occurs while loading the FXML file or setting up the stage.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/drone/DroneDynamicCatalog.fxml"));
        AnchorPane root = loader.load(); // Use AnchorPane for layout

        // Set up the scene
        Scene scene = new Scene(root, 800, 600); // Create a scene with a specified width and height
        primaryStage.setTitle("Drone Dynamics Viewer"); // Set the title of the application window
        primaryStage.setScene(scene); // Set the scene for the primary stage
        primaryStage.show(); // Display the primary stage
    }

    /**
     * The main method that launches the JavaFX application.
     * <p>
     * This method is the entry point of the application and starts the JavaFX runtime environment. It invokes the
     * {@link Application#launch(String[])} method to start the application lifecycle.
     * </p>
     *
     * @param args Command line arguments (not used in this implementation).
     */
    public static void main(String[] args) {
        launch(args); // Launch the JavaFX application
    }
}

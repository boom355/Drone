package com.example.drone.Frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main entry point for the Drone Management application.
 * This class launches the JavaFX application and displays the main window
 * by loading the FXML layout for the Drone Catalog UI.
 * <p>
 * The application is responsible for initializing the UI and setting up
 * the primary stage to display the drone catalog interface.
 * </p>
 *
 * @author [Your Name]
 * @version 1.0
 */
public class DronesApplication extends Application {

    /**
     * The start method is called when the application is launched.
     * It sets up the main stage, loads the FXML layout, and displays the scene.
     *
     * @param stage The primary stage for the application.
     * @throws IOException If the FXML file cannot be loaded or there is an error in loading.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Load the FXML file for the Drone Catalog UI
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/drone/DronesCatalog.fxml"));

        // Create the scene and set it on the stage
        Scene scene = new Scene(fxmlLoader.load());

        // Set the window title and show the stage
        stage.setTitle("Drone Management");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main method to launch the JavaFX application.
     * This method is the entry point of the application when run from the command line.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        launch();
    }
}

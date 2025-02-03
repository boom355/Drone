package com.example.drone.Frontend;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The controller for the Main Menu of the Drone Application.
 * Handles navigation to different parts of the application, such as the Drones Catalog, Drone Types Catalog,
 * and Drone Dynamics Catalog.
 * <p>
 * This class is responsible for managing the navigation from the main menu to various catalogs within the
 * application. It provides methods that trigger the transition between scenes.
 * </p>
 */
public class MainMenuController {

    private Stage stage;

    /**
     * Sets the current stage for the controller.
     * This allows navigation between different scenes within the application.
     *
     * @param stage The current stage (window) to be set for this controller.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Navigates to the Drones Catalog scene.
     * This method is triggered when the user chooses to view the drones catalog.
     * It loads the appropriate FXML file and updates the stage with the new scene.
     *
     * @throws IOException If there is an error loading the FXML file.
     */
    @FXML
    private void goToDrones() throws IOException {
        navigateTo("/com/example/drone/DronesCatalog.fxml", "Drones");
    }

    /**
     * Navigates to the Drone Types Catalog scene.
     * This method is triggered when the user chooses to view the drone types catalog.
     * It loads the appropriate FXML file and updates the stage with the new scene.
     *
     * @throws IOException If there is an error loading the FXML file.
     */
    @FXML
    public void goToDroneTypes() throws IOException {
        navigateTo("/com/example/drone/DroneTypesCatalog.fxml", "Drone Types");
    }

    /**
     * Navigates to the Drone Dynamics Catalog scene.
     * This method is triggered when the user chooses to view the drone dynamics catalog.
     * It loads the appropriate FXML file and updates the stage with the new scene.
     *
     * @throws IOException If there is an error loading the FXML file.
     */
    @FXML
    public void goToDroneDynamics() throws IOException {
        navigateTo("/com/example/drone/DroneDynamicCatalog.fxml", "Drone Dynamics");
    }

    /**
     * A private helper method that handles the navigation to a new scene.
     * It loads the FXML file for the new scene, sets the scene on the stage, and updates the title of the stage.
     *
     * @param fxmlPath The path to the FXML file to load.
     * @param title    The title to set for the new scene's window.
     * @throws IOException If there is an error loading the FXML file.
     */
    private void navigateTo(String fxmlPath, String title) throws IOException {
        // Load the FXML file for the new scene
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = fxmlLoader.load();

        // Create a new scene and set it on the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();

        // Set the current stage for the controller of the new scene (if applicable)
        Object controller = fxmlLoader.getController();
        if (controller instanceof MainMenuController nextController) {
            nextController.setStage(stage);
        }
    }
}

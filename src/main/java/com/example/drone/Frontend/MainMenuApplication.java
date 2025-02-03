package com.example.drone.Frontend;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import com.example.drone.Backend.Drones;
import com.example.drone.Backend.DronesEntry;

/**
 * The main application class that starts the Drone Application with the Main Menu.
 * It loads the Main Menu UI and fetches drone data asynchronously in the background.
 * <p>
 * This class is responsible for setting up the main window of the application by loading
 * the FXML for the Main Menu and initiating the background task to fetch drone data.
 * </p>
 */
public class MainMenuApplication extends Application {

    /**
     * The entry point for launching the Main Menu of the Drone Application.
     * This method loads the FXML for the Main Menu, sets up the stage, and starts the background task
     * to fetch drone data.
     *
     * @param stage The primary stage for this application, onto which the scene will be set.
     * @throws IOException If there is an error loading the FXML file for the Main Menu.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Load the Main Menu FXML
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenuApplication.class.getResource("/com/example/drone/MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        // Pass the stage to the MainMenuController
        MainMenuController controller = fxmlLoader.getController();
        controller.setStage(stage);

        // Fetch data asynchronously when the application starts
        fetchDataInBackground();

        // Set the scene and show the stage
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Fetches drone data in the background using a separate thread.
     * The task fetches data asynchronously using the `Drones` class.
     * Once the task completes successfully, it processes the fetched drone data.
     * In case of failure, it logs the error message.
     */
    private void fetchDataInBackground() {
        // Task for fetching drone data in a separate thread
        Task<List<DronesEntry>> task = new Task<>() {
            @Override
            protected List<DronesEntry> call() {
                // Fetch data using Drones class
                Drones drones = new Drones();
                return drones.fetchDrones();
            }
        };

        // Set what happens when the task is successful
        task.setOnSucceeded(_ -> { // Use '_' for unused parameter
            List<DronesEntry> dronesList = task.getValue();
            System.out.println("Fetched " + dronesList.size() + " drones.");
            // Optionally, pass data to the controller for UI updates
        });

        // Set what happens if the task fails
        task.setOnFailed(_ -> { // Use '_' for unused parameter
            Throwable error = task.getException();
            System.err.println("Error fetching drone data: " + error.getMessage());
        });

        // Start the task in a new thread
        Thread thread = new Thread(task);
        thread.setDaemon(true); // Ensures the thread terminates when the application exits
        thread.start();
    }

    /**
     * The main method for the Drone Application.
     * It launches the application by invoking the `launch()` method of the JavaFX `Application` class.
     *
     * @param args The command-line arguments passed to the application (not used in this case).
     */
    public static void main(String[] args) {
        launch();
    }
}

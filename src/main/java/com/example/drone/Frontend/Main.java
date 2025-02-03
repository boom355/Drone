package com.example.drone.Frontend;

import com.example.drone.Backend.Api;

/**
 * The main entry point for launching the Drone Application.
 * This class contains the `main` method which starts the application by launching the MainMenuApplication.
 * <p>
 * The class starts a new thread to handle fetching data from an API endpoint and saving it to a file.
 * It also launches the main JavaFX application for the Main Menu.
 * </p>
 *
 * @author [Your Name]
 * @version 1.0
 */
public class Main {

    /**
     * The main method for the Drone Application. It is the entry point of the application.
     * It prints a message to the console and then launches the JavaFX application for the Main Menu.
     * <p>
     * This method starts a background thread to fetch data from an API and process the response.
     * It then launches the JavaFX application to display the main menu interface.
     * </p>
     *
     * @param args The command-line arguments passed to the application (not used in this case).
     */
    public static void main(String[] args) {
        // Start a new thread to handle fetching data and saving it to file
        Thread apiThread = new Thread(() -> {
            try {
                // Fetch data from API endpoint and print the response
                String response = Api.fetchData("http://dronesim.facets-labs.com/");
                System.out.println("API Response: " + response);

                // Read past responses from the file and print them
                String storedResponses = Api.readFromFile();
                System.out.println("Stored API Responses:\n" + storedResponses);
            } catch (Exception e) {
                // Print error if something goes wrong during API interaction
                System.err.println("Error: " + e.getMessage());
            }
        });

        // Start the thread
        apiThread.start();

        // Launch the JavaFX application in the main thread
        System.out.println("Launching Drone Application...");
        MainMenuApplication.launch(MainMenuApplication.class, args);

        System.out.println("Drone Application closed.");
    }
}

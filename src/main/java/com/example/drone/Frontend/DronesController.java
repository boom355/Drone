package com.example.drone.Frontend;

import com.example.drone.Backend.Drones;
import com.example.drone.Backend.DronesEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * Controller for managing the Drone Catalog view. This class handles
 * the interaction between the user interface and the backend data for drones.
 * It populates a table with drone data and handles navigation and refresh actions.
 * <p>
 * The controller is responsible for initializing the drone data table, loading
 * the data from the backend, and handling events such as refreshing the table
 * and navigating to the home menu.
 * </p>
 *
 * @author [Your Name]
 * @version 1.0
 */
public class DronesController {

    // FXML components
    @FXML
    private TableView<DronesEntry> droneTable;

    @FXML
    private TableColumn<DronesEntry, String> idColumn;

    @FXML
    private TableColumn<DronesEntry, String> dronetypeColumn;

    @FXML
    private TableColumn<DronesEntry, String> createdDateColumn;

    @FXML
    private TableColumn<DronesEntry, String> serialnumberColumn;

    @FXML
    private TableColumn<DronesEntry, Double> carriageWeightColumn;

    @FXML
    private TableColumn<DronesEntry, String> carriageTypeColumn;

    @FXML
    private TableColumn<DronesEntry, Double> averageSpeedColumn;

    @FXML
    private TableColumn<DronesEntry, Double> totalDistanceColumn;

    // Backend connection
    private final Drones dronesBackend = new Drones();

    /**
     * Initializes the table columns by binding them to the respective fields
     * in the DronesEntry class. This method is invoked automatically when the
     * FXML file is loaded and the controller is initialized.
     */
    @FXML
    public void initialize() {
        // Bind table columns to DronesEntry fields
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dronetypeColumn.setCellValueFactory(new PropertyValueFactory<>("droneType"));
        createdDateColumn.setCellValueFactory(new PropertyValueFactory<>("createdDate"));
        serialnumberColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        carriageWeightColumn.setCellValueFactory(new PropertyValueFactory<>("carriageWeight"));
        carriageTypeColumn.setCellValueFactory(new PropertyValueFactory<>("carriageType"));
        averageSpeedColumn.setCellValueFactory(new PropertyValueFactory<>("averageSpeed"));
        totalDistanceColumn.setCellValueFactory(new PropertyValueFactory<>("totalDistance"));
    }

    /**
     * Refreshes the data displayed in the drone table by re-loading the drones.
     * This method is typically invoked when the user clicks a refresh button.
     * It fetches the latest data and updates the table.
     */
    @FXML
    public void refreshButton() {
        loadDrones(); // Re-load the data by calling the loadDrones method
    }

    /**
     * Handles the "Home" button click event. This method navigates to the main menu view.
     * When the user clicks the "Home" button, the application transitions to the main menu.
     */
    @FXML
    private void onHomeClicked() {
        try {
            // Load the Main Menu FXML (adjust the path if necessary)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/drone/MainMenu.fxml"));
            Parent root = loader.load();

            // Get the current stage (the window)
            Stage stage = (Stage) droneTable.getScene().getWindow();

            // Set the new scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Main Menu"); // Optional: Set the title for the main menu window

            // If needed, set the stage for the controller of the main menu
            MainMenuController controller = loader.getController();
            controller.setStage(stage); // If you have a controller for MainMenu

            // Show the stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the drone data from the backend and populates the table with the data.
     * This method is invoked to fetch the latest list of drones and update the table
     * with the new data.
     */
    @FXML
    public void loadDrones() {
        // Fetch the drones data
        List<DronesEntry> drones = dronesBackend.fetchDrones();

        // Populate the table with data
        ObservableList<DronesEntry> droneData = FXCollections.observableArrayList(drones);
        droneTable.setItems(droneData);
    }
}

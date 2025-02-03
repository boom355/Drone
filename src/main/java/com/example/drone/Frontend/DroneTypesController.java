package com.example.drone.Frontend;

import com.example.drone.Backend.DroneTypesEntry;
import com.example.drone.Backend.DroneTypes;
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
 * Controller for managing the Drone Types catalog. This class is responsible for handling the user
 * interactions related to displaying and managing drone types in the application.
 * It communicates with the backend to fetch drone types and display them in a TableView.
 * <p>
 * The controller uses the DroneTypes backend service to fetch data and populate the UI components
 * like the TableView with drone types. It also handles navigation and refresh operations for the user.
 * </p>
 *
 * @author [Your Name]
 * @version 1.0
 */
public class DroneTypesController {

    // FXML components
    @FXML
    private TableView<DroneTypesEntry> droneTable;

    @FXML
    private TableColumn<DroneTypesEntry, String> idColumn;

    @FXML
    private TableColumn<DroneTypesEntry, String> manufacturerColumn;

    @FXML
    private TableColumn<DroneTypesEntry, String> typenameColumn;

    @FXML
    private TableColumn<DroneTypesEntry, Double> weightColumn;

    @FXML
    private TableColumn<DroneTypesEntry, Double> maxSpeedColumn;

    @FXML
    private TableColumn<DroneTypesEntry, Double> batteryCapacityColumn;

    @FXML
    private TableColumn<DroneTypesEntry, Double> controlRangeColumn;

    @FXML
    private TableColumn<DroneTypesEntry, Double> maxCarriageColumn;

    // Backend connection
    private final DroneTypes droneTypesBackend = new DroneTypes();

    /**
     * Initializes the controller by binding the table columns to the properties
     * of the DroneTypesEntry objects.
     * <p>
     * This method is called when the FXML is loaded and ensures that the TableView
     * columns are properly connected to the fields of the DroneTypesEntry class.
     * </p>
     */
    @FXML
    public void initialize() {
        // Bind table columns to DroneTypesEntry fields
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        manufacturerColumn.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        typenameColumn.setCellValueFactory(new PropertyValueFactory<>("typename"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        maxSpeedColumn.setCellValueFactory(new PropertyValueFactory<>("maxSpeed"));
        batteryCapacityColumn.setCellValueFactory(new PropertyValueFactory<>("batteryCapacity"));
        controlRangeColumn.setCellValueFactory(new PropertyValueFactory<>("controlRange"));
        maxCarriageColumn.setCellValueFactory(new PropertyValueFactory<>("maxCarriage"));
    }

    /**
     * Refreshes the table by calling the loadDrones() method to reload the drone data.
     * <p>
     * This method is typically invoked when the user clicks a refresh button. It reloads
     * the drone data from the backend and updates the table.
     * </p>
     */
    @FXML
    public void refreshButton() {
        loadDrones(); // Re-load the data by calling the loadDrones method
    }

    /**
     * Handles the action when the "Home" button is clicked. It loads the main menu scene.
     * <p>
     * This method is responsible for navigating to the main menu of the application
     * when the user clicks the "Home" button.
     * </p>
     *
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
     * Loads the drone types data from the backend and populates the table with the fetched data.
     * <p>
     * This method communicates with the backend service to retrieve the list of drone types
     * and then populates the TableView with the fetched data.
     * </p>
     */
    @FXML
    public void loadDrones() {
        try {
            // Fetch list of drone types from backend
            List<DroneTypesEntry> droneTypesList = droneTypesBackend.fetchDroneTypes();
            ObservableList<DroneTypesEntry> observableDroneList = FXCollections.observableArrayList(droneTypesList);

            // Populate the table with data
            droneTable.setItems(observableDroneList);
        } catch (Exception e) {
            System.err.println("Error loading drone types: " + e.getMessage());
        }
    }
}

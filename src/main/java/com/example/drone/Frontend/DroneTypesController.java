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

public class DroneTypesController {

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

    private final DroneTypes droneTypesBackend = new DroneTypes();

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

    @FXML
    public void refreshButton() {
        loadDrones(); // Re-load the data by calling the loadDrones method
    }

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

    @FXML
    public void loadDrones() {
        try {
            // Fetch list of drones from backend
            List<DroneTypesEntry> droneTypesList = droneTypesBackend.fetchDroneTypes();
            ObservableList<DroneTypesEntry> observableDroneList = FXCollections.observableArrayList(droneTypesList);

            // Populate the table with data
            droneTable.setItems(observableDroneList);
        } catch (Exception e) {
            System.err.println("Error loading drone types: " + e.getMessage());
        }
    }
}

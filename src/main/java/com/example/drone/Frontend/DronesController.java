package com.example.drone.Frontend;

import com.example.drone.Backend.DroneDynamicEntry;
import com.example.drone.Backend.Drones;
import com.example.drone.Backend.DroneDynamics;
import com.example.drone.Backend.DronesEntry;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.util.List;

public class DronesController {

    @FXML
    private ListView<DronesEntry> dronesListView;

    @FXML
    private TextArea droneDynamicsDisplay;

    private final Drones dronesService = new Drones();
    private final DroneDynamics dynamicsService = new DroneDynamics();

    // Load drones and populate the ListView
    @FXML
    public void loadDrones() {
        try {
            List<DronesEntry> dronesList = dronesService.fetchDrones();
            dronesListView.getItems().setAll(dronesList);
            droneDynamicsDisplay.setText("Drones loaded successfully.");
        } catch (Exception e) {
            droneDynamicsDisplay.setText("Error loading drones: " + e.getMessage());
        }
    }

    // Display drone dynamics when a drone is selected
    @FXML
    public void onDroneSelected() {
        DronesEntry selectedDrone = dronesListView.getSelectionModel().getSelectedItem();
        if (selectedDrone != null) {
            String droneId = selectedDrone.getId();
            displayDroneDynamics(droneId);
        } else {
            droneDynamicsDisplay.setText("No drone selected.");
        }
    }

    // Fetch and display drone dynamics for the selected drone
    private void displayDroneDynamics(String droneId) {
        try {
            List<DroneDynamicEntry> dynamics = dynamicsService.fetchDroneDataById(droneId);
            StringBuilder displayText = new StringBuilder();

            for (DroneDynamicEntry entry : dynamics) {
                displayText.append("Timestamp: ").append(entry.getTimestamp()).append("\n")
                        .append("Speed: ").append(entry.getSpeed()).append(" km/h\n")
                        .append("align_roll: ").append(entry.getAlignRoll()).append(")\n")
                        .append("controlRange: ").append(entry.getControlRange()).append(")\n")
                        .append("align_yaw: ").append(entry.getAlignYaw()).append(")\n")
                        .append("Longitude: ").append(entry.getLongitude()).append(")\n")
                        .append("Latitude: ").append(entry.getLongitude()).append(")\n")
                        .append("BatteryStatus: ").append(entry.getBatteryStatus()).append("\n\n")
                        .append("Status: ").append(entry.getStatus()).append("\n\n")
                        .append("LastSeen: ").append(entry.getLastSeen()).append("\n\n");




            }

            if (displayText.isEmpty()) {
                droneDynamicsDisplay.setText("No dynamics data available for this drone.");
            } else {
                droneDynamicsDisplay.setText(displayText.toString());
            }
        } catch (Exception e) {
            droneDynamicsDisplay.setText("Failed to fetch drone dynamics: " + e.getMessage());
        }
    }
}

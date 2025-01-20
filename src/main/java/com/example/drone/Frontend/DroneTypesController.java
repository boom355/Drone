package com.example.drone.Frontend;

import com.example.drone.Backend.DroneDynamicEntry;
import com.example.drone.Backend.DroneDynamics;
import com.example.drone.Backend.DroneTypes;
import com.example.drone.Backend.DroneTypesEntry;
import com.example.drone.Backend.DronesEntry;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.util.List;

public class DroneTypesController {

    @FXML
    private ListView<DroneTypesEntry> droneTypesListView; // ListView for drone types
    @FXML
    private ListView<DronesEntry> droneListView; // ListView for drones of selected type
    @FXML
    private TextArea droneDynamicsDisplay; // TextArea to display drone dynamics

    private final DroneTypes droneTypesService = new DroneTypes(); // Service to fetch drone types
    private final DroneDynamics droneDynamicsService = new DroneDynamics(); // Service to fetch drone dynamics

    @FXML
    public void loadDroneTypes() {
        try {
            // Fetch drone types and populate the ListView
            List<DroneTypesEntry> droneTypes = droneTypesService.fetchDroneTypes();
            droneTypesListView.getItems().setAll(droneTypes);
        } catch (Exception e) {
            droneDynamicsDisplay.setText("Error loading drone types: " + e.getMessage());
        }
    }

    @FXML
    public void onDroneTypeSelected() {
        DroneTypesEntry selectedDroneType = droneTypesListView.getSelectionModel().getSelectedItem();

        if (selectedDroneType != null) {
            try {
                // Fetch drones for the selected drone type
                List<DronesEntry> drones = droneTypesService.fetchDronesByType(selectedDroneType.getId());
                droneListView.getItems().setAll(drones);
            } catch (Exception e) {
                droneDynamicsDisplay.setText("Error fetching drones: " + e.getMessage());
            }
        } else {
            droneDynamicsDisplay.setText("Please select a drone type.");
        }
    }

    @FXML
    public void onDroneSelected() {
        DronesEntry selectedDrone = droneListView.getSelectionModel().getSelectedItem();

        if (selectedDrone != null) {
            try {
                // Fetch and display drone dynamics
                displayDroneDynamics(selectedDrone.getId());
            } catch (Exception e) {
                droneDynamicsDisplay.setText("Error fetching drone dynamics: " + e.getMessage());
            }
        } else {
            droneDynamicsDisplay.setText("Please select a drone.");
        }
    }

    private void displayDroneDynamics(String droneId) {
        try {
            // Fetch dynamic data for the selected drone
            List<DroneDynamicEntry> dynamics = droneDynamicsService.fetchDroneDataById(droneId);

            StringBuilder displayText = new StringBuilder();
            for (DroneDynamicEntry entry : dynamics) {
                displayText.append("Timestamp: ").append(entry.getTimestamp()).append("\n")
                        .append("Speed: ").append(entry.getSpeed()).append(" km/h\n")
                        .append("Align Roll: ").append(entry.getAlignRoll()).append("\n")
                        .append("Align Yaw: ").append(entry.getAlignYaw()).append("\n")
                        .append("Longitude: ").append(entry.getLongitude()).append("\n")
                        .append("Latitude: ").append(entry.getLatitude()).append("\n")
                        .append("Battery: ").append(entry.getBatteryStatus()).append("%\n")
                        .append("Last Seen: ").append(entry.getLastSeen()).append("\n")
                        .append("Control Range: ").append(entry.getControlRange()).append(" meters\n")
                        .append("Status: ").append(entry.getStatus()).append("\n\n");
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

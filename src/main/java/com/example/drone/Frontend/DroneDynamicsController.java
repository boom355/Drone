package com.example.drone.Frontend;

import com.example.drone.Backend.DroneDynamicEntry;
import com.example.drone.Backend.DroneDynamics;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;

public class DroneDynamicsController {

    @FXML
    private TextField droneIdInput;

    @FXML
    private TextArea droneDataDisplay;

    private final DroneDynamics droneDynamics = new DroneDynamics();

    @FXML
    private void onFetchDataClicked() {
        String droneId = droneIdInput.getText().trim();

        if (droneId.isEmpty()) {
            droneDataDisplay.setText("Please enter a valid Drone ID.");
            return;
        }

        try {
            List<DroneDynamicEntry> dynamicsData = droneDynamics.fetchDroneDataById(droneId);

            if (dynamicsData.isEmpty()) {
                droneDataDisplay.setText("No data found for Drone ID: " + droneId);
            } else {
                StringBuilder displayText = new StringBuilder();
                for (DroneDynamicEntry entry : dynamicsData) {
                    displayText.append("Timestamp: ").append(entry.getTimestamp()).append("\n")
                            .append("Speed: ").append(entry.getSpeed()).append(" km/h\n")
                            .append("Longitude: ").append(entry.getLongitude()).append("\n")
                            .append("Latitude: ").append(entry.getLatitude()).append("\n")
                            .append("Battery: ").append(entry.getBatteryStatus()).append("%\n")
                            .append("Status: ").append(entry.getStatus()).append("\n\n");
                }
                droneDataDisplay.setText(displayText.toString());
            }
        } catch (Exception e) {
            droneDataDisplay.setText("Error fetching drone data: " + e.getMessage());
        }
    }
}

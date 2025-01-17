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
            // Fetch the drone dynamics data by drone ID
            List<DroneDynamicEntry> dynamicsData = droneDynamics.fetchDroneDataById(droneId);

            if (dynamicsData.isEmpty()) {
                droneDataDisplay.setText("No data found for Drone ID: " + droneId);
            } else {
                StringBuilder displayText = new StringBuilder();

                // Iterate through the retrieved drone data and display it
                for (DroneDynamicEntry entry : dynamicsData) {
                    // Normalize battery status
                    int batteryStatus = entry.getBatteryStatus();
                    double normalizedBatteryStatus = (batteryStatus / 42.8);  // Adjust this value based on your data

                    // If controlRange or yaw is missing, use default value
                    double controlRange = entry.getControlRange() != 0.0 ? entry.getControlRange() : 0.0;
                    double yaw = entry.getAlignYaw() != 0.0 ? entry.getAlignYaw() : 0.0;
                    double roll = entry.getAlignRoll() != 0.0 ? entry.getAlignRoll() : 0.0;

                    // Display data
                    displayText.append("Timestamp: ").append(entry.getTimestamp()).append("\n")
                            .append("Speed: ").append(entry.getSpeed()).append(" km/h\n")
                            .append("align_roll: ").append(roll).append("\n")
                            .append("Control Range: ").append(controlRange).append(" meters\n")
                            .append("align_yaw: ").append(yaw).append("\n")
                            .append("Longitude: ").append(entry.getLongitude()).append("\n")
                            .append("Latitude: ").append(entry.getLatitude()).append("\n")
                            .append("Battery: ").append(normalizedBatteryStatus).append("%\n")
                            .append("Last Seen: ").append(entry.getLastSeen()).append("\n")
                            .append("Status: ").append(entry.getStatus()).append("\n\n");
                }

                // Set the formatted text in the TextArea
                droneDataDisplay.setText(displayText.toString());
            }
        } catch (Exception e) {
            // Display error message in case of any exception
            droneDataDisplay.setText("Error fetching drone data: " + e.getMessage());
        }
    }
}

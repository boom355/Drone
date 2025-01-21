package com.example.drone.Frontend;

import com.example.drone.Backend.DroneDynamicEntry;
import com.example.drone.Backend.DroneDynamics;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;

public class DroneDynamicsController {

    @FXML
    private TextField droneIdField;

    @FXML
    private ListView<String> dynamicsListView;

    private final DroneDynamics droneDynamics = new DroneDynamics();

    @FXML
    private void fetchDynamics() {
        String droneId = droneIdField.getText().trim();

        if (droneId.isEmpty()) {
            dynamicsListView.getItems().add("Please enter a drone ID.");
            return;
        }

        try {
            List<DroneDynamicEntry> dynamics = droneDynamics.fetchDroneDataById(droneId);
            ObservableList<String> dynamicsData = FXCollections.observableArrayList();
            if (dynamics.isEmpty()) {
                dynamicsListView.getItems().add("No dynamics found for drone ID: " + droneId);
            } else {
                for (DroneDynamicEntry entry : dynamics) {
                    dynamicsData.add("Timestamp: " + entry.getTimestamp() +
                            ", Speed: " + entry.getSpeed() +
                            ", Battery: " + entry.getBatteryStatus() +
                            ", Align Roll: " + entry.getAlignRoll() +
                            ", Align controlRange: " + entry.getControlRange() +
                            ", Align Yaw: " + entry.getAlignYaw() +
                            ", Longitude: " + entry.getLongitude() +
                            ", Latitude: " + entry.getLatitude() +
                            ", Status: " + entry.getStatus());
                }
                dynamicsListView.setItems(dynamicsData);
            }
        } catch (Exception e) {
            dynamicsListView.getItems().add("Error fetching dynamics for drone ID: " + droneId);
            e.printStackTrace(); // Print the error for debugging
        }
    }
    }


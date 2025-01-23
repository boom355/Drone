package com.example.drone.Frontend;

import com.example.drone.Backend.DroneDynamics;
import com.example.drone.Backend.DroneDynamicEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class DroneDynamicsController {

    @FXML
    private TableView<DroneDynamicEntry> droneTable;

    @FXML
    private TableColumn<DroneDynamicEntry, String> timestampColumn;

    @FXML
    private TableColumn<DroneDynamicEntry, Integer> speedColumn;

    @FXML
    private TableColumn<DroneDynamicEntry, Double> rollColumn;

    @FXML
    private TableColumn<DroneDynamicEntry, Double> yawColumn;

    @FXML
    private TableColumn<DroneDynamicEntry, Double> longitudeColumn;

    @FXML
    private TableColumn<DroneDynamicEntry, Double> latitudeColumn;

    @FXML
    private TableColumn<DroneDynamicEntry, Integer> batteryColumn;

    @FXML
    private TableColumn<DroneDynamicEntry, String> statusColumn;

    @FXML
    private TableColumn<DroneDynamicEntry, String> lastSeenColumn;

    @FXML
    private TableColumn<DroneDynamicEntry, Double> rangeColumn;

    @FXML
    private Button previousButton;

    @FXML
    private Button nextButton;

    private final DroneDynamics droneDynamics = new DroneDynamics();
    private ObservableList<DroneDynamicEntry> droneEntries = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Bind the TableView columns to the DroneDynamicEntry properties
        timestampColumn.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        speedColumn.setCellValueFactory(new PropertyValueFactory<>("speed"));
        rollColumn.setCellValueFactory(new PropertyValueFactory<>("alignRoll"));
        yawColumn.setCellValueFactory(new PropertyValueFactory<>("alignYaw"));
        longitudeColumn.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        latitudeColumn.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        batteryColumn.setCellValueFactory(new PropertyValueFactory<>("batteryStatus"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        lastSeenColumn.setCellValueFactory(new PropertyValueFactory<>("lastSeen"));
        rangeColumn.setCellValueFactory(new PropertyValueFactory<>("controlRange"));

        // Load the first page of drone data
        loadDroneData(null);

        // Disable the previous button initially
        previousButton.setDisable(true);
    }

    @FXML
    private void onNextClicked(ActionEvent event) {
        try {
            String nextPageUrl = droneDynamics.getNextPage();
            if (nextPageUrl != null) {
                loadDroneData(nextPageUrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onPreviousClicked(ActionEvent event) {
        try {
            String previousPageUrl = droneDynamics.getPreviousPage();
            if (previousPageUrl != null) {
                loadDroneData(previousPageUrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDroneData(String pageUrl) {
        try {
            // Fetch data using DroneDynamics
            List<DroneDynamicEntry> droneData = droneDynamics.fetchDroneData(pageUrl);
            droneEntries = FXCollections.observableArrayList(droneData);
            droneTable.setItems(droneEntries);

            // Update button states
            previousButton.setDisable(droneDynamics.getPreviousPage() == null);
            nextButton.setDisable(droneDynamics.getNextPage() == null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

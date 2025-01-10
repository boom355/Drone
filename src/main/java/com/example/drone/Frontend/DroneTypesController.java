package com.example.drone.Frontend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import com.example.drone.Backend.DroneTypes;

public class DroneTypesController {

    @FXML
    private ListView<String> droneTypesListView;

    @FXML
    public void initialize() {
        // Fetch data from the backend and populate the ListView
        DroneTypes droneTypesBackend = new DroneTypes();
        ObservableList<String> droneTypes = FXCollections.observableArrayList(droneTypesBackend.fetchDroneTypes());
        droneTypesListView.setItems(droneTypes);
    }
}

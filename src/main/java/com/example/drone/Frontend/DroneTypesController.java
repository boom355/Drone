package com.example.drone.Frontend;

import com.example.drone.Backend.DronesEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import com.example.drone.Backend.DroneTypes;

import java.util.List;

public class DroneTypesController {

    @FXML
    private ListView<String> droneTypesListView;

    private final DroneTypes droneTypesBackend = new DroneTypes();

    @FXML
    public void loadDrones() {
        List<String> drones = droneTypesBackend.fetchDroneTypes();
        ObservableList<String> droneDetails = FXCollections.observableArrayList(drones);
        droneTypesListView.setItems(droneDetails);
    }



}
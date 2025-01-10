package com.example.drone.Frontend;

import com.example.drone.Backend.Drones;
import com.example.drone.Backend.DronesEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

public class DronesController {

    @FXML
    private ListView<String> dronesListView;

    private final Drones dronesBackend = new Drones();

    @FXML
    public void loadDrones() {
        List<DronesEntry> drones = dronesBackend.fetchDrones();
        ObservableList<String> droneDetails = FXCollections.observableArrayList();

        for (DronesEntry drone : drones) {
            droneDetails.add(drone.toString());
        }

        dronesListView.setItems(droneDetails);
    }
}

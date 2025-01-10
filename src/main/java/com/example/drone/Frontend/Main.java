package com.example.drone.Frontend;

import com.example.drone.Backend.DroneDynamics;
import com.example.drone.Backend.DroneTypes;
import com.example.drone.Backend.Drones;

public class Main {
    public static void main(String[] args) {
        System.out.println("Drone application started.");

        // Initialize DroneCatalog
        DroneTypes droneCatalog = new DroneTypes();
        droneCatalog.fetchDroneTypes(); // Display drone catalog

        // Initialize DroneManager for individual drones
        Drones droneManager = new Drones();
        droneManager.fetchDrones(); // Display individual drones

        DroneDynamics droneDynamics = new DroneDynamics();
        droneDynamics.fetchAndDisplay();

        System.out.println("Drone application finished.");
    }
}

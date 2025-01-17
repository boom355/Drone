package com.example.drone.Frontend;

import com.example.drone.Backend.DroneDynamicEntry;
import com.example.drone.Backend.DroneDynamics;
import com.example.drone.Backend.DroneTypes;
import com.example.drone.Backend.Drones;

import java.util.List;
import java.util.concurrent.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Drone application started.");

        // Use try-with-resources to ensure the ExecutorService is properly shut down
        try (ExecutorService executorService = Executors.newFixedThreadPool(3)) { // 3 threads for concurrent tasks
            // Get the list of tasks to be executed
            List<Callable<Void>> tasks = createTasks();

            // Execute tasks concurrently
            List<Future<Void>> futures = executorService.invokeAll(tasks);

            // Wait for all tasks to complete and handle exceptions if any
            for (Future<Void> future : futures) {
                try {
                    future.get(); // Wait for the task to complete
                } catch (InterruptedException | ExecutionException e) {
                    System.err.println("Error during task execution: " + e.getMessage());
                }
            }

            System.out.println("Drone application finished.");
        } catch (InterruptedException e) {
            System.err.println("Error during task execution: " + e.getMessage());
        }
    }

    // Extracted method to create tasks
    private static List<Callable<Void>> createTasks() {
        // Create tasks for fetching different data
        Callable<Void> fetchDroneTypesTask = () -> {
            try {
                DroneTypes droneCatalog = new DroneTypes();
                droneCatalog.fetchDroneTypes(); // Display drone catalog
            } catch (Exception e) {
                System.err.println("Error fetching drone types: " + e.getMessage());
            }
            return null;
        };

        Callable<Void> fetchDronesTask = () -> {
            try {
                Drones droneManager = new Drones();
                droneManager.fetchDrones(); // Display individual drones
            } catch (Exception e) {
                System.err.println("Error fetching drones: " + e.getMessage());
            }
            return null;
        };

        Callable<Void> fetchDroneDynamicsTask = () -> {
            try {
                DroneDynamics droneDynamics = new DroneDynamics();

                // Ask the user for the drone ID
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter the drone ID to fetch its dynamics: ");
                String droneId = scanner.nextLine();

                // Fetch dynamic data for the specific drone
                List<DroneDynamicEntry> droneDynamicsList = droneDynamics.fetchDroneDataById(droneId);

                // Print the drone dynamics data (you can update this to display on GUI)
                if (droneDynamicsList.isEmpty()) {
                    System.out.println("No data found for drone ID " + droneId);
                } else {
                    droneDynamicsList.forEach(System.out::println); // Display each drone dynamic entry
                }
            } catch (Exception e) {
                System.err.println("Error fetching drone dynamics: " + e.getMessage());
            }
            return null;
        };

        return List.of(fetchDroneTypesTask, fetchDronesTask, fetchDroneDynamicsTask);
    }
}
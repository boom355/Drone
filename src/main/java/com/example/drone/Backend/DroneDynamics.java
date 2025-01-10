package com.example.drone.Backend;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DroneDynamics {
    private static final String BASE_URL = "http://dronesim.facets-labs.com/api/dronedynamics/?format=json";

    public void fetchAndDisplay() {
        try {
            int idCounter = 285482; // Start ID
            String url = BASE_URL;
            Scanner scanner = new Scanner(System.in);

            while (url != null) {
                // Fetch data for the current page
                String response = Api.fetchData(url);
                JSONObject responseObject = new JSONObject(response);

                // Process the current page's drones
                List<DroneDynamicEntry> drones = processDynamicsData(responseObject, idCounter);

                // Increment the ID counter
                idCounter += drones.size();

                // Display the drones for this page
                displayDroneDynamics(drones);

                // Prompt user for pagination
                String nextPage = responseObject.optString("next", null);
                if (nextPage != null) {
                    System.out.println("\nPress Enter to load the next page or type 'exit' to stop...");
                    String input = scanner.nextLine();
                    if ("exit".equalsIgnoreCase(input.trim())) {
                        break;
                    }
                }
                url = nextPage; // Move to the next page
            }

            scanner.close();
        } catch (Exception e) {
            System.err.println("Error fetching or parsing drone data: " + e.getMessage());
        }
    }

    private List<DroneDynamicEntry> processDynamicsData(JSONObject responseObject, int idStart) {
        List<DroneDynamicEntry> droneList = new ArrayList<>();
        JSONArray dronesArray = responseObject.getJSONArray("results");

        for (int i = 0; i < dronesArray.length(); i++) {
            JSONObject droneObject = dronesArray.getJSONObject(i);

            try {
                // Fetch detailed drone data
                String droneUrl = droneObject.optString("drone");
                String detailedDroneData = Api.fetchData(droneUrl); // Fetch details from the drone URL
                JSONObject detailedDroneObject = new JSONObject(detailedDroneData);

                // Extract drone details
                String serialNumber = detailedDroneObject.optString("serialnumber", "Unknown");
                String created = detailedDroneObject.optString("created", "Unknown");
                String droneName = String.format("Drone: %s (created: %s)", serialNumber, created);

                // Extract dynamic data
                int droneId = idStart + i; // Incremental ID
                String timestamp = droneObject.optString("timestamp", "Unknown");
                int speed = droneObject.optInt("speed", 0);
                double alignmentRoll = droneObject.optDouble("align_roll", 0.0);
                double alignmentYaw = droneObject.optDouble("align_yaw", 0.0);
                double longitude = droneObject.optDouble("longitude", 0.0);
                double latitude = droneObject.optDouble("latitude", 0.0);
                int batteryStatus = droneObject.optInt("battery_status", 0);
                String lastSeen = droneObject.optString("last_seen", "Unknown");
                String status = droneObject.optString("status", "Unknown");

                // Create the drone entry
                DroneDynamicEntry droneEntry = new DroneDynamicEntry(
                        String.valueOf(droneId), timestamp, droneName, speed, alignmentRoll, 0.0,
                        alignmentYaw, longitude, latitude, batteryStatus, lastSeen, status
                );
                droneList.add(droneEntry);
            } catch (Exception e) {
                System.err.println("Error processing drone dynamics data: " + e.getMessage());
            }
        }

        return droneList;
    }

    private void displayDroneDynamics(List<DroneDynamicEntry> drones) {
        if (drones.isEmpty()) {
            System.out.println("No drone dynamics data found.");
            return;
        }

        System.out.println("Drone Dynamics:");
        for (DroneDynamicEntry drone : drones) {
            System.out.println(drone); // Relies on DroneDynamicEntry.toString()
        }
    }
}


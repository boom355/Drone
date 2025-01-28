package com.example.drone.Backend;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DroneTypes {
    private static final String BASE_URL = "http://dronesim.facets-labs.com/api/dronetypes/?format=json";

    // Fetches a list of DroneTypesEntry objects
    public List<DroneTypesEntry> fetchDroneTypes() {
        List<DroneTypesEntry> droneTypesList = new ArrayList<>();
        try {
            // Fetch data from API
            String response = Api.fetchData(BASE_URL);
            // Process the data and populate the list
            droneTypesList = processDroneTypesData(response);
        } catch (Exception e) {
            System.err.println("Error fetching drone catalog: " + e.getMessage());
        }
        return droneTypesList; // Return the list of DroneTypesEntry objects
    }

    // Processes the JSON response and creates a list of DroneTypesEntry objects
    private List<DroneTypesEntry> processDroneTypesData(String jsonResponse) {
        List<DroneTypesEntry> droneTypesList = new ArrayList<>();
        JSONObject responseObject = new JSONObject(jsonResponse);
        JSONArray dronesArray = responseObject.getJSONArray("results");

        for (int i = 0; i < dronesArray.length(); i++) {
            JSONObject droneObject = dronesArray.getJSONObject(i);
            try {
                String id = droneObject.optString("id", "Unknown");
                String manufacturer = droneObject.optString("manufacturer", "Unknown");
                String typename = droneObject.optString("typename", "Unknown");
                double weight = droneObject.optDouble("weight");
                double maxSpeed = droneObject.optDouble("max_speed");
                double batteryCapacity = droneObject.optDouble("battery_capacity");
                double controlRange = droneObject.optDouble("control_range");
                double maxCarriage = droneObject.optDouble("max_carriage");

                // Create a DroneTypesEntry object and add it to the list
                DroneTypesEntry drone = new DroneTypesEntry(id, manufacturer, typename, weight, maxSpeed, batteryCapacity, controlRange, maxCarriage);
                droneTypesList.add(drone);
            } catch (Exception e) {
                System.err.println("Error parsing drone catalog data: " + e.getMessage());
            }
        }

        // Handle pagination
        String nextPageUrl = responseObject.optString("next", null);
        if (nextPageUrl != null) {
            try {
                String nextPageResponse = Api.fetchData(nextPageUrl);
                droneTypesList.addAll(processDroneTypesData(nextPageResponse));
            } catch (Exception e) {
                System.err.println("Error fetching next page of catalog data: " + e.getMessage());
            }
        }

        return droneTypesList; // Return the list of DroneTypesEntry objects
    }
}

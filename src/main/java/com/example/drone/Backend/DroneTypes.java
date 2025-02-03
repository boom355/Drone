package com.example.drone.Backend;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a service that fetches and processes drone types from a remote API.
 * It contains methods to retrieve a list of drone types, process the data, and handle pagination.
 * <p>
 * The class retrieves drone type data from a specified API, processes the JSON response,
 * and returns a list of {@link DroneTypesEntry} objects representing the drone types.
 * </p>
 *
 * @author [Your Name]
 * @version 1.0
 */
public class DroneTypes {

    // Base URL for fetching drone types data from the API
    private static final String BASE_URL = "http://dronesim.facets-labs.com/api/dronetypes/?format=json";

    /**
     * Fetches a list of drone types by calling the remote API.
     *
     * @return A list of {@link DroneTypesEntry} objects, each representing a drone type.
     */
    public List<DroneTypesEntry> fetchDroneTypes() {
        List<DroneTypesEntry> droneTypesList = new ArrayList<>();
        try {
            // Fetch data from the API
            String response = Api.fetchData(BASE_URL);
            // Process the fetched data to create a list of drone types
            droneTypesList = processDroneTypesData(response);
        } catch (Exception e) {
            System.err.println("Error fetching drone catalog: " + e.getMessage());
        }
        return droneTypesList; // Return the list of DroneTypesEntry objects
    }

    /**
     * Processes the JSON response to create a list of DroneTypesEntry objects.
     *
     * @param jsonResponse The JSON response as a string, containing drone types data.
     * @return A list of {@link DroneTypesEntry} objects created from the JSON response.
     */
    private List<DroneTypesEntry> processDroneTypesData(String jsonResponse) {
        List<DroneTypesEntry> droneTypesList = new ArrayList<>();
        JSONObject responseObject = new JSONObject(jsonResponse);
        JSONArray dronesArray = responseObject.getJSONArray("results");

        // Iterate through the array of drone types in the response
        for (int i = 0; i < dronesArray.length(); i++) {
            JSONObject droneObject = dronesArray.getJSONObject(i);
            try {
                // Extract data from the drone type JSON object
                String id = droneObject.optString("id", "Unknown");
                String manufacturer = droneObject.optString("manufacturer", "Unknown");
                String typename = droneObject.optString("typename", "Unknown");
                double weight = droneObject.optDouble("weight");
                double maxSpeed = droneObject.optDouble("max_speed");
                double batteryCapacity = droneObject.optDouble("battery_capacity");
                double controlRange = droneObject.optDouble("control_range");
                double maxCarriage = droneObject.optDouble("max_carriage");

                // Create a new DroneTypesEntry object with the extracted data
                DroneTypesEntry drone = new DroneTypesEntry(id, manufacturer, typename, weight, maxSpeed, batteryCapacity, controlRange, maxCarriage);
                // Add the drone type entry to the list
                droneTypesList.add(drone);
            } catch (Exception e) {
                System.err.println("Error parsing drone catalog data: " + e.getMessage());
            }
        }

        // Handle pagination (next page of data if available)
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

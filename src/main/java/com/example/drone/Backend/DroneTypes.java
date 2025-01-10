package com.example.drone.Backend;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DroneTypes {
    private static final String BASE_URL = "http://dronesim.facets-labs.com/api/dronetypes/?format=json";

    /**
     * Fetches drone types and returns a list of readable strings.
     *
     * @return List of drone types in "Manufacturer: Typename" format.
     */
    public List<String> fetchDroneTypes() {
        List<String> droneTypeList = new ArrayList<>();
        try {
            String response = Api.fetchData(BASE_URL);
            List<DroneTypesEntry> droneEntries = processCatalogData(response);

            // Convert DroneTypesEntry objects into readable strings
            for (DroneTypesEntry entry : droneEntries) {
                droneTypeList.add(entry.getManufacturer() + ": " + entry.getTypename());
            }
        } catch (Exception e) {
            System.err.println("Error fetching drone catalog: " + e.getMessage());
        }
        return droneTypeList;
    }

    /**
     * Processes the JSON response and creates a list of DroneTypesEntry objects.
     *
     * @param jsonResponse JSON string from the API.
     * @return List of DroneTypesEntry objects.
     */
    private List<DroneTypesEntry> processCatalogData(String jsonResponse) {
        List<DroneTypesEntry> droneList = new ArrayList<>();
        JSONObject responseObject = new JSONObject(jsonResponse);
        JSONArray dronesArray = responseObject.getJSONArray("results");

        for (int i = 0; i < dronesArray.length(); i++) {
            JSONObject droneObject = dronesArray.getJSONObject(i);
            try {
                String id = droneObject.optString("id", "Unknown");
                String manufacturer = droneObject.optString("manufacturer", "Unknown");
                String typename = droneObject.optString("typename", "Unknown");
                double weight = droneObject.optDouble("weight", 0.0);
                double maxSpeed = droneObject.optDouble("max_speed", 0.0);
                double batteryCapacity = droneObject.optDouble("battery_capacity", 0.0);
                double controlRange = droneObject.optDouble("control_range", 0.0);
                double maxCarriage = droneObject.optDouble("max_carriage", 0.0);

                // Create DroneTypesEntry object
                DroneTypesEntry drone = new DroneTypesEntry(id, manufacturer, typename, weight, maxSpeed, batteryCapacity, controlRange, maxCarriage);
                droneList.add(drone);
            } catch (Exception e) {
                System.err.println("Error parsing drone catalog data: " + e.getMessage());
            }
        }

        // Handle pagination
        String nextPageUrl = responseObject.optString("next", null);
        if (nextPageUrl != null) {
            try {
                String nextPageResponse = Api.fetchData(nextPageUrl);
                droneList.addAll(processCatalogData(nextPageResponse));
            } catch (Exception e) {
                System.err.println("Error fetching next page of catalog data: " + e.getMessage());
            }
        }

        return droneList;
    }
}

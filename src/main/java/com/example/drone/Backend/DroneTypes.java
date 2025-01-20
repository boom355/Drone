package com.example.drone.Backend;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DroneTypes {
    private static final String BASE_URL = "http://dronesim.facets-labs.com/api/dronetypes/?format=json";
    private static final String DRONE_URL = "http://dronesim.facets-labs.com/api/drones/?format=json"; // Assuming the drones API endpoint

    public List<DroneTypesEntry> fetchDroneTypes() {
        List<DroneTypesEntry> droneTypes = new ArrayList<>();
        try {
            String response = Api.fetchData(BASE_URL);
            droneTypes = processCatalogData(response);  // Now returning List<DroneTypesEntry>
        } catch (Exception e) {
            System.err.println("Error fetching drone types: " + e.getMessage());
        }
        return droneTypes;  // Returning List<DroneTypesEntry>
    }

    // Fetch drones by type from API
    public List<DronesEntry> fetchDronesByType(String droneTypeId) {
        List<DronesEntry> drones = new ArrayList<>();
        try {
            String url = DRONE_URL + "&type=" + droneTypeId;  // Modify URL with the specific drone type filter
            String response = Api.fetchData(url);
            drones = processDroneData(response);  // Process and return list of drones
        } catch (Exception e) {
            System.err.println("Error fetching drones by type: " + e.getMessage());
        }
        return drones;
    }

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

                DroneTypesEntry drone = new DroneTypesEntry(id, manufacturer, typename, weight, maxSpeed, batteryCapacity, controlRange, maxCarriage);
                droneList.add(drone);
            } catch (Exception e) {
                System.err.println("Error parsing drone catalog data: " + e.getMessage());
            }
        }

        // Handle pagination (next page)
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

    private List<DronesEntry> processDroneData(String jsonResponse) {
        List<DronesEntry> dronesList = new ArrayList<>();
        JSONObject responseObject = new JSONObject(jsonResponse);
        JSONArray dronesArray = responseObject.getJSONArray("results");

        for (int i = 0; i < dronesArray.length(); i++) {
            JSONObject droneObject = dronesArray.getJSONObject(i);
            try {
                // Extract fields based on your DronesEntry constructor
                String id = droneObject.optString("id", "Unknown");
                String droneType = droneObject.optString("drone_type", "Unknown");  // Adjust field name if necessary
                String createdDate = droneObject.optString("created_date", "Unknown");  // Adjust field name if necessary
                String serialNumber = droneObject.optString("serial_number", "Unknown");  // Adjust field name if necessary
                double carriageWeight = droneObject.optDouble("carriage_weight", 0.0);  // Adjust field name if necessary
                String carriageType = droneObject.optString("carriage_type", "Unknown");  // Adjust field name if necessary

                // Create a new DronesEntry object using the extracted values
                DronesEntry drone = new DronesEntry(id, droneType, createdDate, serialNumber, carriageWeight, carriageType);
                dronesList.add(drone);
            } catch (Exception e) {
                System.err.println("Error parsing drones data: " + e.getMessage());
            }
        }

        return dronesList;
    }
}

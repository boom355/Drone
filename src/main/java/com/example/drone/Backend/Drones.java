package com.example.drone.Backend;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Drones {
    private static final String BASE_URL = "http://dronesim.facets-labs.com/api/drones/?format=json";

    // Fetch drones and return a list of DronesEntry objects
    public List<DronesEntry> fetchDrones() {
        List<DronesEntry> drones = new ArrayList<>();
        try {
            String response = Api.fetchData(BASE_URL);
            drones = processDroneData(response);
        } catch (Exception e) {
            System.err.println("Error fetching drones: " + e.getMessage());
        }
        return drones;
    }

    // Process JSON response to create a list of DronesEntry objects
    private List<DronesEntry> processDroneData(String jsonResponse) {
        List<DronesEntry> droneList = new ArrayList<>();
        JSONObject responseObject = new JSONObject(jsonResponse);
        JSONArray dronesArray = responseObject.getJSONArray("results");

        for (int i = 0; i < dronesArray.length(); i++) {
            JSONObject droneObject = dronesArray.getJSONObject(i);
            try {
                String id = droneObject.optString("id", "Unknown");
                String droneTypeUrl = droneObject.optString("dronetype", "Unknown");
                String createdDate = droneObject.optString("created", "Unknown");
                String serialNumber = droneObject.optString("serialnumber", "Unknown");
                double carriageWeight = droneObject.optDouble("carriage_weight", 0.0);
                String carriageType = droneObject.optString("carriage_type", "Unknown");

                String droneTypeName = fetchDroneTypeName(droneTypeUrl);

                DronesEntry drone = new DronesEntry(id, droneTypeName, createdDate, serialNumber, carriageWeight, carriageType);
                droneList.add(drone);
            } catch (Exception e) {
                System.err.println("Error parsing drone data: " + e.getMessage());
            }
        }

        String nextPageUrl = responseObject.optString("next", null);
        if (nextPageUrl != null) {
            try {
                String nextPageResponse = Api.fetchData(nextPageUrl);
                droneList.addAll(processDroneData(nextPageResponse));
            } catch (Exception e) {
                System.err.println("Error fetching next page of drone data: " + e.getMessage());
            }
        }

        return droneList;
    }

    // Fetch drone type name from the dronetype URL
    private String fetchDroneTypeName(String droneTypeUrl) {
        try {
            String droneTypeResponse = Api.fetchData(droneTypeUrl);
            JSONObject droneTypeObject = new JSONObject(droneTypeResponse);
            String manufacturer = droneTypeObject.optString("manufacturer", "Unknown");
            String typename = droneTypeObject.optString("typename", "Unknown");

            return manufacturer + ": " + typename;
        } catch (Exception e) {
            System.err.println("Error fetching drone type data: " + e.getMessage());
            return "Unknown";
        }
    }
}
package com.example.drone.Backend;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DroneDynamics {

    private static final String BASE_URL = "http://dronesim.facets-labs.com/api/dronedynamics/?format=json";
    private static final String TOKEN = "Token 28c5253fd117e5e5a96c3684ee61155190899907";
    private static final int LOW_BATTERY_THRESHOLD = 15;  // Battery level threshold for alert

    private String nextPage = null;
    private String previousPage = null;

    // Stores the current drone dynamics data
    private List<DroneDynamicEntry> droneDynamicsList = new ArrayList<>();

    // Fetch drone dynamics with pagination
    public List<DroneDynamicEntry> fetchDroneData(String pageUrl) throws Exception {
        List<DroneDynamicEntry> fetchedDynamicsList = new ArrayList<>();
        String url = (pageUrl == null || pageUrl.isEmpty()) ? BASE_URL : pageUrl;

        // Fetch the data with token
        String response = fetchDataWithToken(url);

        // Parse the JSON response
        JSONObject responseObject = new JSONObject(response);
        JSONArray resultsArray = responseObject.getJSONArray("results");

        // Loop through the results array and create entries for each
        for (int i = 0; i < resultsArray.length(); i++) {
            JSONObject entry = resultsArray.getJSONObject(i);
            String id = entry.optString("id", "Unknown");  // Extract the id
            String timestamp = entry.optString("timestamp", "Unknown");
            int speed = entry.optInt("speed", 0);
            double alignRoll = Double.parseDouble(entry.optString("align_roll", "0.0"));
            double controlRange = Double.parseDouble(entry.optString("control_range", "0.0"));
            double alignYaw = Double.parseDouble(entry.optString("align_yaw", "0.0"));
            double longitude = Double.parseDouble(entry.optString("longitude", "0.0"));
            double latitude = Double.parseDouble(entry.optString("latitude", "0.0"));
            int batteryStatus = entry.optInt("battery_status", 0);
            String lastSeen = entry.optString("last_seen", "Unknown");
            String status = entry.optString("status", "Unknown");

            // Create a DroneDynamicEntry object and add it to the list
            DroneDynamicEntry dynamicEntry = new DroneDynamicEntry(
                    id, timestamp, speed, alignRoll, alignYaw, longitude, latitude, batteryStatus, status, lastSeen, controlRange
            );
            fetchedDynamicsList.add(dynamicEntry);
        }

        // Update pagination details
        this.nextPage = responseObject.optString("next", null);
        this.previousPage = responseObject.optString("previous", null);

        return fetchedDynamicsList;
    }


    // Method to fetch data with authorization token
    private String fetchDataWithToken(String url) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestProperty("Authorization", TOKEN);
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                return response.toString();
            }
        } else {
            throw new Exception("HTTP Error: " + responseCode);
        }
    }

    // Calculate average battery percentage
    private Integer calculateBatteryPercentage(List<DroneDynamicEntry> dynamics) {
        int totalBattery = 0;
        int count = dynamics.size();

        for (DroneDynamicEntry entry : dynamics) {
            // Convert raw battery status to percentage
            totalBattery += (entry.getBatteryStatus() * 100) / 10000;
        }

        // Return the average battery percentage
        return count > 0 ? totalBattery / count : 0;
    }

    // Check for drones with battery below 15% and trigger alert
    public void checkLowBattery(List<DroneDynamicEntry> drones) {
        for (DroneDynamicEntry entry : drones) {
            if (entry.getBatteryStatus() < LOW_BATTERY_THRESHOLD) {
                // You can add more sophisticated alert handling here
                System.out.println("ALERT: Drone with ID " + entry.getId() + " has low battery (" + entry.getBatteryStatus() + "%).");
            }
        }
    }

    // Refresh method to re-fetch and update the drone dynamics data
    public void refreshData() {
        try {
            System.out.println("Refreshing drone dynamics data...");
            // Clear the existing list
            this.droneDynamicsList.clear();

            // Reset pagination
            this.nextPage = null;
            this.previousPage = null;

            // Re-fetch the data from the first page
            this.droneDynamicsList = fetchDroneData(null);

            // Log the average battery percentage after refreshing
            int batteryPercentage = calculateBatteryPercentage(this.droneDynamicsList);
            System.out.println("Data refreshed successfully.");
            System.out.println("Average Battery Percentage: " + batteryPercentage + "%");

            // Check for low battery drones
            checkLowBattery(this.droneDynamicsList);
        } catch (Exception e) {
            System.err.println("Error refreshing drone dynamics data: " + e.getMessage());
        }
    }

    // Getters for drone dynamics list and pagination
    public List<DroneDynamicEntry> getDroneDynamicsList() {
        return droneDynamicsList;
    }

    public String getNextPage() {
        return nextPage;
    }

    public String getPreviousPage() {
        return previousPage;
    }

    // In DroneDynamics class
    public Integer getAverageBatteryPercentage() {
        return calculateBatteryPercentage(droneDynamicsList);
    }

}

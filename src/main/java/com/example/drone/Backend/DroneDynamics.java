package com.example.drone.Backend;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for fetching and managing dynamic drone data from a remote API.
 * It extends {@link AbstractDroneDynamics} and provides methods to interact with the drone data API,
 * parse the JSON response, and perform various operations on the data such as checking battery levels
 * and refreshing the data.
 * <p>
 * The data fetched includes various telemetry information about drones such as speed, position,
 * battery status, and more.
 * </p>
 *
 * @author [Your Name]
 * @version 1.0
 */
public class DroneDynamics extends AbstractDroneDynamics {

    private static final String BASE_URL = "http://dronesim.facets-labs.com/api/dronedynamics/?format=json";
    private static final String TOKEN = "Token 28c5253fd117e5e5a96c3684ee61155190899907";
    private static final int LOW_BATTERY_THRESHOLD = 15;  // Battery level threshold for alert

    private String nextPage = null;
    private String previousPage = null;
    private List<DroneDynamicEntry> droneDynamicsList = new ArrayList<>();

    /**
     * Fetches drone data from the given URL, parses the JSON response, and returns a list of
     * {@link DroneDynamicEntry} objects representing each drone entry.
     * <p>
     * The method retrieves the next and previous page URLs from the response for pagination support.
     * </p>
     *
     * @param pageUrl The URL to fetch the data from. If {@code null} or empty, the base URL is used.
     * @return A list of {@link DroneDynamicEntry} objects.
     * @throws DroneDataException If there is an error during the data fetch or parsing.
     */
    @Override
    public List<DroneDynamicEntry> fetchDroneData(String pageUrl) throws DroneDataException {
        List<DroneDynamicEntry> fetchedDynamicsList = new ArrayList<>();
        String url = (pageUrl == null || pageUrl.isEmpty()) ? BASE_URL : pageUrl;

        try {
            // Fetch and parse data
            String response = fetchDataWithToken(url);

            JSONObject responseObject = new JSONObject(response);
            JSONArray resultsArray = responseObject.getJSONArray("results");

            // Set pagination links
            nextPage = responseObject.optString("next", null);
            previousPage = responseObject.optString("previous", null);

            // Parse each result and create DroneDynamicEntry objects
            for (int i = 0; i < resultsArray.length(); i++) {
                JSONObject entry = resultsArray.getJSONObject(i);
                String id = entry.optString("id", "Unknown");
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

                // Create DroneDynamicEntry object and add it to the list
                DroneDynamicEntry dynamicEntry = new DroneDynamicEntry(
                        id, timestamp, speed, alignRoll, alignYaw, longitude, latitude, batteryStatus, status, lastSeen, controlRange
                );
                fetchedDynamicsList.add(dynamicEntry);
            }
        } catch (Exception e) {
            throw new DroneDataException("Error fetching drone data", e);
        }

        return fetchedDynamicsList;
    }

    /**
     * Fetches data from a URL using an HTTP GET request with an authorization token.
     *
     * @param url The URL to fetch data from.
     * @return The response data as a string.
     * @throws DroneDataException If there is an error fetching data.
     */
    private String fetchDataWithToken(String url) throws DroneDataException {
        try {
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
                throw new DroneDataException("Failed to fetch drone data: HTTP Error " + responseCode);
            }
        } catch (Exception e) {
            throw new DroneDataException("Error fetching data with token", e);
        }
    }

    /**
     * Checks for drones with battery levels below the defined threshold and prints an alert.
     *
     * @param drones The list of drone dynamic entries to check.
     */
    public void checkLowBattery(List<DroneDynamicEntry> drones) {
        for (DroneDynamicEntry entry : drones) {
            if (entry.getBatteryPercentage() < LOW_BATTERY_THRESHOLD) {
                System.out.println("ALERT: Drone with Timestamp " + entry.getTimestamp()
                        + " has low battery (" + entry.getBatteryPercentage() + "%).");
            }
        }
    }

    /**
     * Refreshes the drone dynamics data by clearing the existing list and re-fetching data from the API.
     * It also logs the average battery percentage after refreshing and checks for low battery drones.
     */
    public void refreshData() {
        try {
            System.out.println("Refreshing drone dynamics data...");
            // Clear the existing list and fetch fresh data
            this.droneDynamicsList.clear();
            this.droneDynamicsList = fetchDroneData(null);

            // Calculate and log the average battery percentage
            int batteryPercentage = calculateBatteryPercentage(this.droneDynamicsList);
            System.out.println("Data refreshed successfully.");
            System.out.println("Average Battery Percentage: " + batteryPercentage + "%");

            // Check for drones with low battery
            checkLowBattery(this.droneDynamicsList);
        } catch (Exception e) {
            System.err.println("Error refreshing drone dynamics data: " + e.getMessage());
        }
    }

    /**
     * Calculates the average battery percentage for a list of drone dynamic entries.
     *
     * @param dynamics The list of drone dynamic entries.
     * @return The average battery percentage.
     */
    private Integer calculateBatteryPercentage(List<DroneDynamicEntry> dynamics) {
        int totalBattery = 0;
        int count = dynamics.size();

        for (DroneDynamicEntry entry : dynamics) {
            totalBattery += (entry.getBatteryStatus() * 100) / 10000;
        }

        return count > 0 ? totalBattery / count : 0;
    }

    /**
     * Gets the URL for the next page of drone data, if available.
     *
     * @return The next page URL, or {@code null} if not available.
     */
    public String getNextPage() {
        return nextPage;
    }

    /**
     * Gets the URL for the previous page of drone data, if available.
     *
     * @return The previous page URL, or {@code null} if not available.
     */
    public String getPreviousPage() {
        return previousPage;
    }

    /**
     * Gets the list of current drone dynamic entries.
     *
     * @return The list of drone dynamics.
     */
    public List<DroneDynamicEntry> getDroneDynamicsList() {
        return droneDynamicsList;
    }

}

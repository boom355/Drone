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

    private String nextPage = null;
    private String previousPage = null;

    // Fetch drone dynamics with pagination
    public List<DroneDynamicEntry> fetchDroneData(String pageUrl) throws Exception {
        List<DroneDynamicEntry> droneDynamicsList = new ArrayList<>();

        String url = (pageUrl == null || pageUrl.isEmpty()) ? BASE_URL : pageUrl;

        // Fetch the data with token
        String response = fetchDataWithToken(url);

        // Parse the JSON response
        JSONObject responseObject = new JSONObject(response);
        JSONArray resultsArray = responseObject.getJSONArray("results");

        // Loop through the results array and create entries for each
        for (int i = 0; i < resultsArray.length(); i++) {
            JSONObject entry = resultsArray.getJSONObject(i);

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
                    timestamp, speed, alignRoll, alignYaw, longitude, latitude, batteryStatus, status, lastSeen, controlRange
            );
            droneDynamicsList.add(dynamicEntry);
        }

        // Handle pagination (next and previous URLs)
        this.nextPage = responseObject.optString("next", null);
        this.previousPage = responseObject.optString("previous", null);

        return droneDynamicsList;
    }

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

    // Getters for next and previous page URLs
    public String getNextPage() {
        return nextPage;
    }

    public String getPreviousPage() {
        return previousPage;
    }
}
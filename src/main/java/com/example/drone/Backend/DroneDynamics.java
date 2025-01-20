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

    // Fetch drone dynamics for a specific drone ID
    public List<DroneDynamicEntry> fetchDroneDataById(String droneId) throws Exception {
        String url = BASE_URL + "&drone=" + droneId;
        String jsonResponse = fetchDataWithToken(url); // Use token-based fetching
        return processDynamicData(jsonResponse);
    }

    // Process JSON response into a list of DroneDynamicEntry objects
    private List<DroneDynamicEntry> processDynamicData(String jsonResponse) {
        List<DroneDynamicEntry> dynamicsList = new ArrayList<>();
        JSONObject responseObject = new JSONObject(jsonResponse);
        JSONArray resultsArray = responseObject.getJSONArray("results");

        for (int i = 0; i < resultsArray.length(); i++) {
            JSONObject entry = resultsArray.getJSONObject(i);
            DroneDynamicEntry dynamicEntry = new DroneDynamicEntry(
                    entry.optString("timestamp", "Unknown"),
                    entry.optInt("speed", 0),
                    entry.optDouble("align_roll", 0.0),
                    entry.optDouble("align_yaw", 0.0),
                    entry.optDouble("longitude", 0.0),
                    entry.optDouble("latitude", 0.0),
                    entry.optInt("battery_status", 0),
                    entry.optString("status", "Unknown"),
                    entry.optString("last_seen", "Unknown"),
                    entry.optDouble("control_range", 0.0)
            );
            dynamicsList.add(dynamicEntry);
        }
        return dynamicsList;
    }

    // Make an HTTP GET request with token-based authorization
    private String fetchDataWithToken(String url) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestProperty("Authorization", TOKEN);
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK
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
} 
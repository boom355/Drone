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
        List<DroneDynamicEntry> droneDynamicsList = new ArrayList<>();
        String url = BASE_URL + "&drone=" + droneId;

        String response = fetchDataWithToken(url);

        JSONObject responseObject = new JSONObject(response);
        JSONArray resultsArray = responseObject.getJSONArray("results");

        for (int i = 0; i < resultsArray.length(); i++) {
            JSONObject entry = resultsArray.getJSONObject(i);

            String timestamp = entry.optString("timestamp", "Unknown");
            int speed = entry.optInt("speed", 0);
            double longitude = entry.optDouble("longitude", 0.0);
            double latitude = entry.optDouble("latitude", 0.0);
            int batteryStatus = entry.optInt("battery_status", 0);
            String status = entry.optString("status", "Unknown");

            DroneDynamicEntry dynamicEntry = new DroneDynamicEntry(timestamp, speed, longitude, latitude, batteryStatus, status);
            droneDynamicsList.add(dynamicEntry);
        }

        return droneDynamicsList;
    }

    private String fetchDataWithToken(String url) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestProperty("Authorization", DroneDynamics.TOKEN);
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
}

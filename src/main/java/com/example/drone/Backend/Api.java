package com.example.drone.Backend;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Api {

    // Token for Authentication
    private static final String TOKEN = "Token 28c5253fd117e5e5a96c3684ee61155190899907"; // Your token here

    public static String fetchData(String urlString) throws Exception {
        // Create URL object
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set the HTTP method to GET
        connection.setRequestMethod("GET");

        // Add Authorization header with the token
        connection.setRequestProperty("Authorization", TOKEN);

        // Open connection and get the response
        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : " + responseCode);
        }

        // Read the response
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString(); // Return response as a string
    }
}

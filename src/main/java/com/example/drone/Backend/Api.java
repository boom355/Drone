package com.example.drone.Backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;

public class Api {
    private static final String TOKEN = "Token 28c5253fd117e5e5a96c3684ee61155190899907";

    private Api() {}

    private static HttpURLConnection createConnection(String endpoint) throws IOException, URISyntaxException {
        URI uri = new URI(endpoint);
        HttpURLConnection con = (HttpURLConnection) uri.toURL().openConnection();
        con.setRequestProperty("Authorization", TOKEN);
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        return con;
    }

    public static String fetchData(String endpoint) throws IOException, URISyntaxException {
        HttpURLConnection con = createConnection(endpoint);
        int responseCode = con.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("Failed to fetch data. HTTP response code: " + responseCode);
        }

        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        }
    }
}

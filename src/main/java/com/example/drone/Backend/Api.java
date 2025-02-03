package com.example.drone.Backend;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Provides methods for interacting with a remote API.
 * This class supports:
 * <ul>
 *     <li>Fetching data via HTTP GET requests.</li>
 *     <li>Authentication using an API token.</li>
 *     <li>Logging API responses to a file.</li>
 *     <li>Reading stored API responses from the log file.</li>
 * </ul>
 * <p>
 * The API responses are stored in {@code api_response.txt}.
 * </p>
 *
 * @author [Your Name]
 * @version 1.0
 */
public class Api {

    /**
     * API token used for authentication in requests.
     */
    private static final String TOKEN = "Token 28c5253fd117e5e5a96c3684ee61155190899907";

    /**
     * File where API responses are logged.
     */
    private static final String LOG_FILE = "api_response.txt";

    /**
     * Fetches data from the specified URL using an HTTP GET request and logs the response.
     *
     * @param urlString The URL from which to fetch data.
     * @return The response data as a {@code String}.
     * @throws Exception If an error occurs during the request.
     */
    public static String fetchData(String urlString) throws Exception {
        URL url = new URL(urlString);
        BufferedReader in = getBufferedReader(url);
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Log response to a file
        writeToFile(response.toString());

        return response.toString();
    }

    /**
     * Establishes an HTTP connection, sends a GET request, and returns a BufferedReader for reading the response.
     *
     * @param url The API URL to connect to.
     * @return A {@code BufferedReader} to read the API response.
     * @throws IOException If there is an error in creating the connection or reading the response.
     */
    private static BufferedReader getBufferedReader(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", TOKEN);

        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed: HTTP error code: " + responseCode);
        }

        return new BufferedReader(new InputStreamReader(connection.getInputStream()));
    }

    /**
     * Writes API response data to a log file.
     *
     * @param data The response data to be written to the log file.
     */
    private static void writeToFile(String data) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) { // Append mode
            writer.write(data + "\n");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Reads and returns the stored API responses from the log file.
     *
     * @return The contents of the log file as a {@code String}.
     */
    public static String readFromFile() {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return content.toString();
    }
}

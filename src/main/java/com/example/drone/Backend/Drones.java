package com.example.drone.Backend;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Drones {

    private static final String BASE_URL = "http://dronesim.facets-labs.com/api/drones/?format=json";

    /**
     * Fetches a list of all drones and processes their data.
     * This method will fetch drone data in a separate thread.
     *
     * @return A list of {@link DronesEntry} objects representing the fetched drone data.
     */
    public List<DronesEntry> fetchDrones() {
        List<DronesEntry> drones = new ArrayList<>();
        Thread fetchThread = new Thread(() -> {
            try {
                String response = Api.fetchData(BASE_URL);
                drones.addAll(processDroneData(response));
            } catch (Exception e) {
                System.err.println("Error fetching drones: " + e.getMessage());
            }
        });
        fetchThread.start();

        try {
            // Wait for the fetch thread to finish before returning the result
            fetchThread.join();
        } catch (InterruptedException e) {
            System.err.println("Thread interrupted: " + e.getMessage());
        }

        return drones;
    }

    /**
     * Processes the JSON response to create a list of {@link DronesEntry} objects.
     * @param jsonResponse The raw JSON response containing drone data.
     * @return A list of {@link DronesEntry} objects created from the JSON response.
     */
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

                // Fetch drone type name using the drone type URL
                String droneTypeName = fetchDroneTypeName(droneTypeUrl);

                // Create DronesEntry object
                DronesEntry drone = new DronesEntry(id, droneTypeName, createdDate, serialNumber, carriageWeight, carriageType);

                // Fetch the dynamics for the current drone asynchronously
                List<DroneDynamicEntry> droneDynamics = fetchDroneDynamics(id);

                // Calculate and set the average speed and total distance of the drone
                double averageSpeed = calculateAverageSpeed(droneDynamics);
                double totalDistance = calculateTotalDistance(droneDynamics);

                drone.setAverageSpeed(averageSpeed);
                drone.setTotalDistance(totalDistance);

                // Add the drone entry to the list
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

    /**
     * Fetches the drone type name from the provided URL of the drone type.
     *
     * @param droneTypeUrl The URL pointing to the drone type data.
     * @return A string representing the manufacturer and type of the drone.
     */
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

    /**
     * Fetches the drone dynamics (data) for a specific drone ID asynchronously.
     *
     * @param droneId The ID of the drone whose dynamics are to be fetched.
     * @return A list of {@link DroneDynamicEntry} objects representing the dynamics of the specified drone.
     */
    private List<DroneDynamicEntry> fetchDroneDynamics(String droneId) {
        List<DroneDynamicEntry> dynamics = new ArrayList<>();
        String dynamicsUrl = "http://dronesim.facets-labs.com/api/dronedynamics/?drone=" + droneId + "&format=json";

        Thread fetchDynamicsThread = new Thread(() -> {
            try {
                String response = Api.fetchData(dynamicsUrl);
                JSONObject responseObject = new JSONObject(response);
                JSONArray dynamicsArray = responseObject.getJSONArray("results");

                for (int i = 0; i < dynamicsArray.length(); i++) {
                    JSONObject dynamicObject = dynamicsArray.getJSONObject(i);
                    DroneDynamicEntry entry = new DroneDynamicEntry(
                            dynamicObject.optString("id"),
                            dynamicObject.optString("timestamp", "Unknown"),
                            dynamicObject.optInt("speed", 0),
                            dynamicObject.optDouble("align_roll", 0.0),
                            dynamicObject.optDouble("align_yaw", 0.0),
                            dynamicObject.optDouble("longitude", 0.0),
                            dynamicObject.optDouble("latitude", 0.0),
                            dynamicObject.optInt("Battery_status", 0),
                            dynamicObject.optString("status", "Unknown"),
                            dynamicObject.optString("last_seen", "Unknown"),
                            dynamicObject.optDouble("control_range", 0.0)
                    );
                    dynamics.add(entry);
                }
            } catch (Exception e) {
                System.err.println("Error fetching drone dynamics: " + e.getMessage());
            }
        });

        fetchDynamicsThread.start();

        try {
            fetchDynamicsThread.join();
        } catch (InterruptedException e) {
            System.err.println("Thread interrupted: " + e.getMessage());
        }

        return dynamics;
    }

    /**
     * Calculates the average speed of a list of drone dynamics entries.
     * @param dynamics The list of drone dynamic entries.
     * @return The average speed of the drone.
     */
    private double calculateAverageSpeed(List<DroneDynamicEntry> dynamics) {
        double totalSpeed = 0;
        int count = dynamics.size();

        for (DroneDynamicEntry entry : dynamics) {
            totalSpeed += entry.getSpeed();
        }

        return count > 0 ? totalSpeed / count : 0;
    }

    /**
     * Calculates the total distance traveled by the drone based on its dynamics entries.
     * @param dynamics The list of drone dynamic entries.
     * @return The total distance traveled by the drone in kilometers.
     */
    private double calculateTotalDistance(List<DroneDynamicEntry> dynamics) {
        double totalDistance = 0;

        for (int i = 1; i < dynamics.size(); i++) {
            DroneDynamicEntry prev = dynamics.get(i - 1);
            DroneDynamicEntry current = dynamics.get(i);

            totalDistance += calculateDistance(
                    prev.getLatitude(), prev.getLongitude(),
                    current.getLatitude(), current.getLongitude()
            );
        }

        return totalDistance;
    }

    /**
     * Helper method to calculate the distance between two geographical coordinates.
     * @param lat1 The latitude of the first point.
     * @param lon1 The longitude of the first point.
     * @param lat2 The latitude of the second point.
     * @param lon2 The longitude of the second point.
     * @return The distance between the two points in kilometers.
     */
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of the Earth in km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Distance in km
    }
}

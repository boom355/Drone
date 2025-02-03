package com.example.drone.Backend;

/**
 * This class represents a drone entry with details about the drone, including its ID, type,
 * creation date, serial number, carriage weight, and carriage type. It also contains the drone's
 * dynamic data such as average speed and total distance traveled.
 * <p>
 * A {@link DronesEntry} object encapsulates information about a single drone, including its metadata
 * (e.g., ID, type, creation date) and dynamic telemetry data (e.g., average speed, total distance).
 * </p>
 *
 * @author [Your Name]
 * @version 1.0
 */
public class DronesEntry {

    private final String id;
    private final String droneType;
    private final String createdDate;
    private final String serialNumber;
    private final double carriageWeight;
    private final String carriageType;

    private double averageSpeed;
    private double totalDistance;

    /**
     * Constructs a DronesEntry object with the provided drone details.
     *
     * @param id The unique identifier for the drone.
     * @param droneType The type of the drone (e.g., manufacturer and model).
     * @param createdDate The date when the drone was created.
     * @param serialNumber The serial number of the drone.
     * @param carriageWeight The weight capacity of the drone's carriage in kilograms.
     * @param carriageType The type of the carriage (e.g., "Cargo", "Medical").
     */
    public DronesEntry(String id, String droneType, String createdDate, String serialNumber, double carriageWeight, String carriageType) {
        this.id = id;
        this.droneType = droneType;
        this.createdDate = createdDate;
        this.serialNumber = serialNumber;
        this.carriageWeight = carriageWeight;
        this.carriageType = carriageType;
    }

    /**
     * Gets the average speed of the drone.
     *
     * @return The average speed of the drone in km/h.
     */
    public double getAverageSpeed() {
        return averageSpeed;
    }

    /**
     * Sets the average speed of the drone.
     *
     * @param averageSpeed The average speed of the drone in km/h.
     */
    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    /**
     * Gets the total distance traveled by the drone.
     *
     * @return The total distance traveled by the drone in kilometers.
     */
    public double getTotalDistance() {
        return totalDistance;
    }

    /**
     * Sets the total distance traveled by the drone.
     *
     * @param totalDistance The total distance traveled by the drone in kilometers.
     */
    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    // Getters

    /**
     * Gets the unique identifier of the drone.
     *
     * @return The ID of the drone.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the type of the drone.
     *
     * @return The type of the drone (e.g., manufacturer and model).
     */
    public String getDroneType() {
        return droneType;
    }

    /**
     * Gets the creation date of the drone.
     *
     * @return The creation date of the drone.
     */
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * Gets the serial number of the drone.
     *
     * @return The serial number of the drone.
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * Gets the carriage weight of the drone.
     *
     * @return The carriage weight of the drone in kilograms.
     */
    public double getCarriageWeight() {
        return carriageWeight;
    }

    /**
     * Gets the carriage type of the drone.
     *
     * @return The type of the carriage (e.g., "Cargo", "Medical").
     */
    public String getCarriageType() {
        return carriageType;
    }

    /**
     * Provides a string representation of the DronesEntry object, summarizing the drone's attributes,
     * including the average speed and total distance.
     *
     * @return A string representation of the DronesEntry object.
     */
    @Override
    public String toString() {
        return "ID: " + id + ", DroneType: " + droneType + ", Created: " + createdDate +
                ", Serial Number: " + serialNumber + ", Carriage Weight: " + carriageWeight +
                " kg, Carriage Type: " + carriageType +
                ", Average Speed: " + averageSpeed + " km/h, Total Distance: " + totalDistance + " km" ;
    }
}

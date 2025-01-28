package com.example.drone.Backend;

public class DroneDynamicEntry {
    private final String timestamp;
    private final int speed;
    private final double alignRoll;
    private final double controlRange; // New field for control range
    private final double alignYaw;
    private final double longitude;
    private final double latitude;
    private final int batteryStatus; // Battery value out of 1000
    private final String lastSeen; // New field for the last seen timestamp
    private final String status;
    private final String id;  // Add the ID field


    private int batteryPercentage; // Battery percentage (calculated)

    // Constructor to initialize all fields
    public DroneDynamicEntry(String id, String timestamp, int speed, double alignRoll, double alignYaw,
                             double longitude, double latitude, int batteryStatus, String status, String lastSeen,
                             double controlRange) {
        this.id = id;
        this.timestamp = timestamp;
        this.speed = speed;
        this.alignRoll = alignRoll;
        this.controlRange = controlRange;
        this.alignYaw = alignYaw;
        this.longitude = longitude;
        this.latitude = latitude;
        this.batteryStatus = batteryStatus;
        this.status = status;
        this.lastSeen = lastSeen;
        this.batteryPercentage = calculateBatteryPercentage(batteryStatus); // Calculate percentage on creation
    }

    // Method to calculate battery percentage
    private int calculateBatteryPercentage(int batteryStatus) {
        return (batteryStatus * 100) / 10000; // Normalize to a percentage
    }

    // Getters for all fields

    public String getId() {
        return id;
    }
    public String getTimestamp() {
        return timestamp;
    }

    public int getSpeed() {
        return speed;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public int getBatteryStatus() {
        return batteryStatus;
    }

    public String getStatus() {
        return status;
    }

    public double getAlignRoll() {
        return alignRoll;
    }

    public double getAlignYaw() {
        return alignYaw;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public double getControlRange() {
        return controlRange;
    }

    // Getter for battery percentage
    public int getBatteryPercentage() {
        return batteryPercentage;
    }

    // Setter for battery percentage (if recalculation is needed)
    public void setBatteryPercentage(int batteryStatus) {
        this.batteryPercentage = calculateBatteryPercentage(batteryStatus);
    }

    // Override toString for easier debugging and logging
    @Override
    public String toString() {
        return  "id. " + id + ", timestamp: " + timestamp +  ", speed=" + speed + ", alignRoll=" + alignRoll + ", controlRange=" + controlRange + ", alignYaw=" + alignYaw + ", longitude=" + longitude + ", latitude=" + latitude + ", batteryStatus=" + batteryStatus + ", lastSeen='" + lastSeen +  ", status='" + status +  ", batteryPercentage=" + batteryPercentage + "%" ;
    }
}

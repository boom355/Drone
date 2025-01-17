package com.example.drone.Backend;

public class DroneDynamicEntry {
    private final String timestamp;
    private final int speed;
    private final double alignRoll;
    private final double controlRange;  // New field for control range
    private final double alignYaw;
    private final double longitude;
    private final double latitude;
    private final int batteryStatus;
    private final String lastSeen;  // New field for the last seen timestamp
    private final String status;

    // Constructor to initialize all fields, including lastSeen and controlRange
    public DroneDynamicEntry(String timestamp, int speed, double alignRoll, double alignYaw,
                             double longitude, double latitude, int batteryStatus, String status, String lastSeen,
                             double controlRange) {
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
    }

    // Getters for all fields
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
        return lastSeen;  // Getter for lastSeen
    }

    public double getControlRange() {
        return controlRange;  // Getter for controlRange
    }
}

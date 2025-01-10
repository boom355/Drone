package com.example.drone.Backend;


public class DroneDynamicEntry {
    private final String id;
    private final String timestamp;
    private final String drone;
    private final int speed;
    private final double alignmentRoll;
    private final double controlRange;
    private final double alignmentYaw;
    private final double longitude;
    private final double latitude;
    private final int batteryStatus;
    private final String lastSeen;
    private final String status;

    // Constructor to initialize the fields
    public DroneDynamicEntry(String id, String timestamp, String drone, int speed,
                             double alignmentRoll, double controlRange, double alignmentYaw,
                             double longitude, double latitude, int batteryStatus,
                             String lastSeen, String status) {
        this.id = id;
        this.timestamp = timestamp;
        this.drone = drone;
        this.speed = speed;
        this.alignmentRoll = alignmentRoll;
        this.controlRange = controlRange;
        this.alignmentYaw = alignmentYaw;
        this.longitude = longitude;
        this.latitude = latitude;
        this.batteryStatus = batteryStatus;
        this.lastSeen = lastSeen;
        this.status = status;
    }


    @Override
    public String toString() {
        return String.format("ID: %s, TimeStamp: %s, Drone: %s, Speed: %d km/h, Alignment Roll: %.2f, Control Range: %.2f km, Alignment Yaw: %.2f, Longitude: %.6f, Latitude: %.6f, Battery Status: %d%%, Last Seen: %s, Status: %s",
                id, timestamp, drone, speed, alignmentRoll, controlRange, alignmentYaw, longitude, latitude, batteryStatus, lastSeen, status);
    }
}

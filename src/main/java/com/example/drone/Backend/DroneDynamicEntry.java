package com.example.drone.Backend;

public class DroneDynamicEntry {
    private final String timestamp;
    private final int speed;
    private final double longitude;
    private final double latitude;
    private final int batteryStatus;
    private final String status;

    public DroneDynamicEntry(String timestamp, int speed, double longitude, double latitude, int batteryStatus, String status) {
        this.timestamp = timestamp;
        this.speed = speed;
        this.longitude = longitude;
        this.latitude = latitude;
        this.batteryStatus = batteryStatus;
        this.status = status;
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
}

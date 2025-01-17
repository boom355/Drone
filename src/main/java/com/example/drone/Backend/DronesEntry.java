package com.example.drone.Backend;

public class DronesEntry {
    private final String id;
    private final String droneType;
    private final String createdDate;
    private final String serialNumber;
    private final double carriageWeight;
    private final String carriageType;

    // Constructor
    public DronesEntry(String id, String droneType, String createdDate, String serialNumber, double carriageWeight, String carriageType) {
        this.id = id;
        this.droneType = droneType;
        this.createdDate = createdDate;
        this.serialNumber = serialNumber;
        this.carriageWeight = carriageWeight;
        this.carriageType = carriageType;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getDroneType() {
        return droneType;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public double getCarriageWeight() {
        return carriageWeight;
    }

    public String getCarriageType() {
        return carriageType;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", DroneType: " + droneType + ", Created: " + createdDate +
                ", Serial Number: " + serialNumber + ", Carriage Weight: " + carriageWeight +
                " kg, Carriage Type: " + carriageType;
    }
}

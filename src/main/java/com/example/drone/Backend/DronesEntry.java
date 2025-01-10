package com.example.drone.Backend;

public class DronesEntry {
    private final String id;
    private final String droneType;
    private final String createdDate;
    private final String serialNumber;
    private final double carriageWeight;
    private final String carriageType;

    public DronesEntry(String id, String droneType, String createdDate, String serialNumber, double carriageWeight, String carriageType) {
        this.id = id;
        this.droneType = droneType;
        this.createdDate = createdDate;
        this.serialNumber = serialNumber;
        this.carriageWeight = carriageWeight;
        this.carriageType = carriageType;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", DroneType: " + droneType + ", Created: " + createdDate +
                ", Serial Number: " + serialNumber + ", Carriage Weight: " + carriageWeight +
                " kg, Carriage Type: " + carriageType;
    }
}

package com.example.drone.Backend;

public class DroneTypesEntry {
    private final String id;
    private final String manufacturer;
    private final String typename;
    private final double weight;
    private final double maxSpeed;
    private final double batteryCapacity;
    private final double controlRange;
    private final double maxCarriage;

    public DroneTypesEntry(String id, String manufacturer, String typename, double weight, double maxSpeed, double batteryCapacity, double controlRange, double maxCarriage) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.typename = typename;
        this.weight = weight;
        this.maxSpeed = maxSpeed;
        this.batteryCapacity = batteryCapacity;
        this.controlRange = controlRange;
        this.maxCarriage = maxCarriage;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Manufacturer: " + manufacturer + ", Typename: " + typename +
                ", Weight: " + weight + " kg, Max Speed: " + maxSpeed + " km/h, Battery Capacity: " +
                batteryCapacity + " mAh, Control Range: " + controlRange + " km, Max Carriage: " + maxCarriage + " kg";
    }
}

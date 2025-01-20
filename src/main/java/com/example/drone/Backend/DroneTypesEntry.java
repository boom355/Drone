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

    public String getId(){return id; }

    public String getManufacturer(){return manufacturer; }

    public String getTypename(){return typename; }

    public double getWeight(){return weight; }

    public double getMaxSpeed(){return maxSpeed; }

    public double getBatteryCapacity(){return batteryCapacity; }

    public double getControlRange(){return controlRange; }

    public double getMaxCarriage(){return maxCarriage; }


    @Override
    public String toString() {
        return "ID: " + id + ", Manufacturer: " + manufacturer + ", Typename: " + typename +
                ", Weight: " + weight + " kg, Max Speed: " + maxSpeed + " km/h, Battery Capacity: " +
                batteryCapacity + " mAh, Control Range: " + controlRange + " km, Max Carriage: " + maxCarriage + " kg";
    }
}
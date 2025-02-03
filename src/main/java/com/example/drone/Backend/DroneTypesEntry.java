package com.example.drone.Backend;

/**
 * This class represents a drone type entry, containing the details about a specific drone type.
 * It stores information such as the manufacturer, type, weight, speed, battery capacity, and other drone characteristics.
 * <p>
 * The class is used to represent the properties of a drone type, including technical specifications such as weight, speed,
 * and battery capacity. Each instance corresponds to a specific drone type in the system.
 * </p>
 *
 * @author [Your Name]
 * @version 1.0
 */
public class DroneTypesEntry {

    // Fields representing the details of the drone type
    private final String id;
    private final String manufacturer;
    private final String typename;
    private final double weight;
    private final double maxSpeed;
    private final double batteryCapacity;
    private final double controlRange;
    private final double maxCarriage;

    /**
     * Constructs a DroneTypesEntry object with the specified parameters.
     *
     * @param id The unique identifier for the drone type.
     * @param manufacturer The manufacturer of the drone.
     * @param typename The name or type of the drone.
     * @param weight The weight of the drone type (in kilograms).
     * @param maxSpeed The maximum speed of the drone (in kilometers per hour).
     * @param batteryCapacity The battery capacity of the drone (in milliampere-hours).
     * @param controlRange The control range of the drone (in kilometers).
     * @param maxCarriage The maximum carriage weight of the drone (in kilograms).
     */
    public DroneTypesEntry(String id, String manufacturer, String typename, double weight,
                           double maxSpeed, double batteryCapacity, double controlRange, double maxCarriage) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.typename = typename;
        this.weight = weight;
        this.maxSpeed = maxSpeed;
        this.batteryCapacity = batteryCapacity;
        this.controlRange = controlRange;
        this.maxCarriage = maxCarriage;
    }

    /**
     * Gets the unique identifier of the drone type.
     *
     * @return The ID of the drone type.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the manufacturer of the drone type.
     *
     * @return The manufacturer name.
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Gets the type or name of the drone.
     *
     * @return The typename of the drone.
     */
    public String getTypename() {
        return typename;
    }

    /**
     * Gets the weight of the drone type.
     *
     * @return The weight of the drone (in kilograms).
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Gets the maximum speed of the drone type.
     *
     * @return The maximum speed of the drone (in kilometers per hour).
     */
    public double getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * Gets the battery capacity of the drone type.
     *
     * @return The battery capacity of the drone (in milliampere-hours).
     */
    public double getBatteryCapacity() {
        return batteryCapacity;
    }

    /**
     * Gets the control range of the drone type.
     *
     * @return The control range of the drone (in kilometers).
     */
    public double getControlRange() {
        return controlRange;
    }

    /**
     * Gets the maximum carriage weight that the drone type can carry.
     *
     * @return The maximum carriage weight (in kilograms).
     */
    public double getMaxCarriage() {
        return maxCarriage;
    }

    /**
     * Returns a string representation of the DroneTypesEntry object with all the drone type details.
     * <p>
     * The string contains the drone type's ID, manufacturer, typename, weight, maximum speed, battery capacity,
     * control range, and maximum carriage weight.
     * </p>
     *
     * @return A string that contains the drone type details.
     */
    @Override
    public String toString() {
        return "ID: " + id + ", Manufacturer: " + manufacturer + ", Typename: " + typename +
                ", Weight: " + weight + " kg, Max Speed: " + maxSpeed + " km/h, Battery Capacity: " +
                batteryCapacity + " mAh, Control Range: " + controlRange + " km, Max Carriage: " + maxCarriage + " kg";
    }
}

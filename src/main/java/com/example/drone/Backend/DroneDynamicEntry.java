package com.example.drone.Backend;

/**
 * Represents a dynamic data entry for a drone, containing real-time flight information.
 * <p>
 * This class stores various telemetry data such as speed, alignment, location, battery status, and control range.
 * It also includes calculated fields such as battery percentage for easier use.
 * </p>
 *
 * @author [Your Name]
 * @version 1.0
 */
public class DroneDynamicEntry {

    private final String id;            // Unique identifier for the drone
    private final String timestamp;     // Timestamp of data capture
    private final int speed;            // Speed of the drone
    private final double alignRoll;     // Roll alignment of the drone
    private final double alignYaw;      // Yaw alignment of the drone
    private final double longitude;     // Longitude position
    private final double latitude;      // Latitude position
    private final int batteryStatus;    // Battery status (out of 1000)
    private final String status;        // Current status of the drone
    private final String lastSeen;      // Last seen timestamp
    private final double controlRange;  // Control range of the drone
    private int batteryPercentage;      // Calculated battery percentage

    /**
     * Constructs a {@code DroneDynamicEntry} object with all telemetry data.
     *
     * @param id             The unique identifier for the drone.
     * @param timestamp      The timestamp of data recording.
     * @param speed          The current speed of the drone.
     * @param alignRoll      The roll alignment of the drone.
     * @param alignYaw       The yaw alignment of the drone.
     * @param longitude      The longitude coordinate of the drone.
     * @param latitude       The latitude coordinate of the drone.
     * @param batteryStatus  The battery status (value out of 1000).
     * @param status         The current operational status of the drone.
     * @param lastSeen       The last seen timestamp of the drone.
     * @param controlRange   The control range of the drone.
     */
    public DroneDynamicEntry(String id, String timestamp, int speed, double alignRoll, double alignYaw,
                             double longitude, double latitude, int batteryStatus, String status, String lastSeen,
                             double controlRange) {
        this.id = id;
        this.timestamp = timestamp;
        this.speed = speed;
        this.alignRoll = alignRoll;
        this.alignYaw = alignYaw;
        this.longitude = longitude;
        this.latitude = latitude;
        this.batteryStatus = batteryStatus;
        this.status = status;
        this.lastSeen = lastSeen;
        this.controlRange = controlRange;
        this.batteryPercentage = calculateBatteryPercentage(batteryStatus); // Calculate percentage on creation
    }

    /**
     * Calculates the battery percentage based on battery status value.
     *
     * @param batteryStatus The raw battery status value (out of 1000).
     * @return The battery percentage normalized to a scale of 0-100.
     */
    private int calculateBatteryPercentage(int batteryStatus) {
        return (batteryStatus * 100) / 10000; // Normalize to a percentage
    }

    // Getters for all fields

    /**
     * Gets the unique drone identifier.
     * @return The drone ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the timestamp when the data was recorded.
     * @return The timestamp string.
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Gets the speed of the drone.
     * @return The speed value.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Gets the longitude coordinate of the drone.
     * @return The longitude value.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Gets the latitude coordinate of the drone.
     * @return The latitude value.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Gets the raw battery status value.
     * @return The battery status (out of 1000).
     */
    public int getBatteryStatus() {
        return batteryStatus;
    }

    /**
     * Gets the operational status of the drone.
     * @return The status string.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Gets the roll alignment of the drone.
     * @return The roll alignment value.
     */
    public double getAlignRoll() {
        return alignRoll;
    }

    /**
     * Gets the yaw alignment of the drone.
     * @return The yaw alignment value.
     */
    public double getAlignYaw() {
        return alignYaw;
    }

    /**
     * Gets the last seen timestamp of the drone.
     * @return The last seen timestamp.
     */
    public String getLastSeen() {
        return lastSeen;
    }

    /**
     * Gets the control range of the drone.
     * @return The control range value.
     */
    public double getControlRange() {
        return controlRange;
    }

    /**
     * Gets the battery percentage (normalized from battery status).
     * @return The battery percentage value.
     */
    public int getBatteryPercentage() {
        return batteryPercentage;
    }

    /**
     * Updates and recalculates the battery percentage based on a new battery status value.
     *
     * @param batteryStatus The new battery status value.
     */
    public void setBatteryPercentage(int batteryStatus) {
        this.batteryPercentage = calculateBatteryPercentage(batteryStatus);
    }

    /**
     * Returns a string representation of the drone data entry for debugging and logging.
     * @return A formatted string containing all field values.
     */
    @Override
    public String toString() {
        return "id: " + id + ", timestamp: " + timestamp + ", speed=" + speed +
                ", alignRoll=" + alignRoll + ", controlRange=" + controlRange +
                ", alignYaw=" + alignYaw + ", longitude=" + longitude +
                ", latitude=" + latitude + ", batteryStatus=" + batteryStatus +
                ", lastSeen='" + lastSeen + "', status='" + status +
                "', batteryPercentage=" + batteryPercentage + "%";
    }
}

package com.example.drone.Backend;

/**
 * Custom exception class for handling errors related to drone data operations.
 * <p>
 * This exception is thrown when an error occurs while fetching or processing drone data.
 * It supports exception chaining to provide more details about the root cause of the issue.
 * </p>
 *
 * @author [Your Name]
 * @version 1.0
 */
public class DroneDataException extends Exception {

    /**
     * Constructs a new {@code DroneDataException} with the specified detail message.
     *
     * @param message The error message describing the exception.
     */
    public DroneDataException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code DroneDataException} with the specified detail message and cause.
     *
     * @param message The error message describing the exception.
     * @param cause   The underlying cause of the exception.
     */
    public DroneDataException(String message, Throwable cause) {
        super(message, cause);
    }
}

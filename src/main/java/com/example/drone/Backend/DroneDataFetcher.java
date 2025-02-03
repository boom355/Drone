package com.example.drone.Backend;

import java.util.List;

/**
 * Interface for fetching drone data from a specified source.
 * <p>
 * Implementing classes should define how drone data is retrieved, processed,
 * and potentially handled in case of errors.
 * </p>
 *
 * @author [Your Name]
 * @version 1.0
 */
public interface DroneDataFetcher {

    /**
     * Fetches drone data from the given URL.
     *
     * @param url The URL from which to fetch the drone data.
     * @return A list of {@link DroneDynamicEntry} objects representing the fetched data.
     * @throws DroneDataException If an error occurs while fetching the data.
     */
    List<DroneDynamicEntry> fetchDroneData(String url) throws DroneDataException;
}

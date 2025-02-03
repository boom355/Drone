package com.example.drone.Backend;

import java.util.List;

/**
 * Abstract class representing the core drone dynamics functionality.
 * This class provides common properties and an abstract method for fetching drone data.
 * Implementing classes should define how drone data is retrieved.
 *
 * <p>
 * It implements {@link DroneDataFetcher} and provides pagination support through
 * {@code nextPage} and {@code previousPage}.
 * </p>
 *
 * @author [Your Name]
 * @version 1.0
 */
public abstract class AbstractDroneDynamics implements DroneDataFetcher {

    /**
     * URL of the next page of drone data.
     */
    protected String nextPage;

    /**
     * URL of the previous page of drone data.
     */
    protected String previousPage;

    /**
     * Retrieves the URL of the next page of drone data.
     *
     * @return the URL of the next page, or {@code null} if there is no next page.
     */
    public String getNextPage() {
        return nextPage;
    }

    /**
     * Retrieves the URL of the previous page of drone data.
     *
     * @return the URL of the previous page, or {@code null} if there is no previous page.
     */
    public String getPreviousPage() {
        return previousPage;
    }

    /**
     * Fetches drone data from the given page URL.
     * Implementing classes must define how the data is retrieved and handled.
     *
     * @param pageUrl the URL of the page from which to fetch drone data.
     * @return a list of {@link DroneDynamicEntry} objects representing the drone data.
     * @throws DroneDataException if there is an error while fetching the data.
     */
    public abstract List<DroneDynamicEntry> fetchDroneData(String pageUrl) throws DroneDataException;
}

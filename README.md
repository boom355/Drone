 
Project Components  
The system consists of: 
• Backend (Handles data fetching, processing, and API communication) 
• Frontend (JavaFX-based UI for displaying drone data and managing navigation) 
• Exception Handling & Multi-threading (Ensures robustness and responsiveness) 
iii.  
User Interface (UI) 
In this phase of development, we made several improvements to the Graphical User Interface 
(GUI) of the Main Menu to enhance user experience and visual appeal. We incorporated a new 
color theme to make the application more engaging and professional. Additionally, we added 
icons to various buttons and menus, making navigation more intuitive for users. 
To further improve the aesthetics, we included a background image that aligns with the theme of 
drone monitoring. We also refined the button styling and layout, ensuring that elements are well
spaced and easy to interact with. These enhancements contribute to a more polished and user
friendly interface. 
iv. 
Drones Page 
Retrives a list of drones by calling the API and processing in JSON response. The data flow goes 
from the backend to the Frontend, it initializes the table columns and binds them to the 
redpective fiels in DronesEntry. 
The application employs multithreading to handle certain time-consuming tasks without 
blocking the main user interface (UI), ensuring that the application remains responsive. This is 
crucial for operations like fetching drone data and dynamics from an external API. These 
operations can take time, and using threads allows them to be executed asynchronously. 
During the development of the drone application, we encountered an issue where the 
calculation of average speed and total distance for the drones was producing the same results 
across all drones, which was not the expected behavior. 
To resolve this issue, I moved the calculation logic directly into the DroneDynamic class. By 
doing so, the calculations for average speed and total distance are now performed after the 
dynamic data for each drone has been fully fetched and processed. 
v. 
Dronetypes Page 
DroneTypes fetches drone data from a remote API and processes the response into a list of 
DroneTypesEntry objects. The service handles errors during the data-fetching and processing 
stages by catching exceptions and printing error messages to the console. The frontend, 
represented by the DroneTypesController, uses ObservableList to store and display the drone 
data in a TableView. The controller interacts with the backend to fetch the drone types and 
populate the table, with error handling in place to catch issues when loading the data. 
Additionally, the controller provides a refresh functionality to reload the data and a home 
navigation feature to return to the main menu. 
vi. 
DroneDynamics Page 
The drone dynamics management system that integrates several key functionalities. The core 
system is built around an abstract class, AbstractDroneDynamics, which provides common 
methods for handling drone data, such as pagination support through nextPage and 
previousPage, along with the retrieval of drone information via the fetchDroneData method. Error 
handling is a critical part of the system, with the use of a custom DroneDataException class to 
ensure that any issues during data fetching or processing are properly managed. This includes 
managing exceptions that might occur when fetching data from the API or processing the 
results. 
Additionally, the system performs several key calculations to provide actionable insights. It 
calculates the average speed of the drones, the average battery percentage, and the total 
distance traveled based on the drone's GPS coordinates. These calculations help provide 
meaningful analytics to users. The data is displayed in a table view with real-time updates, 
where the battery percentage is highlighted in red for drones below the low battery threshold 
(15%). The drone data is fetched dynamically from an external API, and users can navigate 
through paginated results with Next and Previous buttons. 
The system also includes a refresh feature that updates the displayed drone dynamics data in 
real-time, without requiring the user to manually reload the page. For improved user experience, 
the drone dynamics are displayed with detailed attributes, including timestamp, speed, GPS 
coordinates, and battery status. The interface is responsive, with disabled buttons indicating 
when no further data is available to load, and it handles errors gracefully, logging any issues that 
arise during data fetching or display. Overall, this milestone provides a robust, real-time, error
resistant solution for monitoring and analyzing drone operations. 
vii. 
Api Class  
The Api class utilizes stream-based file handling to interact with a remote API, fetch data via 
HTTP GET requests, and log the responses to a file. It reads API responses through a 
BufferedReader stream and writes them to a log file using a FileWriter stream, both of which 
allow for efficient handling of data. The class also includes functionality to retrieve and display 
stored API responses from the log file by reading it with a BufferedReader, demonstrating a 
complete implementation of file-based input/output (I/O) operations for logging and retrieval of 
API data. 
viii. 
Flow Chart 
ix. 
x. 
Additional Features 
Beyond the core functionalities, the system provides additional features for enhanced 
drone management: 
• Battery Capacity Display in %: Beyond the core functionalities, the system 
provides additional features for enhanced drone management 
• Low Battery Alerts: Alerts are generated when a drone’s battery level drops 
below 15%, ensuring that operators are notified in time for potential action. 
• Average Battery Capacity Calculation: The system calculates and displays the 
average battery capacity across all drones, providing insights into the overall 
health of the fleet. 
User Guide 
• Launch the application 
• Navigate the Main Menu -> to select a drone category 
• Drones Catalog → View metadata of registered drones. 
• Drone Types Catalog → Explore specifications of different drone models. 
• Drone Dynamics → Monitor real-time telemetry data. 
• Use the Load Drone Button to fetch drone data. 
• Use the Refresh as well the Load Drone Button to fetch the latest drone data. 
• Navigate through pages using "Previous" and "Next" buttons in the Drone 
Dynamics UI. 
• Low Battery Alerts pop up when a drone’s battery falls below 15%.

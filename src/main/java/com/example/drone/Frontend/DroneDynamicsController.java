package com.example.drone.Frontend;

import com.example.drone.Backend.DroneDynamics;
import com.example.drone.Backend.DroneDynamicEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DroneDynamicsController {

    @FXML
    private TableView<DroneDynamicEntry> droneTable;

    @FXML
    private TableColumn<DroneDynamicEntry, String> timestampColumn;

    @FXML
    private TableColumn<DroneDynamicEntry, Integer> speedColumn;

    @FXML
    private TableColumn<DroneDynamicEntry, Double> rollColumn;

    @FXML
    private TableColumn<DroneDynamicEntry, Double> yawColumn;

    @FXML
    private TableColumn<DroneDynamicEntry, Double> longitudeColumn;

    @FXML
    private TableColumn<DroneDynamicEntry, Double> latitudeColumn;

    @FXML
    private TableColumn<DroneDynamicEntry, Integer> batteryColumn;

    @FXML
    private TableColumn<DroneDynamicEntry, Integer> batteryPercentageColumn;

    @FXML
    private TableColumn<DroneDynamicEntry, String> statusColumn;

    @FXML
    private TableColumn<DroneDynamicEntry, String> lastSeenColumn;

    @FXML
    private TableColumn<DroneDynamicEntry, Double> rangeColumn;

    @FXML
    private Button loadDronesButton;

    @FXML
    private Button previousButton;

    @FXML
    private Button nextButton;

    @FXML
    private Text averageBatteryText; // For showing the average battery percentage

    private static final Logger logger = Logger.getLogger(DroneDynamicsController.class.getName());
    private final DroneDynamics droneDynamics = new DroneDynamics();
    private ObservableList<DroneDynamicEntry> observableList = FXCollections.observableArrayList();

    private static final String FIRST_PAGE_URL = "http://dronesim.facets-labs.com/api/dronedynamics/?format=json";

    @FXML
    public void initialize() {
        // Bind the TableView columns to the DroneDynamicEntry properties
        timestampColumn.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        speedColumn.setCellValueFactory(new PropertyValueFactory<>("speed"));
        rollColumn.setCellValueFactory(new PropertyValueFactory<>("alignRoll"));
        yawColumn.setCellValueFactory(new PropertyValueFactory<>("alignYaw"));
        longitudeColumn.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        latitudeColumn.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        batteryColumn.setCellValueFactory(new PropertyValueFactory<>("batteryStatus"));
        batteryPercentageColumn.setCellValueFactory(new PropertyValueFactory<>("batteryPercentage"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        lastSeenColumn.setCellValueFactory(new PropertyValueFactory<>("lastSeen"));
        rangeColumn.setCellValueFactory(new PropertyValueFactory<>("controlRange"));

        // Disable the previous button initially
        previousButton.setDisable(true);

        // Set the cell factory to change row color based on battery percentage
        batteryPercentageColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Integer batteryPercentage, boolean empty) {
                super.updateItem(batteryPercentage, empty);
                if (empty || batteryPercentage == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(batteryPercentage + "%");
                    if (batteryPercentage < 15) {
                        setStyle("-fx-background-color: red;");
                    } else {
                        setStyle(""); // Reset style for other rows
                    }
                }
            }
        });
    }

    @FXML
    private void onLoadDronesClicked() {
        try {
            loadDroneData(FIRST_PAGE_URL);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error loading drones", e);
        }
    }

    @FXML
    private void onRefreshClicked() {
        refreshData();
    }

    @FXML
    private void onNextClicked() {
        try {
            String nextPageUrl = droneDynamics.getNextPage();
            if (nextPageUrl != null) {
                loadDroneData(nextPageUrl);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error navigating to the next page", e);
        }
    }

    @FXML
    private void onPreviousClicked() {
        try {
            String previousPageUrl = droneDynamics.getPreviousPage();
            if (previousPageUrl != null) {
                loadDroneData(previousPageUrl);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error navigating to the previous page", e);
        }
    }

    @FXML
    private void onHomeClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/drone/MainMenu.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) loadDronesButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Main Menu");

            MainMenuController controller = loader.getController();
            controller.setStage(stage);

            stage.show();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to load Main Menu", e);
        }
    }

    private void loadDroneData(String pageUrl) {
        try {
            List<DroneDynamicEntry> droneData = droneDynamics.fetchDroneData(pageUrl);
            observableList = FXCollections.observableArrayList(droneData);
            droneTable.setItems(observableList);

            // Update the average battery percentage
            updateAverageBattery();
            updateButtonStates();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error loading drone data", e);
        }
    }

    private void refreshData() {
        try {
            logger.info("Refreshing drone data...");
            droneDynamics.refreshData();
            List<DroneDynamicEntry> updatedData = droneDynamics.getDroneDynamicsList();
            observableList = FXCollections.observableArrayList(updatedData);
            droneTable.setItems(observableList);
            updateAverageBattery();
            logger.info("Data refreshed successfully.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error refreshing data", e);
        }
    }

    private void updateButtonStates() {
        previousButton.setDisable(droneDynamics.getPreviousPage() == null);
        nextButton.setDisable(droneDynamics.getNextPage() == null);
    }

    private void updateAverageBattery() {
        double totalBattery = 0;
        int droneCount = observableList.size();

        for (DroneDynamicEntry drone : observableList) {
            totalBattery += drone.getBatteryPercentage();
        }

        double averageBattery = (droneCount > 0) ? totalBattery / droneCount : 0;
        averageBatteryText.setText("Average Battery: " + String.format("%.2f", averageBattery) + "%");
    }
}

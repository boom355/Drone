// DronesApplication.java

package com.example.drone.Frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DronesApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Load FXML file from the correct path
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/drone/DronesCatalog.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Drone Management");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

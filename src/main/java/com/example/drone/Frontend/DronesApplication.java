package com.example.drone.Frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DronesApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/drone/DronesCatalog.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

       // Set the controller's stage
        MainMenuController controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setTitle("Drone Management");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

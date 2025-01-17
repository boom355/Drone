package com.example.drone;

import com.example.drone.Frontend.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Load the Main Menu FXML
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenuApplication.class.getResource("/com/example/drone/MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        // Pass the stage to the MainMenuController
        MainMenuController controller = fxmlLoader.getController();
        controller.setStage(stage);  // Pass the stage to the MainMenuController

        // Set the scene and show the stage
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

package com.example.drone.Frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/drone/MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        // Set the stage for the main menu
        MainMenuController controller = fxmlLoader.getController();
        controller.setStage(stage);

        stage.setScene(scene);
        stage.setTitle("Main Menu");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

package com.example.drone.Frontend;

public class Main {
    public static void main(String[] args) {
        System.out.println("Launching Drone Application...");

        // Launch the JavaFX application for the Main Menu
        MainMenuApplication.launch(MainMenuApplication.class, args);

        System.out.println("Drone Application closed.");
    }
}

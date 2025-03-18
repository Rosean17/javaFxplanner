package com.example.studyplanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/studyplanner/views/login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 300);
            primaryStage.setTitle("Study Planner - Login");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Error loading FXML file. Check the file path!");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

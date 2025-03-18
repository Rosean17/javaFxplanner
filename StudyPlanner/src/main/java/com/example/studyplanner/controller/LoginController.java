package com.example.studyplanner.controller;

import com.example.studyplanner.utils.FileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    // Handles Login Action
    @FXML
    private void onLoginAction(ActionEvent event) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        // Check if fields are empty
        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Please enter both username and password.");
            return;
        }

        // Validate user from file
        if (FileHandler.validateUser(username, password)) {
            messageLabel.setText("Login successful!");
            loadToDoScreen(); // Redirect to To-Do List
        } else {
            messageLabel.setText("Invalid username or password.");
        }
    }

    // Opens Register Screen
    @FXML
    private void onRegisterAction(ActionEvent event) {
        loadRegisterScreen();
    }

    // Loads the To-Do List Screen
    private void loadToDoScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/studyplanner/views/todo.fxml"));
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Study To-Do List");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Loads the Register Screen
    private void loadRegisterScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/studyplanner/views/register.fxml"));
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Register");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

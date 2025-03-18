package com.example.studyplanner.controller;

import com.example.studyplanner.utils.FileHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RegisterController {

    @FXML
    private TextField newUsernameField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Button registerButton;
    @FXML
    private Button backButton;
    @FXML
    private Label messageLabel;

    // Handle registration logic
    @FXML
    private void handleRegister() {
        String username = newUsernameField.getText().trim();
        String password = newPasswordField.getText().trim();
        String confirmPassword = confirmPasswordField.getText().trim();

        // Validation: Check if fields are empty
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            messageLabel.setText("All fields are required!");
            return;
        }

        // Validation: Check if passwords match
        if (!password.equals(confirmPassword)) {
            messageLabel.setText("Passwords do not match!");
            return;
        }

        // Check if user already exists
        if (FileHandler.userExists(username)) {
            messageLabel.setText("Username already taken!");
            return;
        }

        // Save the new user
        boolean success = FileHandler.saveUser(username, password);
        if (success) {
            messageLabel.setText("Registration successful!");
        } else {
            messageLabel.setText("Error: Could not save user.");
        }
    }

    // Handle going back to login screen
    @FXML
    private void goBack() {
        messageLabel.getScene().getWindow().hide(); // Closes the registration window
    }
}
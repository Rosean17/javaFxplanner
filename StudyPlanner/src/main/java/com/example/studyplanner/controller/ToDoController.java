package com.example.studyplanner.controller;

import com.example.studyplanner.utils.FileHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class ToDoController {

    @FXML private TextField taskField;
    @FXML private ListView<String> taskListView;
    @FXML private Button addButton;
    @FXML private Button deleteButton;
    @FXML private Button logoutButton;

    private ObservableList<String> taskList;
    private String currentUser;

    // Initialize the To-Do List
    public void initialize() {
        taskList = FXCollections.observableArrayList();
        taskListView.setItems(taskList);
    }

    // Set the current logged-in user and load their tasks
    public void setUser(String username) {
        this.currentUser = username;
        loadTasks();
    }

    // Load saved tasks from the file
    private void loadTasks() {
        List<String> savedTasks = FileHandler.loadTasks(currentUser);
        taskList.setAll(savedTasks);
    }

    // Handle adding a task
    @FXML
    private void handleAddTask() {
        String task = taskField.getText().trim();
        if (!task.isEmpty()) {
            taskList.add(task);
            FileHandler.saveTask(currentUser, task);
            taskField.clear();
        } else {
            showAlert("Warning", "Task cannot be empty!");
        }
    }

    // Handle deleting a selected task
    @FXML
    private void handleDeleteTask() {
        String selectedTask = taskListView.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            taskList.remove(selectedTask);
            FileHandler.deleteTask(currentUser, selectedTask);
        } else {
            showAlert("Warning", "Please select a task to delete!");
        }
    }

    // Handle logging out
    @FXML
    private void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/studyplanner/views/login.fxml"));
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setScene(new Scene(loader.load(), 400, 300));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Show alert message
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

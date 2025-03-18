package com.example.studyplanner.utils;

import java.io.*;
import java.util.*;

public class FileHandler {

    private static final String USERS_FILE = "data/users.txt";
    private static final String TASKS_FILE = "data/tasks.txt";

    // ------------------ USER AUTHENTICATION METHODS ------------------

    // Check if user exists
    public static boolean userExists(String username) {
        File file = new File(USERS_FILE);
        if (!file.exists()) return false;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] userData = scanner.nextLine().split(",");
                if (userData.length == 2 && userData[0].equals(username)) {
                    return true; // Username already exists
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Save a new user to users.txt
    public static boolean saveUser(String username, String password) {
        try {
            File file = new File(USERS_FILE);
            file.getParentFile().mkdirs(); // Ensure 'data/' folder exists

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
                bw.write(username + "," + password);
                bw.newLine();
            }
            return true; // Successfully saved user
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Failed to save user
        }
    }

    // Validate user login
    public static boolean validateUser(String username, String password) {
        File file = new File(USERS_FILE);
        if (!file.exists()) return false;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] userData = scanner.nextLine().split(",");
                if (userData.length == 2 && userData[0].equals(username) && userData[1].equals(password)) {
                    return true; // Login successful
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Login failed
    }

    // ------------------ TASK MANAGEMENT METHODS ------------------

    // Save a new task for a user
    public static boolean saveTask(String username, String taskDescription) {
        try {
            File file = new File(TASKS_FILE);
            file.getParentFile().mkdirs(); // Ensure 'data/' folder exists

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
                bw.write(username + "," + taskDescription);
                bw.newLine();
            }
            return true; // Successfully saved task
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Failed to save task
        }
    }

    // Load all tasks for a specific user
    public static List<String> loadTasks(String username) {
        List<String> tasks = new ArrayList<>();
        File file = new File(TASKS_FILE);
        if (!file.exists()) return tasks;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] taskData = scanner.nextLine().split(",", 2);
                if (taskData.length == 2 && taskData[0].equals(username)) {
                    tasks.add(taskData[1]); // Add task description to list
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    // Delete a specific task for a user
    public static boolean deleteTask(String username, String taskDescription) {
        File file = new File(TASKS_FILE);
        if (!file.exists()) return false;

        List<String> updatedTasks = new ArrayList<>();
        boolean removed = false;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] taskData = line.split(",", 2);
                if (taskData.length == 2 && taskData[0].equals(username) && taskData[1].equals(taskDescription)) {
                    removed = true; // Skip this task (delete)
                } else {
                    updatedTasks.add(line); // Keep other tasks
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (removed) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                for (String task : updatedTasks) {
                    bw.write(task);
                    bw.newLine();
                }
                return true; // Task successfully deleted
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false; // Task not found
    }
}

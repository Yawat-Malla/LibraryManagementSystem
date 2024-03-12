package org.example;

import java.sql.*;
import java.util.Scanner;

public class SignupManager {
    private static final Scanner scanner = new Scanner(System.in);

    public void signup() {
        System.out.print("Enter a new username: ");
        String newUsername = scanner.nextLine();

        System.out.print("Enter a new password: ");
        String newPassword = scanner.nextLine();

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:cyberreads.db");
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)")) {

            preparedStatement.setString(1, newUsername);
            preparedStatement.setString(2, newPassword);
            preparedStatement.executeUpdate();

            System.out.println("Signup successful! You can now login.");

        } catch (SQLException e) {
            System.out.println("Signup failed. The username may already be taken.");
            // You can handle different SQLExceptions for more specific error messages.
            // For simplicity, this example assumes the username is unique.
        }
    }
    static void initializeDatabase() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:cyberreads.db");
             Statement statement = connection.createStatement()) {

            // Create the users table if it doesn't exist
            String createUserTableQuery = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT NOT NULL UNIQUE," +
                    "password TEXT NOT NULL)";
            statement.executeUpdate(createUserTableQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

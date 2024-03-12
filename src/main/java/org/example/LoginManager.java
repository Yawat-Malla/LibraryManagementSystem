package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginManager {
    private static final Scanner scanner = new Scanner(System.in);

    public boolean login() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:cyberreads.db");
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("Login successful!");
                    return true;
                } else {
                    System.out.println("Login failed. Please check your username and password.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}


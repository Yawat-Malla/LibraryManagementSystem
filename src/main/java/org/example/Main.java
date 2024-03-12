package org.example;

import org.example.Models.Book;
import org.example.Models.BookHandler;

import java.util.List;
import java.util.Scanner;

import static org.example.SignupManager.initializeDatabase;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeDatabase();

        while (true) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    LoginManager loginManager = new LoginManager();
                    if (loginManager.login()) {
                        runApp();
                    }
                    break;
                case 2:
                    SignupManager signupManager = new SignupManager();
                    signupManager.signup();
                    break;
                case 3:
                    System.out.println("Exiting the Library Management System.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static void displayMainMenu() {
        System.out.println("\nLibrary Management System Menu:");
        System.out.println("1. Login");
        System.out.println("2. Signup");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }
    private static void runApp() {
        // Your existing code for the book management system goes here
        BookHandler bookHandler = new BookHandler();
        bookHandler.createTable();

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBook(bookHandler);
                    break;
                case 2:
                    displayBooks(bookHandler.getAllBooks());
                    break;
                case 3:
                    updateBookTitle(bookHandler);
                    break;
                case 4:
                    deleteBook(bookHandler);
                    break;
                case 5:
                    System.out.println("Exiting the Library Management System.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    private static void displayMenu() {
        System.out.println("\nLibrary Management System Menu:");
        System.out.println("1. Add a Book");
        System.out.println("2. Display All Books");
        System.out.println("3. Update Book Title");
        System.out.println("4. Delete a Book");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addBook(BookHandler bookHandler) {
        System.out.print("Enter the title: ");
        String title = scanner.nextLine();

        System.out.print("Enter the author: ");
        String author = scanner.nextLine();

        System.out.print("Enter the publication year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        Book newBook = new Book();
        newBook.setTitle(title);
        newBook.setAuthor(author);
        newBook.setYear(year);

        bookHandler.addBook(newBook);
        System.out.println("Book added successfully!");
    }

    private static void updateBookTitle(BookHandler bookHandler) {
        System.out.print("Enter the book ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter the new title: ");
        String newTitle = scanner.nextLine();

        bookHandler.updateBookTitle(id, newTitle);
        System.out.println("Book title updated successfully!");
    }

    private static void deleteBook(BookHandler bookHandler) {
        System.out.print("Enter the book ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        bookHandler.deleteBook(id);
        System.out.println("Book deleted successfully!");
    }

    private static void displayBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            System.out.println("Books in Cyberreads Library:");
            System.out.println("ID\tTitle\tAuthor\tYear");

            for (Book book : books) {
                System.out.println(book);
            }
        }
    }
}

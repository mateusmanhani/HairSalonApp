package util;

import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner = new Scanner(System.in);

    // Display main menu
    public void displayMainMenu() {
        System.out.println("\n=================================");
        System.out.println("    Welcome to the Barbershop    ");
        System.out.println("=================================");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("\nType 'exit' to quit the program");
        System.out.println("=================================");
    }

    public void displayCustomerMenu() {
        System.out.println("\n=== Customer Menu ===");
        System.out.println("1. Make a new booking");
        System.out.println("2. View my bookings");
        System.out.println("3. Cancel a booking");
        System.out.println("4. Modify a booking");
        System.out.println("Type 'back' to return to main menu");
    }

    public String getUserInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    }

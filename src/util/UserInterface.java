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

    public String getUserInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    }

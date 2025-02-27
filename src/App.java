
import models.*;
import util.*;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);






        // Simulate user login
        System.out.println("--- Welcome to the Barbershop Booking System");
        System.out.println("Enter username: ");
        String username = sc.nextLine();
        System.out.println("Enter password: ");
        String password = sc.nextLine();

        // simple login check
        if (!username.equals("user") || !password.equals("password")){
            System.out.println("invalid credentials");
        }

        System.out.println("\nLogin successfull! Welcome " + username + "!" );

        // Display services


        // Select Service

        // Display Barbers


        // Select barber


        // Display Barber time slots


        // Select time slot
        System.out.print("Select a time slot by number: ");
        int timeChoice = sc.nextInt();
        TimeSlot selectedTimeSlot = null;
        for (TimeSlot timeSlot : TimeSlot.values()) {
            if (timeSlot.getId() == timeChoice) {
                selectedTimeSlot = timeSlot;
                break;
            }
        }
        if (selectedTimeSlot == null) {
            System.out.println("Invalid time slot selection. Exiting...");
            return;
        }

        //  Make Booking

        // Confirm booking
        System.out.println("\n--- Booking Confirmation ---");
        System.out.println("Service: " + selectedService.getName());
        System.out.println("Barber: " + selectedBarber.getBarberName());
        System.out.println("Time: " + selectedTimeSlot.getTime());
        System.out.println("Total Price: $" + selectedService.getPrice());
        System.out.println("Thank you for your booking!");

        sc.close();



    }
}

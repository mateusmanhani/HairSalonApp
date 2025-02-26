
import models.*;
import util.*;
import util.Service;

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
        System.out.println("\n--- Available Services ---");
        for (Service service : Service.values()){
            System.out.println(service.getId() + ". " + service.getName() + "- $" + service.getPrice());
        }

        // Select Service
        System.out.println("Select a service by number: ");
        int serviceChoice  = sc.nextInt();
        Service selectedService = null;
        for(Service service: Service.values()){
            if(service.getId() == serviceChoice){
                selectedService = service;
                break;
            }
        }
        if (selectedService == null){
            System.out.println("Invalid service selection.");
        }

        // Display Barbers
        System.out.println("\n--- Available Barbers ---");
        for (Barber barber : Barber.values()) {
            System.out.println(barber.getId() + ". " + barber.getName());
        }

        // Select barber
        System.out.print("Select a barber by number: ");
        int barberChoice = sc.nextInt();
        Barber selectedBarber = null;
        for (Barber barber : Barber.values()) {
            if (barber.getId() == barberChoice) {
                selectedBarber = barber;
                break;
            }
        }
        if (selectedBarber == null) {
            System.out.println("Invalid barber selection. Exiting...");
            return;
        }

        // Display time slots
        System.out.println("\n--- Available Time Slots ---");
        for (TimeSlot timeSlot : TimeSlot.values()) {
            System.out.println(timeSlot.getId() + ". " + timeSlot.getTime());
        }

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

        // Confirm booking
        System.out.println("\n--- Booking Confirmation ---");
        System.out.println("Service: " + selectedService.getName());
        System.out.println("Barber: " + selectedBarber.getName());
        System.out.println("Time: " + selectedTimeSlot.getTime());
        System.out.println("Total Price: $" + selectedService.getPrice());
        System.out.println("Thank you for your booking!");

        sc.close();



    }
}

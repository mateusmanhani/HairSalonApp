package controller;

import models.*;
import util.DummyPopulator;
import util.CustomerRepository;
import util.TimeSlot;

import java.util.Scanner;

public class BookingController {
    private final DummyPopulator dm = new DummyPopulator();
    private final CustomerRepository customerRepository = new CustomerRepository();
    private final Barbershop barbershop = dm.createBarbershop(); // Create Barbershop with services and barbers already assigned
    private final Scanner scanner = new Scanner(System.in);
    private Customer currentCustomer = null;

    //main menu
    public void mainMenu(){
       while (true){
           displayMainMenu();
           String choice = getUserInput("\nEnter your choice: ");

           if (choice.equalsIgnoreCase("exit")){
               System.out.println("\nThank you for using our service. Goodbye!");
               break;
           }

           try{
               processMainMenuChoice(Integer.parseInt(choice));
           } catch (NumberFormatException e) {
               System.out.println("Please enter a valid number.");
           }
       }

    }

    // Display main menu
    private void displayMainMenu() {
        System.out.println("\n=================================");
        System.out.println("    Welcome to the Barbershop    ");
        System.out.println("=================================");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("\nType 'exit' to quit the program");
        System.out.println("=================================");
    }



    // Process register or login potion
    private void processMainMenuChoice(int choice) {
        switch (choice) {
            case 1:
                if (handleLogin()) {
                    handleBookingProcess();
                }
                break;
            case 2:
                handleRegistration();
                break;
            default:
                System.out.println("\nInvalid option. Please try again.");
        }
    }

    // Handle booking process
    private void handleBookingProcess(){
        // Step 1: Select service
        Service selectedService = handleServiceSelection();
        if (selectedService == null) return;

        // Step 2: Select Barber
        Barber selectedBarber = handleBarberSelection();
        if (selectedBarber == null) return;

        // Step 3: Select Time slot
        TimeSlot selectedSlot = handleTimeSlotSelection(selectedBarber);
        if (selectedSlot == null) return;

        // Step 4: Process payment and Make booking
        if (processPayment()){
            createBooking(selectedService,selectedBarber,selectedSlot);
        }
    }

    // Handle Service selection
    private Service handleServiceSelection(){
        while (true){
            barbershop.displayServices();

            try{
                String choice = getUserInput("\nEnter service number (or 'back' to return): ");

                if (choice.equalsIgnoreCase("back")) return null;

                int serviceId = Integer.parseInt(choice);
                for (Service service : barbershop.getServicesProvided()){
                    if (service.getServiceId() == serviceId){
                        System.out.println("Selected service: " + service.getServiceName());
                        return service;
                    }
                }
                System.out.println("Invalid Service ID. Please try again.");

            } catch (NumberFormatException e){
                System.out.println("Please enter a valid number.");
            }
        }


    }
    // Handle Barber selection
    private Barber handleBarberSelection(){
        while (true){
            barbershop.displayBarbers();

            try{
                String choice = getUserInput("\nEnter barber number (or 'back' to return): ");
                if (choice.equalsIgnoreCase("back")) return null;

                int barberId = Integer.parseInt(choice);
                for (Barber barber: barbershop.getBarbers()){
                    if (barber.getBarberId() == barberId){
                        System.out.println("Selected Barber: " +barber.getBarberName());
                        return barber;
                    }
                }
                System.out.println("Invalid Id. Please try again.");
            } catch (NumberFormatException e){
                System.out.println("PleaseEnter a valid number");
            }
        }
    }

    // handle slotSelection
    private TimeSlot handleTimeSlotSelection(Barber barber) {
        while (true) {
            System.out.println("\n=== Available Time Slots ===");
            barber.displaySlots();

            try {
                String choice = getUserInput("\nEnter time slot number (or 'back' to return): ");
                if (choice.equalsIgnoreCase("back")) return null;

                int slotId = Integer.parseInt(choice);
                for (TimeSlot slot : barber.getAvailability()) {
                    if (slot.getId() == slotId) {
                        System.out.println("Selected time: " + slot.getTime());
                        return slot;
                    }
                }
                System.out.println("Invalid time slot. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
    // Handle login
    public boolean handleLogin(){
        String username = getUserInput("\nEnter your username without spaces: ");
        String password = getUserInput("\nEnter password: ");

        Customer authenticatedCustomer = authenticateCustomer(username,password);

        if(authenticatedCustomer == null){
            System.out.println("Login Unsuccessful.");
            return false;
        } else{
            System.out.println("Login successful. Go ahead.");
            this.currentCustomer = authenticatedCustomer;
            return true;
        }
    }

    // Handle Registration
    public void handleRegistration(){
        String fulName = getUserInput("\nEnter your full name: ");
        String username = getUserInput("\nEnter you username without spaces: ");
        String password = getUserInput("\n Enter password: ");
        String confirmPassword = getUserInput("\n Confirm password: ");

        // Check if passwords match
        if (!password.equals(confirmPassword)){
            System.out.println("\nError: Passwords do not match. Registration failed.");
            return;
        }

        // Attempt to register user
        boolean registered = registerCustomer(username,password,fulName);

        if (registered) {
            System.out.println("\nRegistration successful! Please login to continue.");
        } else {
            System.out.println("\nRegistration failed. Username already exists.");
        }
    }

    // User sign up
    private boolean registerCustomer(String username, String password, String fullName){
            Customer customer = customerRepository.findByUsername(username);
            if (customer == null){
                int newCustomerId = (int) (Math.random()*100);
                Customer newCustomer = new Customer(newCustomerId,fullName,username,password);
                customerRepository.addCustomer(newCustomer);
                return true;
            }else {
                return false;
            }
    }

    // User login
    public Customer authenticateCustomer(String username, String password) {
        Customer customer = customerRepository.findByUsername(username);
        if (customer == null) {
            System.out.println("Customer not found with username: " + username);
            return null;
        }

        if (customer.getPassword().equals(password)) {
            return customer; // Return the customer if authentication is successful
        } else {
            return null; // Return null if password doesn't match
        }
    }

    private String getUserInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // process payment
    private boolean processPayment(){
        String cardPattern = "^[0-9]{16}$";
        System.out.println("--- Payment Processing ---");
        String cardNumber = getUserInput("Enter card number without spaces (16 digits): ");
        if (cardNumber.matches(cardPattern)){
            System.out.println("Payment Processed successfully!");
            return true;
        }

        System.out.println("Payment failed. Invalid card number.");
        return false;
    }

    // create booking booking
    private void createBooking(Service service, Barber barber, TimeSlot timeSlot){
        int bookingId = (int) (Math.random()*100);
        // Create a new booking
        Booking newBooking = new Booking(bookingId,barber.getBarberId(),currentCustomer.getUserId(),timeSlot, service.getServicePrice());

        // Add booking to customer bookings and to barber bookings
        currentCustomer.addBooking(newBooking);
        barber.addBooking(newBooking);

        // Print booking details for confirmation
        System.out.println("\n=== Booking Confirmation ===");
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Service: " + service.getServiceName());
        System.out.println("Barber: " + barber.getBarberName());
        System.out.println("Time: " + timeSlot.getTime());
        System.out.println("Price: $" + service.getServicePrice());
        System.out.println("Thank you for your booking!");
    }
    
}

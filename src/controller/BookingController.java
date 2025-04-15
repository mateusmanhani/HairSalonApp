package controller;

import models.*;
import util.DummyPopulator;
import util.UserRepository;
import util.TimeSlot;
import util.UserInterface;

import java.util.List;

public class BookingController {
    private final DummyPopulator dm = new DummyPopulator();
    private final UserRepository userRepository = new UserRepository();
    private final Barbershop barbershop = dm.createBarbershop(); // Create Barbershop with services and barbers already assigned
    private final UserInterface ui = new UserInterface();
    private Customer currentCustomer = null;

    //main menu
    public void mainMenu(){
       while (true){
           ui.displayMainMenu();
           String choice = ui.getUserInput("\nEnter your choice: ");

           if (choice.equalsIgnoreCase("exit")){
               ui.displayMessage("\nThank you for using our service. Goodbye!");
               break;
           }

           try{
               processMainMenuChoice(Integer.parseInt(choice));
           } catch (NumberFormatException e) {
               ui.displayMessage("Please enter a valid number.");
           }
       }

    }

    // Process register or login potion
    public void processMainMenuChoice(int choice) {
        switch (choice) {
            case 1:
                if (handleLogin()) {
                    handleCustomerMenu();
                }
                break;
            case 2:
                handleRegistration();
                break;
            default:
                ui.displayMessage("\nInvalid option. Please try again.");
        }
    }

    private void handleCustomerMenu() {
        while (true) {
            ui.displayCustomerMenu();
            String choice = ui.getUserInput("\nEnter your choice: ");

            if (choice.equalsIgnoreCase("back")) {
                return;
            }

            try {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        handleBookingProcess();
                        break;
                    case 2:
                        viewMyBookings();
                        break;
                    case 3:
                        cancelBooking();
                        break;
                    case 4:
                        modifyBooking();
                        break;
                    default:
                        ui.displayMessage("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                ui.displayMessage("Please enter a valid number.");
            }
        }
    }

    // Display user bookings
    public void viewMyBookings() {
        if (currentCustomer == null) {
            ui.displayMessage("Please login first.");
            return;
        }

        List<Booking> bookings = currentCustomer.getBookings();
        if (bookings.isEmpty()) {
            ui.displayMessage("You have no bookings.");
            return;
        }

        ui.displayMessage("\n=== Your Bookings ===");
        for (Booking booking : bookings) {
            displayBookingDetails(booking);
        }
    }

    private void displayBookingDetails(Booking booking) {
        Barber barber = barbershop.findBarberById(booking.getBarberId());
        Service service = barbershop.findServiceById(booking.getServiceId());

        ui.displayMessage("\nBooking ID: " + booking.getBookingId());
        ui.displayMessage("Service: " + (service != null ? service.getServiceName() : "Unknown"));
        ui.displayMessage("Barber: " + (barber != null ? barber.getFullName() : "Unknown"));
        ui.displayMessage("Time: " + booking.getTimeSlot().getTime());
        ui.displayMessage("Price: $" + booking.getBookingPrice());
    }

        public void cancelBooking() {
        if (currentCustomer == null) {
            ui.displayMessage("Please login first.");
            return;
        }

        List<Booking> bookings = currentCustomer.getBookings();
        if (bookings.isEmpty()) {
            ui.displayMessage("You have no bookings to cancel.");
            return;
        }

        viewMyBookings();
        int bookingId = Integer.parseInt(ui.getUserInput("Enter booking ID to cancel: "));

        Booking bookingToCancel = null;
        for (Booking booking : bookings) {
            if (booking.getBookingId() == bookingId) {
                bookingToCancel = booking;
                break;
            }
        }

        if (bookingToCancel != null) {
            // Remove from customer
            currentCustomer.cancelBooking(bookingId);

            // Remove from barber
            Barber barber = barbershop.findBarberById(bookingToCancel.getBarberId());
            if (barber != null) {
                barber.cancelBooking(bookingId);
            }

            // Free up the timeslot
            TimeSlot slot = bookingToCancel.getTimeSlot();
            barber.getAvailability().add(slot);

            ui.displayMessage("Booking cancelled successfully.");
        } else {
            ui.displayMessage("Invalid booking ID.");
        }
    }

    public void modifyBooking() {
        if (currentCustomer == null) {
            ui.displayMessage("Please login first.");
            return;
        }

        List<Booking> bookings = currentCustomer.getBookings();
        if (bookings.isEmpty()) {
            ui.displayMessage("You have no bookings to modify.");
            return;
        }

        viewMyBookings();
        int bookingId = Integer.parseInt(ui.getUserInput("Enter booking ID to modify: "));

        Booking bookingToModify = null;
        for (Booking booking : bookings) {
            if (booking.getBookingId() == bookingId) {
                bookingToModify = booking;
                break;
            }
        }

        if (bookingToModify == null) {
            ui.displayMessage("Invalid booking ID.");
            return;
        }

        // Free up the old timeslot
        Barber oldBarber = barbershop.findBarberById(bookingToModify.getBarberId());
        if (oldBarber != null) {
            oldBarber.getAvailability().add(bookingToModify.getTimeSlot());
        }

        // Get new booking details
        Service newService = handleServiceSelection();
        if (newService == null) return;

        Barber newBarber = handleBarberSelection();
        if (newBarber == null) return;

        TimeSlot newTimeSlot = handleTimeSlotSelection(newBarber);
        if (newTimeSlot == null) return;

        // Update the existing booking
        bookingToModify.setBarberId(newBarber.getUserId());
        bookingToModify.setServiceId(newService.getServiceId());
        bookingToModify.setTimeSlot(newTimeSlot);
        bookingToModify.setBookingPrice(newService.getServicePrice());

        // Remove the new timeslot from availability
        newBarber.getAvailability().remove(newTimeSlot);

        // Update in barber's bookings
        if (oldBarber != null && !oldBarber.equals(newBarber)) {
            oldBarber.cancelBooking(bookingId);
            newBarber.addBooking(bookingToModify);
        }

        ui.displayMessage("\nBooking updated successfully!");
        displayBookingDetails(bookingToModify);
    }

    // Handle booking process
    public void handleBookingProcess(){
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
    public Service handleServiceSelection(){
        while (true){
            barbershop.displayServices();

            try{
                String choice = ui.getUserInput("\nEnter service number (or 'back' to return): ");

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
    public Barber handleBarberSelection(){
        while (true){
            barbershop.displayBarbers();

            try{
                String choice = ui.getUserInput("\nEnter barber number (or 'back' to return): ");
                if (choice.equalsIgnoreCase("back")) return null;

                int barberId = Integer.parseInt(choice);
                for (Barber barber: barbershop.getBarbers()){
                    if (barber.getUserId() == barberId){
                        System.out.println("Selected Barber: " +barber.getUserId());
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
    public TimeSlot handleTimeSlotSelection(Barber barber) {
        while (true) {
            System.out.println("\n=== Available Time Slots ===");
            barber.displaySlots();

            try {
                String choice = ui.getUserInput("\nEnter time slot number (or 'back' to return): ");
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
        String username = ui.getUserInput("\nEnter your username without spaces: ");
        String password = ui.getUserInput("\nEnter password: ");

        Customer authenticatedCustomer = userRepository.authenticateCustomer(username,password);

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
        String fulName = ui.getUserInput("\nEnter your full name: ");
        String username = ui.getUserInput("\nEnter you username without spaces: ");
        String password = ui.getUserInput("\n Enter password: ");
        String confirmPassword = ui.getUserInput("\n Confirm password: ");

        // Check if passwords match
        if (!password.equals(confirmPassword)){
            System.out.println("\nError: Passwords do not match. Registration failed.");
            return;
        }

        // Attempt to register user
        boolean registered = userRepository.registerCustomer(username,password,fulName);

        if (registered) {
            System.out.println("\nRegistration successful! Please login to continue.");
        } else {
            System.out.println("\nRegistration failed. Username already exists.");
        }
    }

    // process payment
    public boolean processPayment(){
        String cardPattern = "^[0-9]{16}$";
        System.out.println("--- Payment Processing ---");
        String cardNumber = ui.getUserInput("Enter card number without spaces (16 digits): ");
        if (cardNumber.matches(cardPattern)){
            System.out.println("Payment Processed successfully!");
            return true;
        }

        System.out.println("Payment failed. Invalid card number.");
        return false;
    }

    // create booking booking
    public void createBooking(Service service, Barber barber, TimeSlot timeSlot){
        int bookingId = (int) (Math.random()*100);
        // Create a new booking
        Booking newBooking = barbershop.createBooking(
                bookingId,
                barber.getUserId(),
                service.getServiceId(),
                currentCustomer.getUserId(),
                timeSlot,
                service.getServicePrice());

        // Add booking to customer bookings
        currentCustomer.addBooking(newBooking);

        // Print booking details for confirmation
        System.out.println("\n=== Booking Confirmation ===");
        displayBookingDetails(newBooking);
    }
    
}

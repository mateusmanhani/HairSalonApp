package models;

import util.TimeSlot;

import java.util.List;

public class Barbershop {
    private String businessName;
    private List<Barber> barbers;
    private List<Service> servicesProvided;

    public Barbershop(String businessName, List<Barber> barbers, List<Service> servicesProvided) {
        this.businessName = businessName;
        this.barbers = barbers;
        this.servicesProvided = servicesProvided;
    }

    // display services with id, name and price
    public void displayServices(){
        System.out.println("\n--- Services Available ---");
        for(Service service : servicesProvided){
            service.printService();
        }
    }


    // display Barbers with id and name
    public void displayBarbers(){
        System.out.println("\n--- Barbers Available ---");
        for (Barber barber : barbers){
            barber.printBarberInfo();
        }
    }
    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public List<Barber> getBarbers() {
        return barbers;
    }

    public void setBarbers(List<Barber> barbers) {
        this.barbers = barbers;
    }

    public List<Service> getServicesProvided() {
        return servicesProvided;
    }

    public void setServicesProvided(List<Service> servicesProvided) {
        this.servicesProvided = servicesProvided;
    }

    // Helper method to find a barber by ID
    private Barber findBarberById(int barberId) {
        for (Barber barber : barbers) {
            if (barber.getBarberId() == barberId) {
                return barber;
            }
        }
        return null;
    }

    // Create a new barber
    public Barber createBarber(int id, String name) {
        Barber barber = new Barber(id, name);
        barber.populateAvl(); // Populate available time slots
        this.barbers.add(barber);
        return barber;
    }

    // Create a new service
    public Service createService(int id, String name, double price) {
        Service service = new Service(id, name, price);
        this.servicesProvided.add(service);
        return service;
    }

    // Create a new booking
    public Booking createBooking(int bookingId, int barberId, int customerId, TimeSlot timeSlot, double price) {
        Booking booking = new Booking(bookingId, barberId, customerId, timeSlot, price);
        // Add booking to the respective barber and customer
        Barber barber = findBarberById(barberId);
        if (barber != null) {
            barber.addBooking(booking);
        }
        return booking;
    }



}

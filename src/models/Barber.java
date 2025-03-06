package models;

import util.TimeSlot;

import java.util.ArrayList;
import java.util.List;

public class Barber extends User {

    private Integer barberId;
    private String barberName;
    private List<TimeSlot> availability;
    private List<Booking> bookings;

    public Barber(Integer barberId, String barberName, String username, String password) {
        super(barberId, barberName, username, password); // Call user constructor
        this.availability = new ArrayList<>();
        this.bookings = new ArrayList<>();
    }

    public Barber (Integer barberId, String barberName){
        super(barberId,barberName);
    }

    // Print availability list
    public void displaySlots(){
        System.out.println("\n--- Time slots available ---");
        for (TimeSlot slot : availability){
            System.out.println(slot.getId() + ", " + slot.getTime());
        }
    }

    // method to populate dummy availability
    public void populateAvl(){
        availability.add(TimeSlot.SLOT_1);
        availability.add(TimeSlot.SLOT_2);
        availability.add(TimeSlot.SLOT_3);
        availability.add(TimeSlot.SLOT_4);
    }

    public void printBarberInfo(){
        System.out.println(barberId + ", " + barberName);
    }

    public Integer getBarberId() {
        return barberId;
    }

    public void setBarberId(Integer barberId) {
        this.barberId = barberId;
    }

    public void setBarberName(String barberName){
        this.barberName = barberName;
    }

    public String getBarberName(){
        return barberName;
    }

    public List<TimeSlot> getAvailability() {
        return availability;
    }

    public void setAvailability(List<TimeSlot> availability) {
        this.availability = availability;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    // You can also add customer-specific methods here
    public void addBooking(Booking booking) {
        this.bookings.add(booking);
    }

    public void cancelBooking(Integer bookingId) {
        bookings.removeIf(booking -> booking.getBookingId().equals(bookingId));
    }
}

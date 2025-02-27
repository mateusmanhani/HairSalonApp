package models;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

    private List<Booking> bookings;

    public Customer(Integer customerId,String customerName, String username, String password) {
        super(customerId,customerName,username,password);
        this.bookings = new ArrayList<>();
    }

    // Customer-specific getters and setters
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

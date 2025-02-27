package models;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private Integer customerId;
    private String customerName;
    private String username;
    private String password;
    private List<Booking> bookings;

    public Customer(Integer customerId,String customerName, String username, String password) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.username = username;
        this.password = password;
        this.bookings = new ArrayList<>();
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}

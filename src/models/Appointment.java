package models;

import util.TimeSlot;

public class Appointment {

    private Integer bookingId;
    private Integer employeeId;
    private Integer customerId;
    private TimeSlot timeSlot;
    private double bookingPrice;

    public Appointment(Integer bookingId,Integer employeeId, Integer customerId, TimeSlot timeSlot, double bookingPrice) {
        this.bookingId = bookingId;
        this.employeeId = employeeId;
        this.customerId = customerId;
        this.timeSlot = timeSlot;
        this.bookingPrice = bookingPrice;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public double getBookingPrice() {
        return bookingPrice;
    }

    public void setBookingPrice(double bookingPrice) {
        this.bookingPrice = bookingPrice;
    }
}


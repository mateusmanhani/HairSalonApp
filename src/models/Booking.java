package models;

import util.TimeSlot;

public class Booking {

    private Integer bookingId;
    private Integer barberId;
    private Integer customerId;
    private Integer serviceId;
    private TimeSlot timeSlot;
    private double bookingPrice;

    public Booking(Integer bookingId, Integer barberId, Integer serviceId, Integer customerId, TimeSlot timeSlot, double bookingPrice) {
        this.bookingId = bookingId;
        this.barberId = barberId;
        this.serviceId = serviceId;
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

    public Integer getBarberId() {
        return barberId;
    }

    public void setBarberId(Integer barberId) {
        this.barberId = barberId;
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

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }
}


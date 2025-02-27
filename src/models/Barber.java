package models;

import util.TimeSlot;

import java.util.List;

public class Barber {

    private Integer barberId;
    private String barberName;
    private List<TimeSlot> availability;

    public Barber(Integer barberId, String barberName) {
        this.barberId = barberId;
        this.barberName = barberName;
    }

    // method to populate dummy availability
    public void populateAvl(){
        availability.add(TimeSlot.SLOT_1);
        availability.add(TimeSlot.SLOT_2);
        availability.add(TimeSlot.SLOT_3);
        availability.add(TimeSlot.SLOT_4);
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
}

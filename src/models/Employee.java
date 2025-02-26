package models;

import util.TimeSlot;

import java.util.List;

public class Employee {

    private String name;
    private List<TimeSlot> availability;


    public Employee(String name){
        this.name = name;
    }

    // method to populate dummy availability
    public void populateAvl(){
        availability.add(TimeSlot.SLOT_1);
        availability.add(TimeSlot.SLOT_2);
        availability.add(TimeSlot.SLOT_3);
        availability.add(TimeSlot.SLOT_4);
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public List<TimeSlot> getAvailability() {
        return availability;
    }

    public void setAvailability(List<TimeSlot> availability) {
        this.availability = availability;
    }
}

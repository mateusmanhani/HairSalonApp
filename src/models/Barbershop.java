package models;

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

    // populate barbershop with barbers and services

    // display services with id, name and price

    // display Barbers with id and name

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public List<Barber> getHairDressers() {
        return barbers;
    }

    public void setHairDressers(List<Barber> barbers) {
        this.barbers = barbers;
    }

    public List<Service> getServicesProvided() {
        return servicesProvided;
    }

    public void setServicesProvided(List<Service> servicesProvided) {
        this.servicesProvided = servicesProvided;
    }


}

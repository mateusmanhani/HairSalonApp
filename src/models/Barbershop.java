package models;

import java.util.List;

public class Barbershop {
    private String businessName;
    private List<Employee> employees;
    private List<Service> servicesProvided;

    public Barbershop(String businessName, List<Employee> employees, List<Service> servicesProvided) {
        this.businessName = businessName;
        this.employees = employees;
        this.servicesProvided = servicesProvided;
    }

    // populate barbershop with employees and services

    // display services with id, name and price

    // display Barbers with id and name

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public List<Employee> getHairDressers() {
        return employees;
    }

    public void setHairDressers(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Service> getServicesProvided() {
        return servicesProvided;
    }

    public void setServicesProvided(List<Service> servicesProvided) {
        this.servicesProvided = servicesProvided;
    }


}

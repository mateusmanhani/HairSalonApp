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


}

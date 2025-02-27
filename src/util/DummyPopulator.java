package util;

import models.Barber;
import models.Barbershop;
import models.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DummyPopulator {

    public Barbershop createBarbershop(){
        List<Barber> barbers = createBarberList();
        List<Service> services = createServicesList();

        return new Barbershop("HairStudio", barbers, services);

    }

    public List<Barber> createBarberList(){
        //  Create List of Barber objects
        List<Barber> barberList = new ArrayList<>();

        // String array with names for the barbers
        String[] names = {"John", "Jack", "Luke", "Aoife", "Peter"};

        int[] ids = {1,2,3,4,5};

        int nameIndex = 0;

        for( int id : ids){
            Barber newBarber = new Barber(id,names[nameIndex]);
            newBarber.populateAvl(); // populate list of available times for this barber
            barberList.add(newBarber); // Add barber to barber list
            nameIndex++; // move to the next name
        }

        return barberList;
    }

    public List<Service> createServicesList(){
        // Create list of Service objects
        List<Service> serviceList = new ArrayList<>();

        // Create array of service names
        String[] serviceNames = {"Haircut","Beard Trim","Hot Towel Shave","Eyebrows","Highlights"};

        // Create array of service prices
        double[] servicePrices = {25.0, 15.0, 20.0, 10.0, 50.0};

        int [] ids = {1,2,3,4,5};

        int nameIndex = 0;
        int priceIndex = 0;


        for(int id : ids){
            Service newService = new Service(id,serviceNames[nameIndex], servicePrices[priceIndex]);
            serviceList.add(newService);
            nameIndex++;
            priceIndex++;
        }

        return serviceList;


    }
}

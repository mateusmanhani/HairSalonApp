package util;

import models.Barber;
import models.Barbershop;
import models.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DummyPopulator {

    public Barbershop createBarbershop() {
        Barbershop barbershop = new Barbershop("HairStudio");

        // Create barbers
        String[] barberNames = {"John", "Jack", "Luke", "Aoife", "Peter"};
        int[] barberIds = {1, 2, 3, 4, 5};
        for (int i = 0; i < barberIds.length; i++) {
            barbershop.createBarber(barberIds[i], barberNames[i]);
        }

        // Create services
        String[] serviceNames = {"Haircut", "Beard Trim", "Hot Towel Shave", "Eyebrows", "Highlights"};
        double[] servicePrices = {25.0, 15.0, 20.0, 10.0, 50.0};
        int[] serviceIds = {1, 2, 3, 4, 5};
        for (int i = 0; i < serviceIds.length; i++) {
            barbershop.createService(serviceIds[i], serviceNames[i], servicePrices[i]);
        }

        return barbershop;
    }

}

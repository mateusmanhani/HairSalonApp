package util;

import models.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    private List<Customer> customerRepository = new ArrayList<>();

    public Customer findByUsername(String username){
        for (Customer customer : customerRepository){
            if( customer.getUsername().equals(username)){
                return customer;
            }
        }

        return null;

    }

    public void addCustomer(Customer customer){
        customerRepository.add(customer);
    }
}

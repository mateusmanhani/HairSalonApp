package util;

import models.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    private final List<Customer> customerRepository = new ArrayList<>();

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

    // You might want to add more methods like:
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customerRepository);
    }

    public Customer findById(Integer customerId) {
        for (Customer customer : customerRepository) {
            if (customer.getUserId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }
}

package util;

import models.Customer;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

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
    // Register a new customer
    public boolean registerCustomer(String username, String password, String fullName) {
        if (findByUsername(username) != null) {
            return false; // Username already exists
        }

        int newCustomerId = generateCustomerId();
        Customer newCustomer = new Customer(newCustomerId, fullName, username, password);
        addCustomer(newCustomer);
        return true;
    }

    // Authenticate a customer
    public Customer authenticateCustomer(String username, String password) {
        Customer customer = findByUsername(username);
        if (customer == null) {
            return null; // Customer not found
        }

        if (customer.getPassword().equals(password)) {
            return customer; // Authentication successful
        } else {
            return null; // Incorrect password
        }
    }

    // Helper method to generate a customer ID
    private int generateCustomerId() {
        return (int) (Math.random() * 100);
    }

}

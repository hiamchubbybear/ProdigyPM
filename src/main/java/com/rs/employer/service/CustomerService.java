package com.rs.employer.service;

import java.util.List;

import com.rs.employer.model.Customer;

// Service interface for customer
public interface CustomerService {
    // Add customer
    public Customer addCustomer(Customer customer);

    // List customer by ID
    public Customer listCustomerById(String username);

    // Delete customer by ID
    public Boolean deleteCustomerById(String username);

    // List all customer
    public List<Customer> listAllCustomer();

    // Update customer by ID
    public Customer updateCustomer(String username, Customer customer);

    // List customer by ID as user
    // public List<userdto> getUserData(Long ID);
    
    // Register user
    public Customer registerUser(String username, String password, String login);
}

package com.rs.employer.service;

import java.util.List;

import com.rs.employer.model.Customer;

// Service interface for customer
public interface CustomerService {
    // Add customer
    public Customer addCustomer(Customer customer);

    // List customer by ID
    public Customer listCustomerById(Long ID);

    // Delete customer by ID
    public Boolean deleteCustomerById(Long ID);

    // List all customer
    public List<Customer> listAllCustomer();

    // Update customer by ID
    public Customer updateCustomer(Long ID, Customer customer);

    // List customer by ID as user
    // public List<userdto> getUserData(Long ID);
    
    // Register user
    public Customer registerUser(String username, String password, String login);
}

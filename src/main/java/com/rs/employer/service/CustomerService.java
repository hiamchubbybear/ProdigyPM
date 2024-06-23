package com.rs.employer.service;

import java.util.List;

import com.rs.employer.model.Customer;

// Service interface for customer
public interface CustomerService {
    // Add customer
    public Customer addCustomer(Customer customer);

    // List customer by ID
    public Customer listCustomerById(Long userid);

    // Delete customer by ID
    public Boolean deleteCustomerById(Long userid);

    // List all customer
    public List<Customer> listAllCustomer();

    // Update customer by ID
    public Customer updateCustomer(Long userid, Customer customer);

    // List customer by ID as user
    // public List<userdto> getUserData(Long ID);
    
    // Register user
    public Customer registerUser(Long userid , String password, String login);
}

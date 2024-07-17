package com.rs.employer.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.rs.employer.model.Customer;
import com.rs.employer.model.Product;

// Service interface for customer
@Service
public interface CustomerService {
    // Add customer
    public Customer addCustomer(Customer customer);

    // List customer by ID
    public Customer listCustomerById(UUID id);

    // Delete customer by ID
    public Boolean deleteCustomerById(UUID id);

    // List all customer
    public List listAllCustomer();

    // Update customer by ID
    public Customer updateCustomer(UUID id, Customer customer);

    // List customer by ID as user
    // public List<userdto> getUserData(Long ID);
    public Customer updatePassword(UUID id, String pwd);

    // Register user
    public Customer registerUser(UUID id, String password, String login);

    public List<Product> getAll();
}

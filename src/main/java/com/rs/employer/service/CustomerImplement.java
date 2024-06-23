package com.rs.employer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.employer.dto.userdto;
import com.rs.employer.model.Customer;
import com.rs.employer.repository.CustomerRepository;

// Service for customer
@Service
public class CustomerImplement implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    // Add customer
    @Override
    public Customer addCustomer(Customer customer) {
        if (customer != null) {
            return customerRepository.save(customer);
        }
        throw new IllegalStateException(
                " Can not add customer with username: " + customer.getUsername() + " cause username is existed ");
    }

    // Update customer by ID
    @Override
    public Customer updateCustomer(String username, Customer customer) {
        Customer customer1 = customerRepository.getReferenceById(username);
        if (customer1 != null) {
            customer1.setName(customer.getName());
            customer1.setAddress(customer.getAddress());
            customer1.setUsername(customer.getUsername());
            customer1.setPassword(customer.getPassword());
            customer1.setRole(customer.getRole());
            customer1.setStatus(customer.getStatus());
            customer1.setBirthDay(customer.getBirthDay());
            return customerRepository.save(customer1);
        }
        throw new IllegalStateException("User data can not found");
    }

    // Delete customer by ID
    @Override
    public Boolean deleteCustomerById(String username) {
        if (customerRepository.existsById(username)) {
            Optional<Customer> eOptional = customerRepository.findById(username);
            Customer customer1 = eOptional.get();
            if (customer1.getId() != null) {
                customerRepository.deleteById(username);
                return true;
            }
        }
        return false;
    }

    // List customer by ID
    @Override
    public Customer listCustomerById(String username) {
        Optional<Customer> eOptional = customerRepository.findById(username);
        Customer customer1 = eOptional.get();
        if (customer1.getId() != null) {
            return customer1;
        } else
            throw new IllegalStateException("User have id:  " + username + " does not exist ");
    }

    // List customer dto by ID as user
    public userdto getUserData(String username) {
        Customer customer = listCustomerById(username);
        userdto userdto = new userdto(customer.getName(), customer.getAddress(), customer.getUsername(),
                customer.getRole(), customer.isGender(), customer.getStatus(), customer.getBirthDay());
        return userdto;
    }

    // Register user
    public Customer registerUser(String username, String password, String login) {
        if (username != null && password != null && login != null) {
            Customer customer = new Customer();
            customer.setUsername(username);
            customer.setPassword(password);
            return customerRepository.save(customer);
        } else
            return null;
    }

    // List all customer
    @Override
    public List<Customer> listAllCustomer() {
        return (List<Customer>) customerRepository.findAll();
    }

}

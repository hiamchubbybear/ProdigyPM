package com.rs.employer.serviceimplements;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.employer.dto.userdto;
import com.rs.employer.model.Customer;
import com.rs.employer.repository.CustomerRepo;
import com.rs.employer.service.CustomerService;

// Service for customer
@Service
public class CustomerImplement implements CustomerService {
    @Autowired
    private CustomerRepo customerRepository;

    // Add customer
    @Override
    public Customer addCustomer(Customer customer) {
        if (customer.getId() != null) {
            return customerRepository.save(customer);
        }
        throw new IllegalStateException(
                " Can not add customer with id: " + customer.getUsername()
                        + " cause id is existed ");
    }

    // Update customer by ID
    @Override
    public Customer updateCustomer(UUID id, Customer customer) {
        Customer customer1 = customerRepository.getReferenceById(id);
        if (customer1 != null) {
            customer1.setId(customer.getId());
            customer1.setUsername(customer.getUsername());
            customer1.setPassword(customer.getPassword());
            customer1.setName(customer.getName());
            customer1.setAddress(customer.getAddress());
            customer1.setRole(customer.getRole());
            customer1.setGender(customer.isGender());
            customer1.setStatus(customer.getStatus());
            customer1.setBirthDay(customer.getBirthDay());
            customerRepository.save(customer1);
        }
        throw new IllegalStateException("User data can not found");
    }

    // Delete customer by ID
    @Override
    public Boolean deleteCustomerById(UUID id) {
        if (customerRepository.existsById(id)) {
            Optional<Customer> eOptional = customerRepository.findById(id);
            Customer customer1 = eOptional.get();
            if (customer1.getId() != null) {
                customerRepository.deleteById(id);
                return true;
            }
        }
        return false;
    }

    // List customer by ID
    @Override
    public Customer listCustomerById(UUID id) {
        Optional<Customer> eOptional = Optional.ofNullable(
                customerRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("User not found")));
        Customer customer1 = eOptional.get();
        if (customer1.getId() != null) {
            return customer1;
        } else
            throw new IllegalStateException("User have id:  " + id + " does not exist ");
    }

    // List customer dto by ID as user
    public userdto getUserData(UUID id) {
        Customer customer = listCustomerById(id);
        userdto userdto = new userdto(customer.getName(), customer.getAddress(),
                customer.getUsername(), customer.getRole(), customer.isGender(), customer.getStatus(),
                customer.getBirthDay());
        return userdto;
    }

    // Register user
    public Customer registerUser(UUID id, String password, String login) {
        if (id != null && password != null && login != null) {
            Customer customer = new Customer();
            customer.setUsername(login);
            customer.setPassword(password);
            return customerRepository.save(customer);
        } else
            return null;
    }

    // List all customer
    @Override
    public List listAllCustomer() {
        return customerRepository.findAll();
    }
}

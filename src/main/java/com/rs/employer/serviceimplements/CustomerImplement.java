package com.rs.employer.serviceimplements;

import java.time.Instant;
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
    private Instant date;
    // ZoneId zone = ZoneId.of("Asia/HoChiMinh");

    // Add customer
    @Override
    public Customer addCustomer(Customer customer) {
        if (!customerRepository.existsByUsername(customer.getUsername())) {
            customer.setCreate(date.now());
            customer.setUpdate(date.now());
            return customerRepository.save(customer);
        }
        throw new IllegalStateException(
                " Can not add customer with id: " + customer.getUsername()
                        + " cause id is existed ");
    }

    // Update customer by ID
    @Override
    public Customer updateCustomer(UUID id, Customer customer) {
        Optional<Customer> customer1 = customerRepository.findById(id);
        Customer customer2 = customer1.get();
        customerRepository.deleteByUsername(customer.getUsername());
        if (customer1 != null) {
            customer2.setId(customer.getId());
            customer2.setAddress(customer.getAddress());
            customer2.setBirthDay(customer.getBirthDay());
            customer2.setGender(customer.isGender());
            customer2.setName(customer.getName());
            customer2.setPassword(customer.getPassword());
            customer2.setRole(customer.getRole());
            customer2.setStatus(customer.getStatus());
            customer2.setUpdate(date.now());
            return customerRepository.save(customer2);
        }

        throw new IllegalStateException("User " + customer.getUsername() + " not found");
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
        throw new IllegalStateException("Data binding " + "not found");
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
        if (customerRepository.existsById(id)) {
            Customer customer = listCustomerById(id);
            userdto userdto = new userdto(customer.getName(), customer.getAddress(),
                    customer.getUsername(), customer.getRole(), customer.isGender(), customer.getStatus(),
                    customer.getBirthDay());
            return userdto;
        } else
            throw new IllegalStateException("Customer ID" + id + "not found");
    }

    // Register user
    public Customer registerUser(UUID id, String password, String login) {
        if (id != null && password != null && login != null) {
            Customer customer = new Customer();
            customer.setUsername(login);
            customer.setPassword(password);
            return customerRepository.save(customer);
        } else
            throw new IllegalStateException("Your data input false ");
    }

    // List all customer
    @Override
    public List listAllCustomer() {
        return customerRepository.findAll();
    }
}

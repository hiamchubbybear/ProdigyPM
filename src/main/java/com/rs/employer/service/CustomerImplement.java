package com.rs.employer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.employer.dto.userdto;
import com.rs.employer.model.Customer;
import com.rs.employer.repository.CustomerRepository;

@Service
public class CustomerImplement implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer addCustomer(Customer customer) {
        if (customer != null) {
            return customerRepository.save(customer);
        }
        throw new IllegalStateException(
                " Can not add customer with username: " + customer.getUsername() + " cause username is existed ");
    }

    @Override
    public Customer updateCustomer(Long UserID, Customer customer) {
        Customer customer1 = customerRepository.getReferenceById(UserID);
        if (customer1 != null) {
            customer1.setId(UserID);
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

    @Override
    public Boolean deleteCustomerById(Long UserID) {
        if (customerRepository.existsById(UserID)) {
            Optional<Customer> eOptional = customerRepository.findById(UserID);
            Customer customer1 = eOptional.get();
            if (customer1.getId() != null) {
                customerRepository.deleteById(UserID);
                return true;
            }
        }
        return false;
    }

    @Override
    public Customer listCustomerById(Long UserID) {
        Optional<Customer> eOptional = customerRepository.findById(UserID);
        Customer customer1 = eOptional.get();
        if (customer1.getId() != null) {
            return customer1;
        } else
            throw new IllegalStateException("User have id:  " + UserID + " does not exist ");
    }
    public userdto getUserData(Long UserID) {
        Customer customer = listCustomerById(UserID);
        userdto userdto = new userdto( customer.getName(), customer.getAddress(), customer.getUsername(), customer.getRole(), customer.isGender(), customer.getStatus(), customer.getBirthDay());
        return userdto;
    }

    public Customer registerUser(String username, String password, String login) {
        if (username != null && password != null && login != null) {
            Customer customer = new Customer();
            customer.setUsername(username);
            customer.setPassword(password);
            return customerRepository.save(customer);
        } else
            return null;
    }

    @Override
    public List<Customer> listAllCustomer() {
        return (List<Customer>) customerRepository.findAll();
    }

}

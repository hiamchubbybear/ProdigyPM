package com.rs.employer.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rs.employer.apirespone.ApiRespone;
import com.rs.employer.dto.userdto;
import com.rs.employer.model.Customer;
import com.rs.employer.serviceimplements.CustomerImplement;

import jakarta.validation.Valid;

//Controller for customer
@RequestMapping(path = "/api/customer")
@RestController
@CrossOrigin
public class customercontroller {
    @Autowired
    private CustomerImplement customerImplement;

    // List all customer in database
    @GetMapping(path = "/all")
    public List<Customer> getAllUser() {
        List<Customer> list = customerImplement.listAllCustomer();
        return list;
    }

    // List customer by ID as user
    @GetMapping(path = "/userdto/{id}")
    public userdto getUserData(@PathVariable(name = "id") UUID id) {
        return customerImplement.getUserData(id);
    }

    // List customer by ID as administrator
    @GetMapping(path = "/getbyid/{id}")
    public Customer getPaticipateUser(@PathVariable(name = "id") UUID id) {
        return customerImplement.listCustomerById(id);
    }

    // Delete customer by ID
    @DeleteMapping(path = "/delete/{id}")
    public boolean deleteCustomer(@PathVariable(name = "id") UUID id) {
        return customerImplement.deleteCustomerById(id);
    }

    // Update customer by ID
    @PostMapping(path = "/update/{id}")
    public Customer updatCustomer(@PathVariable(name = "id") UUID id,
            @RequestBody Customer customer) {
        return customerImplement.updateCustomer(id, customer);
    }

    // Add customer
    @PutMapping(path = "/add")
    public ApiRespone<Customer> addCustomer(@RequestBody @Valid Customer customer) {
        ApiRespone<Customer> apirespone = new ApiRespone<Customer>();
        apirespone.setResult(customerImplement.addCustomer(customer));
        return apirespone;
    }

    // Register customer
    @PutMapping(path = "/register")
    public Customer registerCustomer(UUID id, String password, String login) {
        return customerImplement.registerUser(id, password, login);
    }

    // Hello world
    @GetMapping(path = "/hello")
    public String hello() {
        return "HelloWorld";
    }
}

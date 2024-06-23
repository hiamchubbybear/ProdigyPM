package com.rs.employer.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rs.employer.dto.userdto;
import com.rs.employer.model.Customer;
import com.rs.employer.service.CustomerImplement;

//Controller for customer
@RequestMapping(path = "/api/customer")
@RestController
@CrossOrigin
public class controller {
    @Autowired
    private CustomerImplement customerImplement;

    // List all customer in database
    @GetMapping(path = "/allcustomer")
    public List<Customer> getAllUser() {
        List<Customer> list = customerImplement.listAllCustomer();
        return list;
    }

    // List customer by ID as user
    @GetMapping(path = "/customertdata")
    public userdto getUserData(@RequestParam(name = "userid") Long userid) {
        return customerImplement.getUserData(userid);
    }

    // List customer by ID as administrator
    @GetMapping(path = "/getcustomerbyid")
    public Customer getPaticipateUser(@RequestParam(name = "userid") Long userid) {
        return customerImplement.listCustomerById(userid);
    }

    // Delete customer by ID
    @DeleteMapping(path = "/delete")
    public boolean deleteCustomer(@RequestParam(name = "userid") Long userid) {
        return customerImplement.deleteCustomerById(userid);
    }

    // Update customer by ID
    @PostMapping(path = "/update")
    public Customer updatCustomer(@RequestParam(name = "userid") Long userid,
            @RequestBody Customer customer) {
        return customerImplement.updateCustomer(userid, customer);
    }

    // Add customer
    @PutMapping(path = "/add")
    public void addCustomer(@RequestBody Customer customer) {
        customerImplement.addCustomer(customer);
    }

    // Register customer
    @PutMapping(path = "/register")
    public Customer registerCustomer(Long userid, String password, String login) {
        return customerImplement.registerUser(userid, password, login);
    }

    // Hello world
    @GetMapping(path = "/hello")
    public String hello() {
        return "HelloWorld";
    }
}

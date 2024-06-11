package com.rs.employer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rs.employer.model.Customer;
import com.rs.employer.service.CustomerImplement;

@RequestMapping(path = "/v1")
@RestController
public class controller {
    @Autowired
    private CustomerImplement customerImplement;

    @GetMapping(path = "/getallus")
    public List<Customer> getAllUser() {
        List<Customer> list = customerImplement.listAllCustomer();
        return list;

    }

    @GetMapping(path = "/get")
    public Customer getPaticipateUser(@RequestParam(name = "userid") Long UserID) {
        return customerImplement.listCustomerById(UserID);
    }

    @DeleteMapping(path = "/delete")
    public boolean deleteCustomer(@RequestParam(name = "userid") Long UserID) {
        return customerImplement.deleteCustomerById(UserID);
    }

    @GetMapping(path = "/update")
    public Customer updatCustomer(@RequestParam(name = "userid") Long UserID,
            @RequestBody Customer customer) {
        return customerImplement.updateCustomer(UserID, customer);
    }

    @GetMapping(path = "/add")
    public void addCustomer(@RequestBody Customer customer) {
        customerImplement.addCustomer(customer);
    }

    @GetMapping(path = "/register")
    public Customer registerCustomer(String username, String password, String login) {
        return customerImplement.registerUser(username, password, login);
    }

    @GetMapping(path = "/hello")
    public String hello() {
        return "HelloWorld";
    }

}

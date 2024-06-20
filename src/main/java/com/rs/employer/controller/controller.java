package com.rs.employer.controller;

import java.util.List;

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

@RequestMapping(path = "/v1")
@RestController
@CrossOrigin
public class controller {
    // public final CustomerRepository customerRepository;

    @Autowired
    private CustomerImplement customerImplement;

    @GetMapping(path = "/getallus")
    public List<Customer> getAllUser() {
        List<Customer> list = customerImplement.listAllCustomer();
        return list;

    }

    @PostMapping("/birth")
    public String postMethodName(@RequestBody String entity) {

        return entity;
    }

    @GetMapping(path = "/userdto")
    public userdto getUserData(@RequestParam(name = "userid") Long ID) {
        return customerImplement.getUserData(ID);
    }

    @GetMapping(path = "/get")
    public Customer getPaticipateUser(@RequestParam(name = "userid") Long UserID) {
        return customerImplement.listCustomerById(UserID);
    }

    @DeleteMapping(path = "/delete")
    public boolean deleteCustomer(@RequestParam(name = "userid") Long UserID) {
        return customerImplement.deleteCustomerById(UserID);
    }

    @PostMapping(path = "/update")
    public Customer updatCustomer(@RequestParam(name = "userid") Long UserID,
            @RequestBody Customer customer) {
        return customerImplement.updateCustomer(UserID, customer);
    }

    @PutMapping(path = "/add")
    public void addCustomer(@RequestBody Customer customer) {
        customerImplement.addCustomer(customer);
    }

    @PutMapping(path = "/register")
    public Customer registerCustomer(String username, String password, String login) {
        return customerImplement.registerUser(username, password, login);
    }

    @GetMapping(path = "/hello")
    public String hello() {
        return "HelloWorld";
    }

}

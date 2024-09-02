package com.rs.employer.controller.model;

import java.util.List;
import java.util.Optional;
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
import com.rs.employer.dto.Request.CustomerRequest;
import com.rs.employer.model.Customer;
import com.rs.employer.serviceimplements.CustomerService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

//Controller for customer
@RequestMapping(path = "/api/customer")
@RestController
@Slf4j
@CrossOrigin
public class customercontroller {
    @Autowired
    private CustomerService customerImplement;

    // List all customer in database
    @GetMapping(path = "/all")
    public ApiRespone<List<Customer>> getAllUser() {
        ApiRespone<List<Customer>> apiRespone = new ApiRespone<>();
        List<Customer> list = customerImplement.listAllCustomer();
        apiRespone.setData(list);
        return apiRespone;
    }

    // List customer by ID as administrator
    @GetMapping(path = "/getbyid/{id}")
    public ApiRespone<Customer> getPaticipateUser(@PathVariable UUID id) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(customerImplement.listCustomerById(id));
        return apiRespone;
    }

    @GetMapping("/getMyInfo")
    public ApiRespone<Optional<Customer>> getInfo() {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(customerImplement.getMyInfo());
        return apiRespone;

    }

    // Delete customer by ID
    @DeleteMapping(path = "/delete/{id}")
    public ApiRespone<Boolean> deleteCustomer(@PathVariable UUID id) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(customerImplement.deleteCustomerById(id));
        return apiRespone;
    }

    // Update customer by ID
    @PutMapping(path = "/update/{id}")
    public ApiRespone<Customer> updatCustomer(@PathVariable UUID id,
            @RequestBody CustomerRequest request) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(customerImplement.updateCustomer(id, request));
        return apiRespone;
    }

    // Add customer
    @PostMapping(path = "/add")
    public ApiRespone<Customer> addCustomer(@RequestBody @Valid CustomerRequest customer) {
        ApiRespone<Customer> apirespone = new ApiRespone<Customer>();
        apirespone.setData(customerImplement.addCustomer(customer));
        return apirespone;
    }

    // Hello world
    @GetMapping(path = "/hello")
    public ApiRespone<String> hello() {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData("HelloWorld");
        return apiRespone;
    }
}

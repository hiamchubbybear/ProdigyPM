package com.rs.employer.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.rs.employer.model.Customer;
import com.rs.employer.model.Product;
import com.rs.employer.serviceimplements.CustomerServiceImpl;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

//Controller for customer
@RequestMapping(path = "/api/customer")
@RestController
@CrossOrigin
public class customercontroller {
    @Autowired
    private CustomerServiceImpl customerImplement;

    // List all customer in database
    @GetMapping(path = "/all")
    public ApiRespone<List<Customer>> getAllUser() {
        ApiRespone<List<Customer>> apiRespone = new ApiRespone<>();
        List<Customer> list = customerImplement.listAllCustomer();
        apiRespone.setData(list);
        return apiRespone;
    }

    // List customer by ID as user
    // @GetMapping(path = "/userdto/{id}")
    // public ApiRespone<Userdto> getUserData(@PathVariable(name = "id") UUID id) {
    // ApiRespone apiRespone = new ApiRespone<>();
    // apiRespone.setData(customerImplement.getUserData(id));
    // return apiRespone;
    // }

    // List customer by ID as administrator
    @PreAuthorize("returnObject.username == authentication.name")
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
    public boolean deleteCustomer(@PathVariable UUID id) {
        return customerImplement.deleteCustomerById(id);
    }

    // Update customer by ID
    @PutMapping(path = "/update/{id}")
    public ApiRespone<Customer> updatCustomer(@PathVariable UUID id,
            @RequestBody Customer customer) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(customerImplement.updateCustomer(id, customer));
        return apiRespone;
    }

    // Add customer
    // @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "/add")
    public ApiRespone<Customer> addCustomer(@RequestBody @Valid Customer customer) {
        ApiRespone<Customer> apirespone = new ApiRespone<Customer>();
        apirespone.setData(customerImplement.addCustomer(customer));
        return apirespone;
    }

    // Register customer
    @PutMapping(path = "/register")
    public Customer registerCustomer(UUID id, String password, String login) {
        return customerImplement.registerUser(id, password, login);
    }

    @PutMapping(path = "/pwd")
    public ApiRespone<Customer> udpatePassword(@PathVariable UUID uuid, @RequestBody String password) {
        Customer apiCustomer = customerImplement.updatePassword(uuid, password);
        ApiRespone<Customer> cusApiRespone = new ApiRespone<>();
        cusApiRespone.setData(apiCustomer);
        return cusApiRespone;
    }

    // Hello world
    @GetMapping(path = "/hello")
    public ApiRespone<String> hello() {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData("HelloWorld");
        return apiRespone;
    }

    // @GetMapping(path = "/product")
    // public ApiRespone<List<Product>> helo() {
    // ApiRespone apiRespone = new ApiRespone<>();
    // apiRespone.setData(customerImplement.getAll());
    // return apiRespone;
    // }
    @GetMapping("/birth/{name}")
    public ApiRespone<Product> birthDayList(@PathParam("name") String name) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(customerImplement.findByName(name));
        return apiRespone;
    }

}

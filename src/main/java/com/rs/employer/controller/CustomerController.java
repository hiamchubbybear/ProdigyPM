package com.rs.employer.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.rs.employer.dto.Request.Register.RegisterRequest;
import com.rs.employer.dto.Respone.RegisterRespone;
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
import com.rs.employer.dto.Request.User.CustomerRequest;
import com.rs.employer.model.Customer;
import com.rs.employer.serviceimplements.CustomerService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
@RequestMapping(path = "/api/customer")
@RestController
@Slf4j
@CrossOrigin
public class CustomerController {
    @Autowired
    private CustomerService customerImplement;
    @GetMapping(path = "/all")
    public ApiRespone<List<Customer>> getAllUser() {
        ApiRespone<List<Customer>> apiRespone =
        new ApiRespone<>(customerImplement.listAllCustomer());
        return apiRespone;
    }
    @GetMapping(path = "/getbyid/{id}")
    public ApiRespone<Customer> getPaticipateUser(@PathVariable UUID id) {
        ApiRespone apiRespone = new ApiRespone<>
        (customerImplement.listCustomerById(id));
        return apiRespone;
    }

    @GetMapping("/getMyInfo")
    public ApiRespone<Optional<Customer>> getInfo() {
        ApiRespone apiRespone = new ApiRespone<>
        (customerImplement.getMyInfo());
        return apiRespone;
    }
    @DeleteMapping(path = "/delete/{id}")
    public ApiRespone<Boolean> deleteCustomer(@PathVariable UUID id) {
        customerImplement.deleteCustomerById(id);
        ApiRespone apiRespone = new ApiRespone<>(true);
        return apiRespone;
    }
    @PutMapping(path = "/update/{id}")
    public ApiRespone<Customer> updatCustomer(@PathVariable UUID id,
            @RequestBody CustomerRequest request) {
        ApiRespone apiRespone = new ApiRespone<>
        (customerImplement.updateCustomer(id, request));
        return apiRespone;
    }
    @PostMapping(path = "/add")
    public ApiRespone<Customer> addCustomer(@RequestBody @Valid CustomerRequest customer) {
        ApiRespone<Customer> apirespone = new ApiRespone<Customer>
        (customerImplement.addCustomer(customer));
        return apirespone;
    }
    @GetMapping(path = "/hello")
    public ApiRespone<String> hello() {
        ApiRespone apiRespone = new ApiRespone("Hello worlds");
        return apiRespone;
    }
    @GetMapping(path = "/getallandsortby/{value}")
    public ApiRespone<List<Customer>> getAllAndSort(
        @PathVariable(value = "value" ,required = true) String value) {
        ApiRespone apiRespone = new ApiRespone(customerImplement.listAllSort(value));
        return apiRespone;
    }
    @PostMapping(path = "/register")
    public ApiRespone<Customer> registerCustomer(@RequestBody RegisterRequest customer) {
        ApiRespone apiRespone = new ApiRespone(customerImplement.register(customer));
        return apiRespone;
    }
}

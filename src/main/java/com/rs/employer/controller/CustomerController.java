package com.rs.employer.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.nimbusds.jose.JOSEException;
import com.rs.employer.dao.CustomerRepo;
import com.rs.employer.dto.Request.ActivateRequestToken;
import com.rs.employer.dto.Request.ForgotAccountRequest;
import com.rs.employer.dto.Request.Register.RegisterRequest;
import com.rs.employer.dto.Respone.ActivateAccountRespone;
import com.rs.employer.dto.Respone.CustomerUpdateRespone;
import com.rs.employer.dto.Respone.ForgotAccountRespone;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.rs.employer.apirespone.ApiRespone;
import com.rs.employer.dto.Request.User.CustomerRequest;
import com.rs.employer.model.Customer;
import com.rs.employer.serviceimplements.CustomerService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping(path = "/api/customer")
@RestController
@Slf4j

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class CustomerController {
    @Autowired
    private CustomerService customerImplement;
    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping(path = "/all")
    public ApiRespone<List<Customer>> getAllUser() {
        return new ApiRespone<>(customerImplement.listAllCustomer());
    }
    @GetMapping(path = "/getbyid/{id}")
    public ApiRespone<Customer> getPaticipateUser(@PathVariable UUID id) {
        ApiRespone apiRespone = new ApiRespone<>
        (customerImplement.listCustomerById(id));
        return apiRespone;
    }

    @GetMapping("/getMyInfo")
    public ApiRespone<Optional<Customer>> getInfo() {
        return new ApiRespone<>
                (customerImplement.getMyInfo());
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
        return new ApiRespone<>
                (customerImplement.updateCustomer(id, request));
    }
    @PostMapping(path = "/add")
    public ApiRespone<Customer> addCustomer(@RequestBody @Valid CustomerRequest customer) {
        return  new ApiRespone<Customer>
                (customerImplement.addCustomer(customer));
    }
    @GetMapping(path = "/hello")
    public ApiRespone<String> hello() throws ParseException {
        customerRepo.updateStatus("admin");
    return new ApiRespone<>("Hello worlds");
    }
    @GetMapping(path = "/getallandsortby/{value}")
    public ApiRespone<List<Customer>> getAllAndSort(
        @PathVariable(value = "value" ,required = true) String value) {

        return  new ApiRespone(customerImplement.listAllSort(value));
    }
    @PostMapping(path = "/register")
    public ApiRespone<Customer> registerCustomer(@RequestBody RegisterRequest customer) {
        return new ApiRespone(customerImplement.register(customer));
    }
    @PutMapping(path = "/updateUser")
    public ApiRespone<Customer> updateCustomerData(@RequestBody CustomerUpdateRespone customer) {
        return new ApiRespone(customerImplement.customerRequest(customer));
    }
    @PostMapping(path = "/activate")
    public ApiRespone<ActivateAccountRespone> activateAccount(
            @RequestBody ActivateRequestToken request
    ) throws ParseException, JOSEException {
        if (request == null || request.getToken() == null) {
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
        }
        return new ApiRespone<>(customerImplement.activateRequest(request));
    }
    @PostMapping(path = "/resetpwd")
    public ApiRespone<ForgotAccountRespone> resetPassword(@RequestBody ForgotAccountRequest request) throws JOSEException {
        return new ApiRespone(customerImplement.forgotAccount(request));
    }
    @PostMapping("/upload")
    public ApiRespone<String> uploadImage(@RequestParam String username,@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        byte[] fileBytes = file.getBytes();
        customerRepo.updateCustomerImage(username, fileBytes );
        return new ApiRespone<>("Image uploaded successfully: " + fileName);
    }
}

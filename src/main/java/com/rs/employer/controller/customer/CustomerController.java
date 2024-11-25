package com.rs.employer.controller.customer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import com.nimbusds.jose.JOSEException;
import com.rs.employer.dao.customer.CustomerRepo;
import com.rs.employer.dto.Request.ActivateRequestToken;
import com.rs.employer.dto.Request.ForgotAccountRequest;
import com.rs.employer.dto.Request.Register.RegisterRequest;
import com.rs.employer.dto.Respone.*;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.model.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.metrics.SystemMetricsAutoConfiguration;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import com.rs.employer.apirespone.ApiRespone;
import com.rs.employer.dto.Request.User.CustomerRequest;
import com.rs.employer.serviceimplements.customer.CustomerService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping(path = "/api/customer")
@RestController
@Slf4j

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class CustomerController {
    private final CustomerService customerImplement;
    private final CustomerRepo customerRepo;
    private final SystemMetricsAutoConfiguration systemMetricsAutoConfiguration;

    @Autowired
    public CustomerController(CustomerService customerImplement, CustomerRepo customerRepo, SystemMetricsAutoConfiguration systemMetricsAutoConfiguration) {
        this.customerImplement = customerImplement;
        this.customerRepo = customerRepo;
        this.systemMetricsAutoConfiguration = systemMetricsAutoConfiguration;
    }

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
    public ApiRespone<CustomerInfoDTO> getInfo() {
        return new ApiRespone<>
                (customerImplement.getMyInfo());
    }
    @DeleteMapping(path = "/delete/{id}")
    public ApiRespone<Boolean> deleteCustomer(@PathVariable UUID id) {
        customerImplement.deleteCustomerById(id);
        ApiRespone apiRespone = new ApiRespone<>(true);
        return apiRespone;
    }
    @PutMapping(path = "/update")
    public ApiRespone<CustomerInfoDTO> updatCustomer(
            @RequestBody CustomerInfoDTO request) {
        return new ApiRespone<>
                (customerImplement.updateCustomer(request));
    }
    @PostMapping(path = "/add")
    public ApiRespone<Customer> addCustomer(@RequestBody @Valid CustomerRequest customer) {
        return  new ApiRespone<>
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
        if(!customerRepo.existsByUsername(username)) {
            throw new AppException(ErrorCode.USER_NOTFOUND);
        }else {
            customerRepo.updateCustomerImage(username, fileBytes);
            return new ApiRespone<>("Image uploaded successfully: " + fileName);
        }
    }
    @GetMapping(path = "/image")
    @PostAuthorize("#username == authentication.name")
    public ApiRespone<?> getImage(@RequestParam(name = "username") String username) throws IOException {
                String imageResource = Base64.getEncoder().encodeToString(customerImplement.userImage(username).getByteArray());
        return new ApiRespone<>(new ImageRespone(imageResource,username));
    }

}

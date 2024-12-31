package com.rs.employer.controller.customer;

import java.io.IOException;
import java.text.ParseException;
import java.util.Base64;
import java.util.List;

import com.nimbusds.jose.JOSEException;
import com.rs.employer.dao.customer.CustomerRepository;
import com.rs.employer.dto.CustomerAllInfoDTO;
import com.rs.employer.dto.Request.ActivateRequestToken;
import com.rs.employer.dto.Request.Register.RegisterRequest;
import com.rs.employer.dto.Response.*;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.model.customer.Customer;
import com.rs.employer.service.ProcessSecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.rs.employer.apirespone.ApiRespone;
import com.rs.employer.dto.Request.User.CustomerRequest;
import com.rs.employer.service.customer.customer.CustomerService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping(path = "/api/customer")
@RestController
@Slf4j

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class CustomerController {
    private final CustomerService customerImplement;
    private final CustomerRepository customerRepo;

    @Autowired
    public CustomerController(CustomerService customerImplement, CustomerRepository customerRepo) {
        this.customerImplement = customerImplement;
        this.customerRepo = customerRepo;
    }

    @GetMapping(path = "/all")
    public ApiRespone<List<CustomerAllInfoDTO>> getAllUser() {
        return new ApiRespone<>(customerImplement.listAllCustomer());
    }

    @GetMapping(path = "get")
    public ApiRespone<CustomerResponse> getPaticipateUser(@RequestParam String username) {
        return new ApiRespone<>(customerImplement.listCustomerById(username));
    }

    @GetMapping("/info")
    public ApiRespone<CustomerInfoDTO> getInfo() {
        return new ApiRespone<>
                (customerImplement.getMyInfo(ProcessSecurityContextHolder.getUsername(SecurityContextHolder.getContext())));
    }

    @DeleteMapping(path = "/delete")
    public ApiRespone<Boolean> deleteCustomer() {
        return new ApiRespone<>(customerImplement.deleteCustomerById(ProcessSecurityContextHolder.getUsername(SecurityContextHolder.getContext())));
    }

    @PutMapping(path = "/update")
    public ApiRespone<CustomerInfoDTO> updateCustomer(
            @RequestBody CustomerInfoDTO request , @RequestParam String username ) {
        return new ApiRespone<>
                (customerImplement.updateCustomer(request, username));
    }

    @PostMapping(path = "/add")
    public ApiRespone<Customer> addCustomer(@RequestBody @Valid CustomerRequest customer) {
        return new ApiRespone<>
                (customerImplement.addCustomer(customer));
    }

    @GetMapping(path = "/hello")
    public ApiRespone<String> hello() throws ParseException {
        customerRepo.updateStatus("admin");
        customerRepo.delete(customerRepo.findByUsername("admin").get());
        return new ApiRespone<>("Hello worlds");
    }

    @GetMapping(path = "/all/sort")
    public ApiRespone<List<Customer>> getAllAndSort(
            @RequestParam(value = "by", required = true) String value) {
        return new ApiRespone<>(customerImplement.listAllSort(value));
    }

    @PostMapping(path = "/register")
    public ApiRespone<RegisterRespone> registerCustomer(@RequestBody RegisterRequest customer) {
        return new ApiRespone<>(customerImplement.register(customer));
    }

    @PutMapping(path = "/user")
    public ApiRespone<Customer> updateCustomerData(@RequestBody CustomerUpdateResponse customer , @RequestParam String username) {
        return new ApiRespone<>(customerImplement.customerRequest(customer,username));
    }

    @PostMapping(path = "/activate")
    public ApiRespone<ActivateAccountResponse> activateAccount(
            @RequestBody ActivateRequestToken request
    ) throws ParseException, JOSEException {
        if (request == null || request.getToken() == null) {
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
        }
        return new ApiRespone<>(customerImplement.activateRequest(request));
    }


    @PostMapping(path = "/pwd")
    public ApiRespone<ForgotAccountRespone> resetPassword(@RequestParam String email) throws JOSEException {
        return new ApiRespone<>(customerImplement.forgotAccount(email));
    }

    @PostMapping("/img/upload")
    public ApiRespone<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String fileName = file.getOriginalFilename();
        byte[] fileBytes = file.getBytes();
        if (!customerRepo.existsByUsername(username)) {
            throw new AppException(ErrorCode.USER_NOTFOUND);
        } else {
            customerRepo.updateCustomerImage(username, fileBytes);
            return new ApiRespone<>("Image uploaded successfully: " + fileName);
        }
    }

    @GetMapping(path = "/image")
    public ApiRespone<?> getImage() throws IOException {
        String imageResource = Base64.getEncoder().encodeToString(customerImplement.userImage( ProcessSecurityContextHolder.getUsername(SecurityContextHolder.getContext())));
        return new ApiRespone<>(new ImageRespone(imageResource, SecurityContextHolder.getContext().getAuthentication().getName()));
    }

    @PostMapping(path = "/cfpwd/{passcode}/{email}")
    public ApiRespone<Boolean> confirmForgotPassword(@PathVariable String passcode, @PathVariable String email) {
        return new ApiRespone<>(customerImplement.confirmForgotPasswordCode(passcode, email));
    }

}

package com.rs.employer.service.customer;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

import com.nimbusds.jose.JOSEException;

import com.rs.employer.dto.Request.ActivateRequestToken;
import com.rs.employer.dto.Request.Register.RegisterRequest;
import com.rs.employer.dto.Response.*;
import com.rs.employer.model.customer.Customer;
import org.springframework.stereotype.Service;

import com.rs.employer.dto.Request.User.CustomerRequest;


@Service
public interface ICustomerService {
    public RegisterRespone register(RegisterRequest registerRequest);
    public Customer addCustomer(CustomerRequest customer );
    public CustomerResponse listCustomerById(String username);
    public boolean deleteCustomerById(String username);
    public List listAllCustomer();
    public Customer customerRequest(CustomerUpdateResponse request , String username);
    public CustomerInfoDTO updateCustomer(CustomerInfoDTO customer, String username);
    public Customer updatePassword(String username, String pwd);
    public List<Customer> listAllSort(String sort);
    public ActivateAccountResponse activateRequest(ActivateRequestToken treq) throws ParseException, JOSEException;
    public ForgotAccountRespone forgotAccount(String request) throws JOSEException;
    byte[] userImage(String username) throws IOException;
}

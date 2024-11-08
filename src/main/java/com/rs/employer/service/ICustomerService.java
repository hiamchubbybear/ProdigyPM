package com.rs.employer.service;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

import com.nimbusds.jose.JOSEException;

import com.rs.employer.dto.Request.ActivateRequestAccount;
import com.rs.employer.dto.Request.ActivateRequestToken;
import com.rs.employer.dto.Request.ForgotAccountRequest;
import com.rs.employer.dto.Request.Register.RegisterRequest;
import com.rs.employer.dto.Respone.*;
import com.rs.employer.model.Resources;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import com.rs.employer.dto.Request.User.CustomerRequest;
import com.rs.employer.model.Customer;

@Service
public interface ICustomerService {
    public RegisterRespone register(RegisterRequest registerRequest);
    public Customer addCustomer(CustomerRequest customer);
    public CustomerRespone listCustomerById(UUID id);
    public void deleteCustomerById(UUID id);
    public List listAllCustomer();
    public Customer customerRequest(CustomerUpdateRespone request);
    public Customer updateCustomer(UUID id, CustomerRequest customer);
    public Customer updatePassword(UUID id, String pwd);
    public Customer registerUser(UUID id, String password, String login);
    public List<Customer> listAllSort(String sort);
    public ActivateAccountRespone activateRequest(ActivateRequestToken treq) throws ParseException, JOSEException;
    public ForgotAccountRespone forgotAccount(ForgotAccountRequest request) throws JOSEException;
    public ByteArrayResource userImage(String username );
}

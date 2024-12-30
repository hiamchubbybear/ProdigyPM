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
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import com.rs.employer.dto.Request.User.CustomerRequest;


@Service
public interface ICustomerService {
    public RegisterRespone register(RegisterRequest registerRequest);
    public Customer addCustomer(CustomerRequest customer );
    public CustomerResponse listCustomerById(String id);
    public void deleteCustomerById(String id);
    public List listAllCustomer();
    public Customer customerRequest(CustomerUpdateRespone request);
    public CustomerInfoDTO updateCustomer(CustomerInfoDTO customer, String uuid);
    public Customer updatePassword(UUID id, String pwd);
    public List<Customer> listAllSort(String sort);
    public ActivateAccountResponse activateRequest(ActivateRequestToken treq) throws ParseException, JOSEException;
    public ForgotAccountRespone forgotAccount(String request) throws JOSEException;
    public byte[] userImage() throws IOException;
}

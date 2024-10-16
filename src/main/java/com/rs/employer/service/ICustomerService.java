package com.rs.employer.service;

import java.util.List;
import java.util.UUID;

import com.rs.employer.dto.Request.Register.RegisterRequest;
import com.rs.employer.dto.Respone.RegisterRespone;
import org.springframework.stereotype.Service;

import com.rs.employer.dto.Request.User.CustomerRequest;
import com.rs.employer.dto.Respone.CustomerRespone;
import com.rs.employer.model.Customer;

@Service
public interface ICustomerService {
    public RegisterRespone register(RegisterRequest registerRequest);
    public Customer addCustomer(CustomerRequest customer);
    public CustomerRespone listCustomerById(UUID id);
    public void deleteCustomerById(UUID id);
    public List listAllCustomer();
    public Customer updateCustomer(UUID id, CustomerRequest customer);
    public Customer updatePassword(UUID id, String pwd);
    public Customer registerUser(UUID id, String password, String login);
    public List<Customer> listAllSort(String sort);
}

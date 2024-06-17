package com.rs.employer.service;

import java.util.List;

import com.rs.employer.model.Customer;

public interface CustomerService {
    public Customer addCustomer(Customer customer);

    public Customer listCustomerById(Long ID);

    public Boolean deleteCustomerById(Long ID);

    public List<Customer> listAllCustomer();

    public Customer updateCustomer(Long ID, Customer customer);

    // public List<userdto> getUserData(Long ID);

    public Customer registerUser(String username, String password, String login);
}

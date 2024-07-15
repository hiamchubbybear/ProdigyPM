package com.rs.employer.serviceimplements;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.employer.dto.userdto;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.mapper.Mapping;
import com.rs.employer.model.Customer;
import com.rs.employer.repository.CustomerRepo;
import com.rs.employer.service.CustomerService;

// Service for customer
@Service
public class CustomerImplement implements CustomerService {
    @Autowired
    private CustomerRepo customerRepository;
    private Instant date;
    // ZoneId zone = ZoneId.of("Asia/HoChiMinh");
    @Autowired
    private Mapping userMapping;

    // Add customer
    @Override
    public Customer addCustomer(Customer customer) {
        if (!customerRepository.existsByUsername(customer.getUsername())) {
            customer.setCreate(date.now());
            customer.setUpdate(date.now());
            return customerRepository.save(customer);
        }
        throw new AppException(ErrorCode.USERNAME_EXISTED);
    }

    // Update customer by ID
    @Override
    public Customer updateCustomer(UUID id, Customer customer) {
        Optional<Customer> customer1 = customerRepository.findById(id);
        if (customer1.isPresent()) {
            Customer customer2 = userMapping.customerMapper(customer);
            customer2.setCreate(customer.getCreate());
            customer2.setUpdate(date.now());
            return customerRepository.save(customer2);
        }
        throw new AppException(ErrorCode.USER_NOTFOUND);
    }

    // Delete customer by ID
    @Override
    public Boolean deleteCustomerById(UUID id) {
        if (customerRepository.existsById(id)) {
            Optional<Customer> eOptional = customerRepository.findById(id);
            Customer customer1 = eOptional.get();
            if (customer1.getId() != null) {
                customerRepository.deleteById(id);
                return true;
            }
        }
        throw new AppException(ErrorCode.USER_NOTFOUND);
    }

    // List customer by ID
    @Override
    public Customer listCustomerById(UUID id) {
        Optional<Customer> eOptional = Optional.ofNullable(
                customerRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("User not found")));
        Customer customer1 = eOptional.get();
        if (customer1.getId() != null) {
            return customer1;
        } else
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
    }

    // List customer dto by ID as user
    public userdto getUserData(UUID id) {
        if (customerRepository.existsById(id)) {
            Customer customer = listCustomerById(id);
            userdto userdto = new userdto(customer.getName(), customer.getAddress(),
                    customer.getUsername(), customer.getRole(), customer.isGender(), customer.getStatus(),
                    customer.getBirthDay());
            return userdto;
        } else
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
    }

    // Register user
    public Customer registerUser(UUID id, String password, String login) {
        if (id != null && password != null && login != null) {
            Customer customer = new Customer();
            customer.setUsername(login);
            customer.setPassword(password);
            return customerRepository.save(customer);
        } else
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
    }

    // List all customer
    @Override
    public List listAllCustomer() {
        return customerRepository.findAll();
    }
}

package com.rs.employer.serviceimplements;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rs.employer.dto.userdto;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.model.Customer;
import com.rs.employer.model.Product;
import com.rs.employer.repository.CustomerRepo;
import com.rs.employer.service.CustomerService;

// Service for customer
@Service
public class CustomerImplement implements CustomerService {
    @Autowired
    private CustomerRepo customerRepository;
    private Instant date;

    // ZoneId zone = ZoneId.of("Asia/HoChiMinh");
    // Add customer
    @Override
    public Customer addCustomer(Customer customer) {
        if (customerRepository.existsByUsername(customer.getUsername())
                || customerRepository.existsByUserid(customer.getUserid()))
            throw new AppException(ErrorCode.USEREXISTED_OR_USERIDEXISTED);
        else {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(5);
            customer.setPassword(passwordEncoder.encode(customer.getPassword()));
            customer.setCreate(date.now());
            customer.setUpdate(date.now());
            // customer.setProducts(customerRepository.getAllProductDetail());
            return customerRepository.save(customer);
        }
    }

    @Override
    public Customer updateCustomer(UUID id, Customer customer) {
        Optional<Customer> customer1 = customerRepository.findById(id);
        if (customer1.isPresent()) {
            customerRepository.deleteById(id);
            Customer customer2 = customer1.get();
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(5);
            if (customer2 != null) {
                Customer customer3 = new Customer();
                // Customer customer2 = userMapping.customerMapper(customer);
                customer3.setUserid(customer.getUserid());
                customer3.setUsername(customer.getUsername());
                customer3.setPassword(passwordEncoder.encode(customer.getPassword()));
                customer3.setName(customer.getName());
                customer3.setAddress(customer.getAddress());
                customer3.setRole(customer.getRole());
                customer3.setGender(customer.isGender());
                customer3.setStatus(customer.getStatus());
                customer3.setUpdate(date.now());
                customer3.setCreate(customer2.getCreate());
                customer3.setBirthDay(customer.getBirthDay());
                return customerRepository.save(customer3);
            }
        }
        throw new AppException(ErrorCode.USER_NOTFOUND);
    }

    // Delete customer by ID
    @Override
    public Boolean deleteCustomerById(UUID id) {
        if (customerRepository.existsById(id)) {
            Optional<Customer> eOptional = customerRepository.findById(id);
            Customer customer1 = eOptional.get();
            if (customer1.getUserid() != null) {
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
                        .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND)));
        Customer customer1 = eOptional.get();
        if (customer1.getUserid() != null) {
            return customer1;
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
        return customerRepository.findAll(Sort.by("userid").ascending());
    }

    @Override
    public Customer updatePassword(UUID id, String password) {
        Optional<Customer> eCustomer = customerRepository.findById(id);
        if (eCustomer != null) {
            Customer customer1 = eCustomer.get();
            customer1.setPassword(password);
            customer1.setUpdate(date.now());
            return customerRepository.save(customer1);
        } else
            throw new AppException(ErrorCode.USERNAME_INVALID);
    }

    public List<Product> findByName(String name) {
        return customerRepository.findAllDepartment(name);
    }
}

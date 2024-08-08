package com.rs.employer.serviceimplements;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rs.employer.dto.Request.CustomerRequest;
import com.rs.employer.dto.Respone.CustomerRespone;
import com.rs.employer.enums.Role;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.mapper.CustomerMapper;
import com.rs.employer.model.Customer;
import com.rs.employer.model.Product;
import com.rs.employer.repository.CustomerRepo;
import com.rs.employer.service.CustomerService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

// Service for customer
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepository;
    private Instant date;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CustomerMapper mapper;

    // ZoneId zone = ZoneId.of("Asia/HoChiMinh");
    // Add customertomertoom
    @Override
    public Customer addCustomer(CustomerRequest customer, String role) {
        HashSet<String> role1 = new HashSet<>();
        if (customerRepository.existsByUsername(customer.getUsername()))
            throw new AppException(ErrorCode.USEREXISTED_OR_USERIDEXISTED);
        else {
            // PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(5);
            customer.setPassword(passwordEncoder.encode(customer.getPassword()));
            Customer customer1 = mapper.toCustomer(customer);
            customer1.setCreate(date.now());
            customer1.setUpdate(date.now());
            return customerRepository.save(customer1);
        }
    }

    @Override
    public Customer updateCustomer(UUID id, CustomerRequest customer) {
        Optional<Customer> customer1 = customerRepository.findById(id);
        if (customer1.isPresent()) {
            customerRepository.deleteById(id);
            Customer customer2 = customer1.get();
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(5);
            if (customer2 != null) {
                HashSet<String> role = new HashSet<>();
                role.add(Role.ADMIN.name());
                Customer customer3 = mapper.toCustomer(customer);
                customer3.setCreate(customer2.getCreate());
                customer3.setUpdate(date.now());
                return customerRepository.save(customer3);
            }
        }
        throw new AppException(ErrorCode.USER_NOTFOUND);
    }

    @Override
    public Boolean deleteCustomerById(UUID id) {
        if (customerRepository.existsById(id)) {
            Optional<Customer> eOptional = customerRepository.findById(id);
            Customer customer1 = eOptional.get();
            if (customer1.getUuid() != null) {
                customerRepository.deleteById(id);
                return true;
            }
        }
        throw new AppException(ErrorCode.USER_NOTFOUND);
    }

    // List customer by ID
    @Override
    @PostAuthorize("returnObject.username == authentication.name")
    public CustomerRespone listCustomerById(UUID id) {
        Optional<Customer> eOptional = Optional.ofNullable(
                customerRepository.findById(id)
                        .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND)));
        CustomerRespone customer1 = mapper.toCustomerRespone(eOptional.get());
        if (customer1 != null) {
            return customer1;
        } else
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
    }

    public Optional<Customer> getMyInfo() {
        var user = SecurityContextHolder.getContext();
        String name = user.getAuthentication().getName();
        return customerRepository.findByUsername(name);
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
        return customerRepository.findAll(Sort.by("create").ascending());
    }

    @Override
    public Customer updatePassword(UUID id, String password) {
        Optional<Customer> eCustomer = customerRepository.findById(id);
        if (eCustomer != null) {
            Customer customer1 = eCustomer.get();
            customer1.setPassword(password);
            customer1.setUpdate(date.now());
            customer1.setCreate(customer1.getCreate());
            return customerRepository.save(customer1);
        } else
            throw new AppException(ErrorCode.USERNAME_INVALID);
    }

    public List<Product> findByName(String name) {
        return customerRepository.findAllDepartment(name);
    }
}

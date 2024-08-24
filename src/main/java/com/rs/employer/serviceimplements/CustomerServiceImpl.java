    package com.rs.employer.serviceimplements;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rs.employer.dao.CartRepository;
import com.rs.employer.dao.CustomerRepo;
import com.rs.employer.dao.RoleRepository;
import com.rs.employer.dto.Request.CustomerRequest;
import com.rs.employer.dto.Respone.CustomerRespone;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.mapper.CustomerMapper;
import com.rs.employer.model.Cart;
import com.rs.employer.model.Customer;
import com.rs.employer.model.Product;
import com.rs.employer.service.CustomerService;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

// Service for customer
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    CartRepository cartRepository;
    Instant now = Instant.now();
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CustomerMapper mapper;

    // ZoneId zone = ZoneId.of("Asia/HoChiMinh");
    // Add customertomertoom
    @Override
    public Customer addCustomer(CustomerRequest customer) {

        if (customerRepository.existsByUsername(customer.getUsername()))
            throw new AppException(ErrorCode.USEREXISTED_OR_USERIDEXISTED);
        else {
            // PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(5);
            var role = roleRepository.findAllById(customer.getRole());
            customer.setPassword(passwordEncoder.encode(customer.getPassword()));
            if (!cartRepository.existsByOwner(customer.getUsername()))
                createCart(customer);
            Customer customer1 = mapper.toCustomer(customer);
            customer1.setCreate(now);
            customer1.setUpdate(now);
            customer1.setRoles(new HashSet<>(role));
            customer1.setCart(cartRepository.findByOwner(customer.getUsername()));
            return customerRepository.save(customer1);
        }
    }

    private Cart createCart(CustomerRequest customer) {
        // var user = customerRepository.findByUsername(customer.getUsername());
        // if (user.isPresent()) {
        // Customer customer1 = user.get();
        Cart cart = new Cart(now, customer.getUsername(), now);
        return cartRepository.save(cart);

        // } else
        // throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_UPDATE_USER') or hasAuthority('SCOPE_PERMIT_ALL')")
    public Customer updateCustomer(UUID id, CustomerRequest customer) {
        Optional<Customer> customer1 = customerRepository.findById(id);
        if (customer1.isPresent()) {

            var roles = roleRepository.findAllById(customer.getRole());
            Customer customer3 = mapper.toCustomer(customer);
            customer3.setUpdate(now);
            customer3.setRoles(new HashSet<>(roles));
            customer3.setPassword(passwordEncoder.encode(customer.getPassword()));
            return customerRepository.save(customer3);
        }
        throw new AppException(ErrorCode.USER_NOTFOUND);
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL') or hasAuthority('SCOPE_DELETE_MS')")
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
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = SecurityContextHolder.getContext();
        System.out.printf("The role of the jwt code : {}" + authentication.getAuthorities());
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
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public List listAllCustomer() {
        return customerRepository.findAll(Sort.by("create").ascending());
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_UPDATE_USER')")
    @PostAuthorize("returnObject.username == authentication.name")
    public Customer updatePassword(UUID id, String password) {
        Optional<Customer> eCustomer = customerRepository.findById(id);
        if (eCustomer != null) {
            Customer customer1 = eCustomer.get();
            customer1.setPassword(password);
            customer1.setUpdate(now);
            customer1.setCreate(customer1.getCreate());
            return customerRepository.save(customer1);
        } else
            throw new AppException(ErrorCode.USERNAME_INVALID);
    }

    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public List<Product> findByName(String name) {
        return customerRepository.findAllDepartment(name);
    }
}

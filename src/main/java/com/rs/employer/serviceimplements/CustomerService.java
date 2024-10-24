
package com.rs.employer.serviceimplements;

import java.text.ParseException;
import java.time.Instant;
import java.util.*;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;
import com.rs.employer.dto.Request.ActivateRequestAccount;
import com.rs.employer.dto.Request.ActivateRequestToken;
import com.rs.employer.dto.Request.Register.RegisterRequest;
import com.rs.employer.dto.Respone.CustomerUpdateRespone;
import com.rs.employer.dto.Respone.RegisterRespone;
import com.rs.employer.model.Role;
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
import com.rs.employer.dto.Request.User.CustomerRequest;
import com.rs.employer.dto.Respone.CustomerRespone;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.mapper.CustomerMapper;
import com.rs.employer.model.Cart;
import com.rs.employer.model.Customer;
import com.rs.employer.model.Product;
import com.rs.employer.service.ICustomerService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerService implements ICustomerService {
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
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    AuthenticationService authenticationService;
    @Override
    public RegisterRespone register(RegisterRequest registerRequest) {
        try {
            if (!(customerRepository.existsByEmail(registerRequest.getEmail()) || customerRepository.existsByUsername(registerRequest.getUsername()))) {
                Customer customer = new Customer();
                customer.setUsername(registerRequest.getUsername());
                customer.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
                customer.setEmail(registerRequest.getEmail());
                customer.setCreate(now);
                customerRepository.save(customer);
                return new RegisterRespone(registerRequest.getUsername(), registerRequest.getPassword(), registerRequest.getEmail());
            }
        } catch (Exception e) {
                e.printStackTrace();
        }
        return null;
    }

    @Override
    public Customer addCustomer(CustomerRequest customer) {

        if (customerRepository.existsByUsername(customer.getUsername()))
            throw new AppException(ErrorCode.USEREXISTED_OR_USERIDEXISTED);
        else {
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
        Cart cart = new Cart(now, now, customer.getUsername());
        return cartRepository.save(cart);
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
    public void deleteCustomerById(UUID id) {
        customerRepository.findById(id).ifPresentOrElse(customerRepository::delete, () -> {
            throw new AppException(ErrorCode.USER_NOTFOUND);
        });
    }

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

    public Customer registerUser(UUID id, String password, String login) {
        if (id != null && password != null && login != null) {
            Customer customer = new Customer();
            customer.setUsername(login);
            customer.setPassword(password);
            return customerRepository.save(customer);
        } else
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public List listAllCustomer() {
        return customerRepository.findAll(Sort.by("create").ascending());
    }

    @Override
    public Customer customerRequest(CustomerUpdateRespone request) {
        var user = SecurityContextHolder.getContext();
        String name = user.getAuthentication().getName();
        Optional<Customer> customerOptional = customerRepository.findByUsername(name);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            if (((request.getPassword() != null) && (request.getPassword() != customer.getPassword())))
                customer.setPassword(request.getPassword());
            if (request.getEmail() != customer.getEmail()) customer.setEmail(request.getEmail());
            customer.setName(request.getName());
            customer.setAddress(request.getAddress());
            Set<Role> setRoles = new HashSet<Role>();
            setRoles.add(roleRepository.findByName(request.getRole()));
            customer.setRoles(setRoles);
            customer.setGender(request.isGender());
            customer.setStatus(customer.getStatus());
            customer.setUpdate(now);
            customer.setDob(request.getDob());
            customer.setCart(null);
            return customerRepo.save(customer);
        } else {
            throw new AppException(ErrorCode.USER_NOTFOUND);
        }

    }

    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public List listAllSortByKey(String key) {
        return customerRepository.findAll(Sort.by(key).ascending());
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
    @Override
    public List<Customer> listAllSort(String sort) {
        return customerRepository.findAll(Sort.by(sort).ascending());
    }

    @Override
    public ActivateRequestAccount activateRequest(ActivateRequestToken tokenRequest) throws ParseException, JOSEException {
        JWTClaimsSet claimsSet = JWTClaimsSet.parse(tokenRequest.getToken());
        authenticationService.ActivateAccount(tokenRequest);
        ActivateRequestAccount activateRequestAccount = new ActivateRequestAccount(
                claimsSet.getSubject(),
                claimsSet.getStringClaim("email")
        );
        if (customerRepository.existsByUsernameAndEmail(activateRequestAccount.getUsername(), activateRequestAccount.getEmail())) {
            if (customerRepository.updateStatus(activateRequestAccount.getUsername()) == 1) {
                return activateRequestAccount;
            }
        } else {
            throw new AppException(ErrorCode.ACTIVATED_FAILED);
        }
        throw new AppException(ErrorCode.USER_NOTFOUND);
    }

}

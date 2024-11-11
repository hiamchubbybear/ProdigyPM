
package com.rs.employer.serviceimplements;

import java.time.Instant;
import java.util.*;

import com.nimbusds.jose.JOSEException;
import com.rs.employer.dto.Request.ActivateRequestToken;
import com.rs.employer.dto.Request.ForgotAccountRequest;
import com.rs.employer.dto.Request.Register.RegisterRequest;
import com.rs.employer.dto.Respone.*;
import com.rs.employer.email.EmailService;
import com.rs.employer.model.customer.Cart;
import com.rs.employer.model.customer.Customer;
import com.rs.employer.model.customer.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.rs.employer.dao.CartRepository;
import com.rs.employer.dao.CustomerRepo;
import com.rs.employer.dao.RoleRepository;
import com.rs.employer.dto.Request.User.CustomerRequest;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.mapper.CustomerMapper;
import com.rs.employer.service.ICustomerService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class CustomerService implements ICustomerService {

    private final CustomerRepo customerRepository;
    private final RoleRepository roleRepository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerMapper mapper;
    private final CustomerRepo customerRepo;
    private final AuthenticationService authenticationService;
    private final EmailService emailService;
    private final Instant now = Instant.now();
    @Autowired
    public CustomerService(CustomerRepo customerRepository, RoleRepository roleRepository,
                           CartRepository cartRepository, PasswordEncoder passwordEncoder,
                           CustomerMapper mapper, CustomerRepo customerRepo,
                           AuthenticationService authenticationService, EmailService emailService) {
        this.customerRepository = customerRepository;
        this.roleRepository = roleRepository;
        this.cartRepository = cartRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
        this.customerRepo = customerRepo;
        this.authenticationService = authenticationService;
        this.emailService = emailService;
    }


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
    @PostAuthorize("returnObject.username == authentication.name")
    public CustomerInfoDTO updateCustomer(CustomerInfoDTO customer) {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Customer> customerOptional = customerRepository.findByUsername(username);

        if (customerOptional.isPresent()) {
            Customer existingCustomer = customerOptional.get();
            if (username.equals(customer.getUsername())) {
                existingCustomer.setEmail(customer.getEmail());
                existingCustomer.setName(customer.getName());
                existingCustomer.setAddress(customer.getAddress());
                existingCustomer.setGender(customer.isGender());
                existingCustomer.setStatus(customer.isStatus());
                   customerRepository.save(existingCustomer);
                return customer;
            }
            throw new AppException(ErrorCode.USER_NOTFOUND);
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

    public CustomerInfoDTO getMyInfo() {
        var user = SecurityContextHolder.getContext();
        String name = user.getAuthentication().getName();
        Customer customer =  customerRepository.findByUsername(name).orElse(new Customer());
        return new CustomerInfoDTO(name,customer.getEmail(),customer.getName(), customer.getAddress(),
                customer.isGender(), customer.isStatus());
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
    public ActivateAccountRespone activateRequest(ActivateRequestToken token) {
        try {
            var context = SecurityContextHolder.getContext().getAuthentication();
            if (context != null && context.getPrincipal() instanceof Jwt) {
                Jwt jwt = (Jwt) (context.getPrincipal());
                String email = jwt.getClaim("email");
                var name = jwt.getSubject();
                if (token.getToken().equals(jwt.getId())) {
                    if (customerRepository.existsByUsernameAndEmail(name, email)
                            && customerRepo.findStatusByUsernameAndEmail(name) != true) {
                        return (customerRepository.updateStatus(name) > 0)
                                ? new ActivateAccountRespone(true)
                                : new ActivateAccountRespone(false);
                    }
                } else {
                    throw new AppException(ErrorCode.ACTIVATED_FAILED);
                }
            }
        } catch (Exception e) {
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
        }
        return new ActivateAccountRespone(false);
    }

    @Override
    public ForgotAccountRespone forgotAccount(ForgotAccountRequest request) throws JOSEException {
        var email = request.getEmail();
        String token = authenticationService.ResetPasswordToken(request.getUsername(), request.getEmail());
        if (customerRepository.existsByUsernameAndEmail
                (request.getUsername(), request.getEmail())) {
            String link = new StringBuilder().
                    append("http://localhost:3000/api/customer/resetpwd?").
                    append(token).toString();
            emailService.sendResetPasswordLink(email, "", link);
            return new ForgotAccountRespone(true, "Your auth link sent to your email" , token);
        }
        return new ForgotAccountRespone(false , "Failed" , "null");
    }

    @Override
    public ByteArrayResource userImage(String username) {
        Optional<Customer> customerOpt = customerRepository.findByUsername(username);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            byte[] image = customer.getImage();
            if (image != null) {
                return new ByteArrayResource(image);
            } else {
                throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
            }
        } else {
            throw new AppException(ErrorCode.USER_NOTFOUND);
        }
    }


}

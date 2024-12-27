
package com.rs.employer.service.customer.customer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

import com.nimbusds.jose.JOSEException;
import com.rs.employer.dao.customer.TokenRepository;
import com.rs.employer.dao.customer.TokenService;
import com.rs.employer.dto.Request.ActivateRequestToken;
import com.rs.employer.dto.Request.Register.RegisterRequest;
import com.rs.employer.dto.Response.*;
import com.rs.employer.service.EmailService;
import com.rs.employer.model.customer.Customer;
import com.rs.employer.model.customer.Role;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rs.employer.dao.customer.CustomerRepo;
import com.rs.employer.dao.customer.RoleRepository;
import com.rs.employer.dto.Request.User.CustomerRequest;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.mapper.CustomerMapper;
import com.rs.employer.service.customer.ICustomerService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class CustomerService implements ICustomerService {

    private final CustomerRepo customerRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerMapper mapper;
    private final CustomerRepo customerRepo;
    private final AuthenticationService authenticationService;
    private final EmailService emailService;
    private final Instant now = Instant.now();
    private final TokenRepository tokenRepository;
    private final TokenService tokenService;

    @Autowired
    public CustomerService(CustomerRepo customerRepository, RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           CustomerMapper mapper, CustomerRepo customerRepo,
                           AuthenticationService authenticationService, EmailService emailService, TokenRepository tokenRepository, TokenService tokenService) {
        this.customerRepository = customerRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
        this.customerRepo = customerRepo;
        this.authenticationService = authenticationService;
        this.emailService = emailService;
        this.tokenRepository = tokenRepository;
        this.tokenService = tokenService;
    }


    @Override
    public RegisterRespone register(RegisterRequest registerRequest) {
        try {
            if (!(customerRepository.existsByEmail(registerRequest.getEmail()) &&
                    !customerRepository.existsByUsername(registerRequest.getUsername()))) {
                Customer customer = new Customer();
                customer.setUsername(registerRequest.getUsername());
                customer.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
                customer.setEmail(registerRequest.getEmail());
                customer.setCreate(now);
                customerRepository.save(customer);
                var token = tokenService.checkTokenAndRegenerateToken(registerRequest.getEmail());
                System.out.println("Token đã gửi là  " + token);
                emailService.sendActivateToken(registerRequest.getEmail(),
                        "Activate Token", token);
                return new RegisterRespone(registerRequest.getUsername(), registerRequest.getPassword(), registerRequest.getEmail());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Cacheable(
            value = "caching",
            key = "#customer.username",
            condition = "#customer != null",
            unless = "#result == null"
    )
    @Override
    public Customer addCustomer(CustomerRequest customer) {
        if (customerRepository.existsByUsername(customer.getUsername()))
            throw new AppException(ErrorCode.USEREXISTED_OR_USERIDEXISTED);
        else {
            var role = roleRepository.findAllById(customer.getRole());
            customer.setPassword(passwordEncoder.encode(customer.getPassword()));
            Customer customer1 = mapper.toCustomer(customer);
            customer1.setCreate(now);
            customer1.setUpdate(now);
            customer1.setRoles(new HashSet<>(role));
            return customerRepository.save(customer1);
        }
    }

    @Override
    @PostAuthorize("returnObject.username == authentication.name")
    public CustomerInfoDTO updateCustomer(CustomerInfoDTO customer) {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("Tên của người dùng cập nhật là " + username);
        Customer existingCustomer = customerRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USEREXISTED_OR_USERIDEXISTED));
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


    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL') or hasAuthority('SCOPE_DELETE_MS')")
    public void deleteCustomerById(UUID id) {
        customerRepository.findById(id).ifPresentOrElse(customerRepository::delete, () -> {
            throw new AppException(ErrorCode.USER_NOTFOUND);
        });
    }

    @Override
    @PostAuthorize("returnObject.username == authentication.name")
    public CustomerResponse listCustomerById(UUID id) {
        return mapper.toCustomerRespone(customerRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND)));
    }

    @Cacheable(
            value = "userInfoCache",
            key = "#username",
            unless = "#result == null"
    )
    public CustomerInfoDTO getMyInfo() {
        var user = SecurityContextHolder.getContext().getAuthentication();

        if (user == null) {
            throw new AppException(ErrorCode.USER_NOTFOUND);
        }

        Customer customer = customerRepository.findByUsername(user.getName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));

        return new CustomerInfoDTO(
                user.getName(),
                customer.getEmail(),
                customer.getName(),
                customer.getAddress(),
                customer.isGender(),
                customer.isStatus()
        );
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
    @Transactional
    @Cacheable(value = "customers", key = "'AllCustomers'")
    public List listAllCustomer() {
        return customerRepository.findAll(Sort.by("create").ascending());
    }

    @Override
    public Customer customerRequest(CustomerUpdateRespone request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        Optional.ofNullable(request.getPassword())
                .filter(password -> !password.equals(customer.getPassword()))
                .ifPresent(customer::setPassword);
        Optional.ofNullable(request.getEmail())
                .filter(email -> !email.equals(customer.getEmail()))
                .ifPresent(customer::setEmail);
        customer.setName(request.getName());
        customer.setAddress(request.getAddress());
        Role role = roleRepository.findByName(request.getRole());
        customer.setRoles(Set.of(role));
        customer.setGender(request.isGender());
        customer.setStatus(customer.getStatus());
        customer.setUpdate(Instant.now());
        customer.setDob(request.getDob());
        System.out.println("Đã cập nhật thông tin của {}" + username);
        return customerRepository.save(customer);
    }

    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public List listAllSortByKey(String key) {
        return customerRepository.findAll(Sort.by(key).ascending());
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_UPDATE_USER')")
    @PostAuthorize("returnObject.username == authentication.name")
    public Customer updatePassword(UUID id, String password) {
        Customer customer1 = customerRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        customer1.setPassword(password);
        customer1.setUpdate(now);
        customer1.setCreate(customer1.getCreate());
        return customerRepository.save(customer1);
    }

    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    @Override
    public List<Customer> listAllSort(String sort) {
        return customerRepository.findAll(Sort.by(sort).ascending());
    }

    @Override
    public ActivateAccountResponse activateRequest(ActivateRequestToken treq) throws ParseException, JOSEException {
        if (tokenService.compareToken(treq.getToken(), treq.getEmail())) {
            Optional<Customer> foundCustomer = customerRepository.findByEmail(treq.getEmail());
            System.out.println("Found customer: " + foundCustomer.get());
            Customer customer = foundCustomer.get();
            return customerRepository.updateStatus(customer.getUsername()) > 0 ?
                    new ActivateAccountResponse(true) : new ActivateAccountResponse(false)
                    ;
        } else throw new AppException(ErrorCode.ACTIVATED_FAILED);
    }

    @Override
    public ForgotAccountRespone forgotAccount(String email) throws JOSEException {
        var object = customerRepo.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION));
        String token = tokenService.checkTokenAndRegenerateToken(email);
        if (customerRepository.existsByUsernameAndEmail
                (object.getUsername(), email)) {
            String toClient = new StringBuilder().
                    append(token).toString();
            emailService.sendResetPasswordLink(email, "", toClient);
            System.out.println();
            return new ForgotAccountRespone(true, "Your reset token sent to your email", toClient);
        }
        return new ForgotAccountRespone(false, "Failed", "null");
    }

    @Override
    public ByteArrayResource userImage(String username) throws IOException {
        Optional<Customer> customerOpt = customerRepository.findByUsername(username);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            byte[] image = customer.getImage();
            if (image != null) {
                return new ByteArrayResource(image);
            } else {
                Path path = Paths.get("src/main/resources/Default-Profile-Picture-Download-PNG-Image.png");
                byte[] imageBytes = Files.readAllBytes(path);
                return new ByteArrayResource(imageBytes);
            }
        } else {
            throw new AppException(ErrorCode.USER_NOTFOUND);
        }
    }

    public boolean confirmForgotPasswordCode(String passcode, String email) {
        var customer = customerRepository.findByEmail(email).get();
        var token = tokenRepository.findTokenByCustomerEmailAndUsed(email, false);
        if (customer != null) {
            if (passcode.equals(token)) {
                return true;
            } else return false;
        } else throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
    }

}


package com.rs.employer.service.customer.customer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.Instant;
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
            if (!(customerRepository.existsByEmail(registerRequest.getEmail()) && !customerRepository.existsByUsername(registerRequest.getUsername()))) {
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
    public CustomerResponse listCustomerById(UUID id) {
        Optional<Customer> eOptional = Optional.ofNullable(
                customerRepository.findById(id)
                        .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND)));
        CustomerResponse customer1 = mapper.toCustomerRespone(eOptional.get());
        if (customer1 != null) {
            return customer1;
        } else
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
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
            System.out.println("Đã cập nht thông tin của " + name);
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

//    @Override
//    public ActivateAccountResponse activateRequest(ActivateRequestToken token) {
//        try {
//            var context = SecurityContextHolder.getContext().getAuthentication();
//            if (context != null && context.getPrincipal() instanceof Jwt) {
//                Jwt jwt = (Jwt) (context.getPrincipal());
//                String email = jwt.getClaim("email");
//                var name = jwt.getSubject();
//                if (token.getToken().equals(jwt.getId())) {
//                    if (customerRepository.existsByUsernameAndEmail(name, email)
//                            && customerRepo.findStatusByUsernameAndEmail(name) != true) {
//                        return (customerRepository.updateStatus(name) > 0)
//                                ? new ActivateAccountResponse(true)
//                                : new ActivateAccountResponse(false);
//                    }
//                } else {
//                    throw new AppException(ErrorCode.ACTIVATED_FAILED);
//                }
//            }
//        } catch (Exception e) {
//            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
//        }
//        return new ActivateAccountResponse(false);
//    }


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

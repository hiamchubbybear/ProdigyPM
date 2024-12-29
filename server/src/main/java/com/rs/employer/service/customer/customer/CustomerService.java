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
import com.rs.employer.dto.CustomerAllInfoDTO;
import com.rs.employer.dto.Request.ActivateRequestToken;
import com.rs.employer.dto.Request.Register.RegisterRequest;
import com.rs.employer.dto.Response.*;
import com.rs.employer.service.EmailService;
import com.rs.employer.model.customer.Customer;
import com.rs.employer.model.customer.Role;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
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

@Service
public class CustomerService implements ICustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
    private final CustomerRepo customerRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerMapper mapper;
    private final CustomerRepo customerRepo;
    private final EmailService emailService;
    private final Instant now = Instant.now();
    private final TokenRepository tokenRepository;
    private final TokenService tokenService;
    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public CustomerService(CustomerRepo customerRepository, RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           CustomerMapper mapper, CustomerRepo customerRepo, EmailService emailService, TokenRepository tokenRepository, TokenService tokenService, RedisTemplate<String, String> redisTemplate) {
        this.customerRepository = customerRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
        this.customerRepo = customerRepo;
        this.emailService = emailService;
        this.tokenRepository = tokenRepository;
        this.tokenService = tokenService;
        this.redisTemplate = redisTemplate;
    }

    /*
    @req: Use to create an account without user profile.
    @par: RegisterRequest Object containing the username, password, and email of the new customer.
    @des: This method checks if the provided email and username already exist in the system. 
          If either exists, it throws an exception. If both are unique, it creates a new Customer object, 
          encodes the password, saves the customer to the database, generates an activation token, 
          and sends an activation email to the user.
    */
    @Override
    public RegisterRespone register(RegisterRequest customer) {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }
        if (customerRepository.existsByUsername(customer.getUsername())) {
            throw new AppException(ErrorCode.USERNAME_ALREADY_EXISTS);
        }
        Customer newCustomer = new Customer(
                customer.getUsername(),
                customer.getEmail(),
                now,
                passwordEncoder.encode(customer.getPassword())
        );
        log.info("Customer created with email: {}", newCustomer.getEmail());
        customerRepository.save(newCustomer);
        log.info("New customer created {}", newCustomer.getEmail());
        var token = tokenService.checkTokenAndRegenerateToken(customer.getEmail());
        log.info("Token sent : " + token);
        emailService.sendActivateToken(customer.getEmail(), "Activate Token", token);
        return new RegisterRespone(customer.getUsername(), customer.getPassword(), customer.getEmail());
    }

    /*
    @req: Use to add a new customer to the system.
    @par: CustomerRequest Object containing the details of the customer to be added, including username, password, and roles.
    @des: This method checks if the username already exists in the system. 
          If it does, it throws an exception. If not, it encodes the password, 
          maps the CustomerRequest to a Customer object, sets the creation and update timestamps, 
          assigns roles to the customer, and saves the customer to the database.
    */
    @Cacheable(
            value = "user",
            key = "#customer.username",
            condition = "#customer != null",
            unless = "#result == null"
    )
    @Override
    public Customer addCustomer(CustomerRequest customer) {
        if (customerRepository.existsByUsername(customer.getUsername()))
            throw new AppException(ErrorCode.USERNAME_ALREADY_EXISTS);
        else {
            customer.setPassword(passwordEncoder.encode(customer.getPassword()));
            Customer customer1 = mapper.toCustomer(customer);
            customer1.setCreate(now);
            customer1.setUpdate(now);
            customer1.setRole(customer.getRoles());
            return customerRepository.save(customer1);
        }
    }

    /*
    @req: Use to update customer information.
    @par: CustomerInfoDTO Object containing updated customer details.
    @des: This method retrieves the username from the security context, finds the existing customer by username, 
          and updates their email, name, address, gender, and status. 
          It then saves the updated customer back to the database and returns the updated CustomerInfoDTO.
    */

    @Override
    @PostAuthorize("returnObject.username == authentication.name")
    @Caching(
            put = {
                    @CachePut(
                            value = "customer",
                            key = "#customer.username",
                            condition = "#customer != null",
                            unless = "#result == null"
                    )
            },
            evict = {
                    @CacheEvict(
                            value = "customer", key = "'AllCustomers'"
                    )
            }
    )
    public CustomerInfoDTO updateCustomer(CustomerInfoDTO customer) {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("Tên của người dùng cập nhật là " + username);
        Customer existingCustomer = customerRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
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

    /*
    @req: Use to delete a customer by their ID.
    @par: UUID id of the customer to be deleted.
    @des: This method checks if the customer exists by ID. If they do, it deletes the customer. 
          If not, it throws an exception indicating the user was not found.
    */
    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL') or hasAuthority('SCOPE_DELETE_MS')")
    public void deleteCustomerById(UUID id) {
        customerRepository.findById(id).ifPresentOrElse((customer) -> {
            customerRepository.deleteById(id);
            redisTemplate.delete(customer.getUsername());
        }, () -> {
            throw new AppException(ErrorCode.USER_NOTFOUND);
        });
    }

    /*
    @req: Use to retrieve customer information by their ID.
    @par: UUID id of the customer.
    @des: This method finds the customer by ID and maps it to a CustomerResponse object. 
          If the customer is not found, it throws an exception indicating the user was not found.
    */
    @Override
    @PostAuthorize("returnObject.username == authentication.name")
    @Caching
    public CustomerResponse listCustomerById(UUID id) {
        return mapper.toCustomerRespone(customerRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND)));
    }

    /*
    @req: Use to get the current user's information.
    @par: None.
    @des: This method retrieves the authenticated user's username from the security context, 
          finds the corresponding customer in the database, and returns their information 
          as a CustomerInfoDTO. If the user is not found, it throws an exception.
    */
    @Cacheable(
            value = "customer",
            key = "#root.target.getUsername()",
            condition = "#result != null",
            unless = "null"
    )
    @PostAuthorize("returnObject.username == authentication.name")
    public CustomerInfoDTO getMyInfo() {
        var user = SecurityContextHolder.getContext().getAuthentication();
        if (user == null) {
            throw new AppException(ErrorCode.USER_NOTFOUND);
        }
        String username = user.getName();
        if (username == null) {
            throw new AppException(ErrorCode.USER_NOTFOUND);
        }

        Customer customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        return new CustomerInfoDTO(
                username,
                customer.getEmail(),
                customer.getName(),
                customer.getAddress(),
                customer.isGender(),
                customer.isStatus(),
                customer.getRole()
        );
    }

    /*
    @req: Use to get the username of the currently authenticated user.
    @par: None.
    @des: This method retrieves the username from the security context. 
          If the user is not authenticated, it returns null.
    */
    public String getUsername() {
        var user = SecurityContextHolder.getContext().getAuthentication();
        return user != null ? user.getName() : null;
    }

    /*
    @req: Use to list all customers in the system.
    @par: None.
    @des: This method retrieves all customers from the database, sorts them by creation date, 
          and maps them to a list of CustomerAllInfoDTO objects for easier consumption.
    */
    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    @Transactional
    @Cacheable(value = "customer", key = "'AllCustomers'")
    public List<CustomerAllInfoDTO> listAllCustomer() {
        List<Customer> ListofCustomer = customerRepository.findAll(Sort.by("create").ascending());
        return ListofCustomer.stream().map(customer -> new CustomerAllInfoDTO(
                customer.getUuid(),
                customer.getUsername(),
                customer.getEmail(),
                customer.getName(),
                customer.getAddress(),
                customer.isGender(),
                customer.isStatus(),
                customer.getCreate(),
                customer.getDob(),
                customer.getRole()
        )).toList();
    }

    /*
    @req: Use to update the current user's information.
    @par: CustomerUpdateRespone object containing updated details.
    @des: This method retrieves the authenticated user's username, finds the corresponding customer, 
          and updates their password, email, name, address, role, gender, status, 
          and date of birth. It then saves the updated customer back to the database.
    */
    @Override
    @Caching(
            put = {
                    @CachePut(
                            value = "customer",
                            key = "#customer.username",
                            condition = "#customer != null",
                            unless = "#result == null"
                    )
            },
            evict = {
                    @CacheEvict(
                            value = "customer", key = "'AllCustomers'"
                    )
            }
    )
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
        customer.setRole(request.getRole());
        customer.setGender(request.isGender());
        customer.setStatus(customer.getStatus());
        customer.setUpdate(Instant.now());
        customer.setDob(request.getDob());
        System.out.println("Đã cập nhật thông tin của {}" + username);
        return customerRepository.save(customer);
    }

    /*
    @req: Use to update a customer's password by their ID.
    @par: UUID id of the customer and the new password.
    @des: This method finds the customer by ID, updates their password and update timestamp, 
          and saves the changes to the database.
    */
    @Override
    @PreAuthorize("hasAuthority('SCOPE_UPDATE_USER')")
    @PostAuthorize("returnObject.username == authentication.name")
    public Customer updatePassword(UUID id, String password) {
        Customer customer1 = customerRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        customer1.setPassword(passwordEncoder.encode(password));
        customer1.setUpdate(now);
        return customerRepository.save(customer1);
    }

    /*
    @req: Use to list all customers sorted by a specified field.
    @par: String sort indicating the field to sort by.
    @des: This method retrieves all customers from the database, sorted by the specified field, 
          and returns the list of customers.
    */
//    @Cacheable(value = "customersSorted", key = "#sort")
//    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    @Override
    public List<Customer> listAllSort(String sort) {
        return customerRepository.findAll(Sort.by(sort).ascending());
    }

    /*
    @req: Use to activate a customer account using a token.
    @par: ActivateRequestToken object containing the token and email.
    @des: This method compares the provided token with the stored token for the email. 
          If they match, it updates the customer's status to activate.
          If not, it throws an exception indicating activation failed.
    */
    @Override
    public ActivateAccountResponse activateRequest(ActivateRequestToken treq) throws ParseException, JOSEException {
        if (tokenService.compareToken(treq.getToken(), treq.getEmail())) {
            Optional<Customer> foundCustomer = customerRepository.findByEmail(treq.getEmail());
            System.out.println("Found customer: " + foundCustomer.orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND)));
            Customer customer = foundCustomer.orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
            return customerRepository.updateStatus(customer.getUsername()) > 0 ?
                    new ActivateAccountResponse(true) : new ActivateAccountResponse(false);
        } else throw new AppException(ErrorCode.ACTIVATED_FAILED);
    }

    /*
    @req: Use to handle forgotten account requests.
    @par: String email of the customer requesting a password reset.
    @des: This method finds the customer by email, generates a reset token, 
          and sends a reset password link to the customer's email. 
          If the email does not match any customer, it throws an exception.
    */
    @Override
    public ForgotAccountRespone forgotAccount(String email) {
        var object = customerRepo.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION));
        String token = tokenService.checkTokenAndRegenerateToken(email);
        if (customerRepository.existsByUsernameAndEmail(object.getUsername(), email) && !token.isEmpty()) {
            emailService.sendResetPasswordLink(email, "", token);
            return new ForgotAccountRespone(true, "Your reset token sent to your email", token);
        }
        return new ForgotAccountRespone(false, "Failed", "null");
    }

    /*
    @req: Use to retrieve the user's profile image.
    @par: String username of the customer.
    @des: This method finds the customer by username, retrieves their image, 
          or returns a default image if none exists. If the customer is not found, 
          it throws an exception.
    */
    @Cacheable(value = "userImage", key = "#username")
    @Override
    public byte[] userImage(String username) throws IOException {
        Optional<Customer> customerOpt = customerRepository.findByUsername(username);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            byte[] image = customer.getImage();
            if (image != null) {
                return image; // Trả về byte[]
            } else {
                Path path = Paths.get("server/src/main/resources/Default-Profile-Picture-Download-PNG-Image.png");
                return Files.readAllBytes(path); // Trả về byte[] từ file
            }
        } else {
            throw new AppException(ErrorCode.USER_NOTFOUND);
        }
    }
    /*
    @req: Use to confirm the password reset code.
    @par: String passcode and String email of the customer.
    @des: This method checks if the provided passcode matches the stored token for the email. 
          It returns true if it matches, otherwise returns false.
    */

    public boolean confirmForgotPasswordCode(String passcode, String email) {
        var token = tokenRepository.findTokenByCustomerEmailAndUsed(email, false);
        if (!customerRepository.existsByEmail(email))
            return false;
        return token.map(token1 -> token1.getToken().equals(passcode)).orElse(false);
    }

    // TEST
//    @Override
//    public List listAllCustomer() {
//        List<Customer> ListofCustomer = customerRepository.findAll(Sort.by("create").ascending());
//        return ListofCustomer;
//    }
    public Customer findCustomerByUsername(String username) {
        Customer customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        customer.setRole(username);
        return customerRepository.save(customer);
    }
}








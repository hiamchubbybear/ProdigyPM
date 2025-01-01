package com.rs.employer.service.customer.customer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.Instant;
import java.util.*;

import com.nimbusds.jose.JOSEException;
import com.rs.employer.applicationconfig.RedisConfiguration;
import com.rs.employer.dao.customer.TokenRepository;
import com.rs.employer.dao.customer.TokenService;
import com.rs.employer.dto.CustomerAllInfoDTO;
import com.rs.employer.dto.Request.ActivateRequestToken;
import com.rs.employer.dto.Request.Register.RegisterRequest;
import com.rs.employer.dto.Response.*;
import com.rs.employer.service.CacheService;
import com.rs.employer.service.EmailService;
import com.rs.employer.model.customer.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rs.employer.dao.customer.CustomerRepository;
import com.rs.employer.dto.Request.User.CustomerRequest;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.mapper.CustomerMapper;
import com.rs.employer.service.customer.ICustomerService;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CustomerService implements ICustomerService {
    private final CacheService cacheService;
    @PersistenceContext
    private EntityManager entityManager;
    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerMapper mapper;
    private final CustomerRepository customerRepo;
    private final EmailService emailService;
    private final Instant now = Instant.now();
    private final TokenRepository tokenRepository;
    private final TokenService tokenService;
    private final RedisConfiguration redisConfiguration;

    @Autowired
    public CustomerService(CustomerRepository customerRepository,
                           PasswordEncoder passwordEncoder,
                           CustomerMapper mapper, CustomerRepository customerRepo, EmailService emailService, TokenRepository tokenRepository, TokenService tokenService, RedisConfiguration redisConfiguration, CacheService cacheService) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
        this.customerRepo = customerRepo;
        this.emailService = emailService;
        this.tokenRepository = tokenRepository;
        this.tokenService = tokenService;
        this.redisConfiguration = redisConfiguration;
        this.cacheService = cacheService;
    }

    /**
    @permission: All
    @function: Use to create an account without user profile.
    @parameters: RegisterRequest Object containing the username, password, and email of the new customer.
    @description: This method checks if the provided email and username already exist in the system.
          If either exists, it throws an exception. If both are unique, it creates a new Customer object,
          encodes the password, saves the customer to the database, generates an activation token,
          and sends an activation email to the user.
    **/
    @CacheEvict(
            value = "customer", key = "'AllCustomers'"
    )
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

    /**
    @permission: Admin
    @function: Use to add a new customer to the system.
    @parameters: CustomerRequest Object containing the details of the customer to be added, including username, password, and roles.
    @description: This method checks if the username already exists in the system.
          If it does, it throws an exception. If not, it encodes the password,
          maps the CustomerRequest to a Customer object, sets the creation and update timestamps,
          assigns roles to the customer, and saves the customer to the database.
    **/
    @Cacheable(
            value = "userAdded",
            key = "#customer.username",
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

    /**
    @permission: User
    @function: Use to update customer information.
    @parameters: CustomerInfoDTO Object containing updated customer details.
    @description: This method retrieves the username from the security context, finds the existing customer by username,
          and updates their email, name, address, gender, and status.
          It then saves the updated customer back to the database and returns the updated CustomerInfoDTO.
    **/
    @Override
    @Transactional(rollbackOn = RuntimeException.class)
    @PostAuthorize("returnObject.username == authentication.name")
    @Caching(
            put = {
                    @CachePut(
                            value = "customerUpdate",
                            key = "#username",
                            unless = "#result == null"
                    )
            },
            evict = {
                    @CacheEvict(
                            value = "customer", key = "'allCustomers'"
                    )
            }
    )
    public CustomerInfoDTO updateCustomer(CustomerInfoDTO existingCustomer, String username) {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaUpdate<Customer> update = cb.createCriteriaUpdate(Customer.class);
            Root<Customer> root = update.from(Customer.class);
            if (existingCustomer.getEmail() != null) {
                update.set(root.get("email"), existingCustomer.getEmail());
            }
            if (existingCustomer.getName() != null) {
                update.set(root.get("name"), existingCustomer.getName());
            }
            if (existingCustomer.getAddress() != null) {
                update.set(root.get("address"), existingCustomer.getAddress());
            }
            if (existingCustomer.getRole() != null) {
                update.set(root.get("role"), existingCustomer.getRole());
            }
            if (existingCustomer.getGender() != null) {
                update.set(root.get("gender"), existingCustomer.getGender());
            }
            update.where(cb.equal(root.get("username"), username));
            entityManager.createQuery(update).executeUpdate();
            entityManager.flush();
            return existingCustomer;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update customer information: " + e.getMessage());
        }
    }


    /**
    @function   : Use to delete a customer by their ID.
    @parameters : UUID id of the customer to be deleted.
    @description: This method checks if the customer exists by ID. If they do, it deletes the customer.
          If not, it throws an exception indicating the user was not found.
    **/
    @Override
    @Transactional(rollbackOn = AppException.class)
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL') or hasAuthority('SCOPE_DELETE_MS')")
    public Boolean deleteCustomerById(String username) {
        log.info("Customer deleting");
        Optional<Customer> foundCustomer = customerRepository.findByUsername(username);
        if (foundCustomer.isPresent()) {

            customerRepository.deleteCustomerByUsername(username);
            log.info("Customer deleted {}", foundCustomer.get().getUsername());
            cacheService.evictCaches(username);
            return true;
        } else throw new AppException(ErrorCode.USER_NOTFOUND);

    }


    /**
    @function   : Use to retrieve customer information by their ID.
    @parameters : UUID id of the customer.
    @description: This method finds the customer by ID and maps it to a CustomerResponse object.
          If the customer is not found, it throws an exception indicating the user was not found.
    **/
    @Override
    @PostAuthorize("returnObject.username == authentication.name")
    @Caching
    public CustomerResponse listCustomerById(String username) {
        return mapper.toCustomerResponse(customerRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND)));
    }

    /**
    @function   : Use to get the current user's information.
    @parameters : None.
    @description: This method retrieves the authenticated user's username from the security context,
          finds the corresponding customer in the database, and returns their information
          as a CustomerInfoDTO. If the user is not found, it throws an exception.
    **/
    @Cacheable(
            value = "customerInfo",
            key = "#username",
            unless = "#result == null"
    )
    @PostAuthorize("returnObject.username == authentication.name")
    public CustomerInfoDTO getMyInfo(String username) {
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
                customer.getRole()
        );
    }

    /**
    @function   : Use to get the username of the currently authenticated user.
    @parameters : None.
    @description: This method retrieves the username from the security context.
          If the user is not authenticated, it returns null.
    **/
    public String getUsername() {
        var user = SecurityContextHolder.getContext().getAuthentication();
        return user != null ? user.getName() : null;
    }

    /**
    @function   : Use to list all customers in the system.
    @parameters : None.
    @description: This method retrieves all customers from the database, sorts them by creation date,
          and maps them to a list of CustomerAllInfoDTO objects for easier consumption.
    **/
    @Override
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    @Cacheable(value = "customer", key = "'allCustomers'")
    public List<CustomerAllInfoDTO> listAllCustomer() {
        List<Customer> ListonCustomer = customerRepository.findAll(Sort.by("create").ascending());
        return ListonCustomer.stream().map(customer -> new CustomerAllInfoDTO(
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

    /**
    @permission: Admin
    @function: Use to update the current user's information.
    @parameters: CustomerUpdateResponse object containing updated details.
    @description: This method retrieves the authenticated user's username, finds the corresponding customer,
          and updates their password, email, name, address, role, gender, status,
          and date of birth. It then saves the updated customer back to the database.
    **/
    @Override
    @Caching(
            put = {
                    @CachePut(
                            value = "customerAdminReq",
                            key = "#username",
                            unless = "#result == null"
                    )
            },
            evict = {
                    @CacheEvict(
                            value = "customer", key = "'allCustomers'"
                    )
            }
    )
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    public Customer customerRequest(CustomerUpdateResponse request, String username) {
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
        customer.setStatus(request.getStatus());
        customer.setUpdate(Instant.now());
        customer.setDob(request.getDob());
        System.out.println("Đã cập nhật thông tin của {}" + username);
        return customerRepository.save(customer);
    }

    /**
    @permission: User owner
    @function: Use to update a customer's password by their ID.
    @parameters: UUID id of the customer and the new password.
    @description: This method finds the customer by ID, updates their password and update timestamp,
          and saves the changes to the database.
    **/
    @Override
    @PostAuthorize("returnObject.username == authentication.name")
    public Customer updatePassword(String username, String password) {
        Customer customer1 = customerRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        customer1.setPassword(passwordEncoder.encode(password));
        customer1.setUpdate(now);
        return customerRepository.save(customer1);
    }

    /**
    @permission: Admin
    @function: Use to list all customers sorted by a specified field.
    @parameters: String sort indicating the field to sort by.
    @description: This method retrieves all customers from the database, sorted by the specified field,
          and returns the list of customers.
    **/
    @Cacheable(value = "customersSorted", key = "#sort")
    @PreAuthorize("hasAuthority('SCOPE_PERMIT_ALL')")
    @Override
    public List<Customer> listAllSort(String sort) {
        return customerRepository.findAll(Sort.by(sort).ascending());
    }

    /**
    @permission: User owner
    @function: Use to activate a customer account using a token.
    @parameters: ActivateRequestToken object containing the token and email.
    @description: This method compares the provided token with the stored token for the email.
          If they match, it updates the customer's status to activate.
          If not, it throws an exception indicating activation failed.
    **/
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

    /**
    @permission: User owner
    @function: Use to handle forgotten account requests.
    @parameters: String email of the customer requesting a password reset.
    @description: This method finds the customer by email, generates a reset token,
          and sends a reset password link to the customer's email.
          If the email does not match any customer, it throws an exception.
    **/
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

    /**
    @permission: User owner and admin
    @function: Use to retrieve the user's profile image.
    @parameters: String username of the customer.
    @description: This method finds the customer by username, retrieves their image,
          or returns a default image if none exists. If the customer is not found,
          it throws an exception.
    **/
    @Cacheable(value = "userImage", key = "#username")
    @PostAuthorize("#username == authentication.name")
    @Override
    public byte[] userImage(String username) throws IOException {
        Optional<Customer> customerOpt = customerRepository.findByUsername(username);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            byte[] image = customer.getImage();
            if (image != null) {
                return image;
            } else {
                Path path = Paths.get("server/src/main/resources/Default-Profile-Picture-Download-PNG-Image.png");
                return Files.readAllBytes(path);
            }
        } else {
            throw new AppException(ErrorCode.USER_NOTFOUND);
        }
    }

    /**
    @permission: Account owner
    @function: Use to confirm the password reset code.
    @parameters: String passcode and String email of the customer.
    @description: This method checks if the provided passcode matches the stored token for the email.
          It returns true if it matches, otherwise returns false.
    **/
    public boolean confirmForgotPasswordCode(String passcode, String email) {
        var token = tokenRepository.findTokenByCustomerEmailAndUsed(email, false);
        if (!customerRepository.existsByEmail(email))
            return false;
        return token.map(token1 -> token1.getToken().equals(passcode)).orElse(false);
    }


    /**
    @permission: Account owner and admin
    @function: Use to upload the image for user.
    @parameters: File and Barer token.
    @description: This method check if username doesn't exist throw new exception user not found
          .Else get file name and byte array and update it to table. Service will be roll back if catch an AppException
    **/
    @Transactional(dontRollbackOn = RuntimeException.class, rollbackOn = AppException.class)
    public String uploadImage(MultipartFile file, String username) throws IOException {
        String fileName = file.getOriginalFilename();
        byte[] fileBytes = file.getBytes();
        if (!customerRepo.existsByUsername(username)) {
            throw new AppException(ErrorCode.USER_NOTFOUND);
        } else {
            customerRepo.updateCustomerImage(username, fileBytes);
            return "User upload images successfully" + fileName + " with username: " + username;
        }

    }
}
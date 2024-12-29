package com.rs.employer.applicationconfig;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.rs.employer.enums.RoleEnum;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.model.customer.Customer;
import com.rs.employer.model.customer.Permission;
import com.rs.employer.model.customer.Role;
import com.rs.employer.service.EmailService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rs.employer.dao.customer.CustomerRepo;
import com.rs.employer.dao.customer.PermissionRepository;
import com.rs.employer.dao.customer.RoleRepository;
import com.rs.employer.enums.PermissionEnum;

@Configuration
public class RoleAndAdminConfiguration {

    private static final Logger log = LoggerFactory.getLogger(RoleAndAdminConfiguration.class);
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final CustomerRepo repo;

    @Autowired
    public RoleAndAdminConfiguration(PasswordEncoder passwordEncoder, RoleRepository roleRepository, PermissionRepository permissionRepository, CustomerRepo repo) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.repo = repo;
    }


    @Bean
    @Transactional
    @Primary
    public ApplicationRunner createPermissionsAndRoles() {
        return args -> {
            log.info("Starting to create permissions...");
            for (PermissionEnum permissionEnum : PermissionEnum.values()) {
                if (!permissionRepository.existsByName(permissionEnum.name())) {
                    Permission permission = new Permission(permissionEnum.name(),
                            permissionEnum.name().replace("SCOPE_", ""), permissionEnum.getAssigned());
                    permissionRepository.save(permission);
                    log.info("Created permission: {}", permissionEnum.name());
                } else {
                    log.info("Permission {} already exists.", permissionEnum.name());
                }
            }
            log.info("Finished creating permissions.");

            log.info("Starting to create roles...");
            for (RoleEnum role : RoleEnum.values()) {
                if (roleRepository.findAllByName(role.getName())!= null) {
                    Set<Permission> permissions = new HashSet<>(permissionRepository.findAllByAssigned(role.getName()));
                    roleRepository.save(new Role(role.getName(), role.getDescription(), permissions));
                    log.info("Role {} has been created", role.getName());
                } else {
                    log.info("Role {} already exists.", role.getName());
                }
            }
            log.info("Finished creating roles.");
        };
    }

    @Bean
    public ApplicationRunner createAdmin() {
        return args -> {
            if (!repo.findByUsername("admin").isPresent()) {
                Set<Role> role = roleRepository.findAllByName("ADMIN");
                log.info("Role đã thêm vào user {}", role.stream().findFirst().get().getName());

                Customer admin = new Customer();
                admin.setUsername("admin");
                admin.setAddress("Hue");
                admin.setCreate(Instant.now());
                admin.setUpdate(Instant.now());
                admin.setGender(true);
                admin.setEmail("ilovepakpak@gmail.com");
                admin.setName("Chessy");
                admin.setPassword(passwordEncoder.encode("160304"));
                admin.setRoles(role);
                admin.setStatus(true);
                repo.save(admin);
                log.info("Admin account created successfully.");
            } else {
                log.info("Admin account already exists.");
            }
        };
    }
}
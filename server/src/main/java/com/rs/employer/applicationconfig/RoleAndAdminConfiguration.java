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
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    private final Instant now = Instant.now();

    @Autowired
    public RoleAndAdminConfiguration(PasswordEncoder passwordEncoder, RoleRepository roleRepository, PermissionRepository permissionRepository, CustomerRepo repo) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.repo = repo;
    }

    @Bean
    @Transactional
    public ApplicationRunner createAdmin() {
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
//
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
            if (!repo.findByUsername("admin").isPresent()) {
//                Set<Role> role = roleRepository.findAllByName("ADMIN");
//                log.info("Role đã thêm vào user {}", role.stream().findFirst().get().getName());


//                Permission permission = new Permission("PERMIT_ALL", "Permission crud to all");
//                permissionRepository.save(permission);
//                Role role = new Role();
//                role.setName("ADMIN");
//                role.setDescription("ADMIN Role");
//                HashSet<Permission> permissions = new HashSet<>();
//                permissions.add(permission);
//                role.setPermissions(permissions);
//                roleRepository.save(role);
                Role adminRole = roleRepository.findByName("ADMIN")
                        .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));

                log.info("Creating admin role... {}", adminRole.getName());

                // Tạo một tập hợp vai trò và thêm vai trò ADMIN vào đó
                Set<Role> roles = new HashSet<>();
                roles.add(adminRole);
//                Set<Role> role1 = new HashSet<>();
//                role1.add(roleRepository.findById(1).orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND)));
//                log.info("Creating admin role...{}" , role1.stream().findFirst().get().getName());


                Customer admin = new Customer("admin",passwordEncoder.encode("160304")
                        ,"ilovepakpak@gmail.com","Chessy","Hue",roles,true,true,now,now,null );
                log.info("Creating admin role... {}", admin.getRoles().stream().findFirst().get().getName());
                repo.save(admin);
                log.info("Admin account created successfully.");
            } else {
                log.info("Admin account already exists.");
            }
        };
    }
}
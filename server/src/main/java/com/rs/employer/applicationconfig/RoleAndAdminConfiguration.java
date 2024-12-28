package com.rs.employer.applicationconfig;


import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.rs.employer.model.customer.Customer;
import com.rs.employer.model.customer.Permission;
import com.rs.employer.model.customer.Role;
import com.rs.employer.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.actuate.sbom.SbomEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rs.employer.dao.customer.CustomerRepo;
import com.rs.employer.dao.customer.PermissionRepository;
import com.rs.employer.dao.customer.RoleRepository;
import com.rs.employer.enums.PermissionEnum;
@org.springframework.context.annotation.Configuration
public class RoleAndAdminConfiguration {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    EmailService emailService;

    @Bean
    @Primary
    ApplicationRunner createAdmin() {
        return args -> {
            for (PermissionEnum permissionEnum : PermissionEnum.values()) {
                if (!permissionRepository.existsByName(permissionEnum.name())) {
                    Permission permission = new Permission(permissionEnum.name(),
                            permissionEnum.name().replace("SCOPE_", "") + " permission");
                    permissionRepository.save(permission);
                }
            }

        };
    }

    @Bean
    ApplicationRunner applicationRunner(CustomerRepo repo, SbomEndpoint sbomEndpoint) {
        return args -> {
            if (!repo.findByUsername("admin").isPresent()) {
                if (!repo.findByUsername("admin").isPresent()) {
                    Permission permission = new Permission("PERMIT_ALL", "Permission crud to all ");
                    permissionRepository.save(permission);
                    Role role = new Role();
                    role.setName("ADMIN");
                    role.setDescription("ADMIN Role");
                    HashSet<Permission> permissions = new HashSet<>();
                    permissions.add(permission);
                    role.setPermissions(permissions);
                    roleRepository.save(role);
                    Set<Role> role1 = new HashSet<>();
                    role1.add(roleRepository.findByName("ADMIN"));
                    Customer admin = new Customer();
                    admin.setUsername("admin");
                    admin.setAddress("Hue");
                    admin.setCreate(Instant.now());
                    admin.setUpdate(Instant.now());
                    admin.setGender(true);
                    admin.setEmail("ilovepakpak@gmail.com");
                    admin.setName("Chessy");
                    admin.setPassword(passwordEncoder.encode("160304"));
                    admin.setRoles(role1);
                    admin.setStatus(true);
//                    admin.setStatus("ONLINE");
//                    emailService.sendActivateToken("ilovepakpak@gmail.com", "Test email", "TOOOOOOO");
//                    System.out.println("Sent email!");
                    repo.save(admin);
                }
            }
            ;
        };
    }
}
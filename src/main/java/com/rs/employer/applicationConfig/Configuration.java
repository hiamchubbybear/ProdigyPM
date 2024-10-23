package com.rs.employer.applicationConfig;


import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

=======
>>>>>>> main
import com.rs.employer.dao.CustomerRepo;
import com.rs.employer.dao.PermissionRepository;
import com.rs.employer.dao.RoleRepository;
import com.rs.employer.email.EmailSender;
import com.rs.employer.enums.PermissionEnum;
import com.rs.employer.model.Customer;
import com.rs.employer.model.Permission;
import com.rs.employer.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.actuate.sbom.SbomEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@org.springframework.context.annotation.Configuration
@RequiredArgsConstructor
public class Configuration {
    @Autowired
    PasswordEncoder passwordEncoder ;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    EmailSender emailSender;
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
<<<<<<< HEAD
=======

>>>>>>> main
    @Bean
    ApplicationRunner applicationRunner(CustomerRepo repo, SbomEndpoint sbomEndpoint) {
        return args -> {
<<<<<<< HEAD
        if (!repo.findByUsername("admin").isPresent()) {
=======
            if (!repo.findByUsername("admin").isPresent()) {
>>>>>>> main
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
                admin.setStatus("ONLINE");
                emailSender.sendSimpleMail("ilovepakpak@gmail.com","Test email" , "TOOOOOOO");
                System.out.println("Sent email!");
                repo.save(admin);
            }
        };
    }
}

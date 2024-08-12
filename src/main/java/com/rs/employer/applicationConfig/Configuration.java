package com.rs.employer.applicationConfig;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rs.employer.model.Customer;
import com.rs.employer.model.Permission;
import com.rs.employer.model.Role;
import com.rs.employer.repository.CustomerRepo;
import com.rs.employer.repository.PermissionRepository;
import com.rs.employer.repository.RoleRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@org.springframework.context.annotation.Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC, makeFinal = true)
public class Configuration {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PermissionRepository permissionRepository;

    @Bean
    @Primary
    ApplicationRunner createAdmin() {
        return args -> {

        };
    }

    // Role adminRole = new Role();
    // adminRole.setName("ADMIN");
    // adminRole.setDescription("Administrator role with full access");
    // adminRole.setPermissions(new HashSet<>(Arrays.asList(adminPermission)));
    // adminRole = roleRepository.save(adminRole);
    @Bean
    ApplicationRunner applicationRunner(CustomerRepo repo) {
        return args -> {
            if (repo.findByUsername("admin").isEmpty()) {
                // HashSet<Role> role = new HashSet<>();
                // role.forEach(role -> role.setName("ADMIN"););

                // ============

                // Role roleToAdd = findRole.get();
                // Set<Role> roles = new HashSet<>();
                // roles.add(roleToAdd);

                // role.ifPresent(role1::add);
                Permission permission = new Permission("ADMIN_ROLE", "Permission crud to all ");
                // Permission permission2 = new Permission("ADMIN_ROLE", "Permission crud to all
                // ");
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
                admin.setUsername("admin");
                admin.setAddress("Hue");
                admin.setCreate(Instant.now());
                admin.setUpdate(Instant.now());
                admin.setGender(true);
                admin.setName("Chessy");
                admin.setPassword(passwordEncoder.encode("160304"));
                admin.setRoles(role1);
                admin.setStatus("ONLINE");
                repo.save(admin);
            }
        };
    }
}

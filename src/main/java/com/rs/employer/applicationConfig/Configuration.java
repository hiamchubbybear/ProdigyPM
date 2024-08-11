package com.rs.employer.applicationConfig;

import java.time.Instant;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rs.employer.model.Customer;
import com.rs.employer.repository.CustomerRepo;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@org.springframework.context.annotation.Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC, makeFinal = true)
public class Configuration {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(CustomerRepo repo) {
        return args -> {
            if (repo.findByUsername("admin").isEmpty()) {

                HashSet role = new HashSet<String>();
                role.add("ADMIN");
                Customer admin = new Customer();

                admin.setUsername("admin");
                // admin.setUsername("admin");
                admin.setAddress("Hue");
                admin.setCreate(Instant.now());
                admin.setUpdate(Instant.now());
                admin.setGender(true);
                admin.setName("Chessy");
                admin.setPassword(passwordEncoder.encode("160304"));
                // admin.setRoles(role);
                admin.setStatus("ONLINE");
                repo.save(admin);
            }
        };
    }
}

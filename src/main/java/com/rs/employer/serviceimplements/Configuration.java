package com.rs.employer.serviceimplements;

import java.time.Instant;
import java.util.HashSet;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rs.employer.enums.Role;
import com.rs.employer.model.Customer;
import com.rs.employer.repository.CustomerRepo;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@org.springframework.context.annotation.Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Configuration {
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(CustomerRepo repo) {
        return args -> {
            if (repo.findByUsername("ADMIN").isEmpty()) {
                HashSet role = new HashSet<>();
                role.add(Role.ADMIN.name());
                Customer admin = new Customer();
                admin.setUsername("admin");
                admin.setUserid((long) 1);
                admin.setAddress("Hue");
                admin.setCreate(Instant.now());
                admin.setUpdate(Instant.now());
                admin.setGender(true);
                admin.setName("Chessy");
                admin.setPassword(passwordEncoder.encode("160304"));
                admin.setRole(role);
                admin.setStatus("ONLINE");
                repo.save(admin);
            }
        };
    }
}

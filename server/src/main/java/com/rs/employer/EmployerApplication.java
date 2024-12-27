package com.rs.employer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EmployerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployerApplication.class, args);
    }

}


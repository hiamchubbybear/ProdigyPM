package com.rs.employer.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rs.employer.model.Customer;

// Database JPA repository for Customer
@Repository
public interface CustomerRepo extends JpaRepository<Customer, UUID> {
    // @Query("SELECT u FROM schema_customer u WHERE u.username = :username")
    // Optional<Customer> findByUsername(String username);
    Optional<Customer> findByUsername(String email);
}

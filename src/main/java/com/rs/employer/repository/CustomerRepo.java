package com.rs.employer.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rs.employer.model.Customer;

// Database JPA repository for Customer
@Repository
public interface CustomerRepo extends JpaRepository<Customer, UUID> {
    // @Query("UPDATE customer set id = 2 where id = ?1")
    
    
}

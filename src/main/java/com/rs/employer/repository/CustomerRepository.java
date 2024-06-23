package com.rs.employer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rs.employer.model.Customer;

// Database JPA repository for Customer
@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

}

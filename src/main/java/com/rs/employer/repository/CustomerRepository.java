package com.rs.employer.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rs.employer.model.Customer;
// Database JPA repository for Customer
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
 @Query("select p from :qualifiedcar p where p.type in (:type)")
    List<Customer> findByQualifiedPersonAndType(@Param("qualifiedcar") String qualifiedcar, @Param("type") String type);
}


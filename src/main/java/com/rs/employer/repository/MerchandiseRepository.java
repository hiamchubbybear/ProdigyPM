package com.rs.employer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rs.employer.model.Merchandise;

@Repository
public interface MerchandiseRepository extends JpaRepository<Merchandise, Long> {
    
}
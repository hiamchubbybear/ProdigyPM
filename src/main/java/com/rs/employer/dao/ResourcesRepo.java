package com.rs.employer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rs.employer.model.Resources;

@Repository
public interface ResourcesRepo extends JpaRepository<Resources, Long> {
}

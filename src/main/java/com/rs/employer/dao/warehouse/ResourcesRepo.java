package com.rs.employer.dao.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rs.employer.model.warehouse.Resources;

@Repository
public interface ResourcesRepo extends JpaRepository<Resources, Long> {
}

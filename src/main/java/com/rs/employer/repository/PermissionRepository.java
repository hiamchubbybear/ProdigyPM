package com.rs.employer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rs.employer.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {

}
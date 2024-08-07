package com.rs.employer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rs.employer.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

}

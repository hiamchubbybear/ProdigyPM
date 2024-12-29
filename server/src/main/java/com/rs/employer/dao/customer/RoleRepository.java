package com.rs.employer.dao.customer;

import com.rs.employer.model.customer.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);

    @Query("SELECT b FROM Role b WHERE b.name = ?1")
    Role findByRoleName(String name);

    Set<Role> findAllByName(String role);
}
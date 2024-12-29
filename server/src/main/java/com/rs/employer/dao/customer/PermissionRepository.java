package com.rs.employer.dao.customer;

import com.rs.employer.model.customer.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {
    boolean existsByName(String name);

    Permission findByName(String name);

    List<Permission> findAllByAssigned (String assigned);
}

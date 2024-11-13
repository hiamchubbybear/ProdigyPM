package com.rs.employer.dao.customer;

import com.rs.employer.model.customer.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByName(String name);
}

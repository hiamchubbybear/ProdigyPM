package com.rs.employer.dao.customer;

import com.rs.employer.model.customer.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Repository;


@Repository
@EnableRedisRepositories
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByName(String name);
}

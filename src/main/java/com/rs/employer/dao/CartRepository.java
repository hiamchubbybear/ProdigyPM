package com.rs.employer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rs.employer.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByOwner(String owner);
    Cart findByCartid(Long cartid);
    Boolean existsByOwner(String owner);
}

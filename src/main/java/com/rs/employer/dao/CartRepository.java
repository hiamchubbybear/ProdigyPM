package com.rs.employer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rs.employer.model.customer.Cart;

@Repository
public interface
CartRepository extends JpaRepository<Cart, Long> {
    Cart findByOwner(String owner);
    // @Query(value = "DELTE")
    // Boolean deleteProductById();
    // Cart findByCartid(Long cartid);
    @Query(value = "SELECT p.product_id FROM product p " +
    "JOIN cart_products cp ON p.product_id = cp.products_product_id " +
    "JOIN cart c ON c.cart_id = cp.cart_cart_id " +
    "WHERE c.cart_id = :cartId", nativeQuery = true)
    List<Long> findProductsByCartId(@Param("cartId") Long cartId);
    Cart findByCartid(Long cartid);
    Boolean existsByOwner(String owner);
}

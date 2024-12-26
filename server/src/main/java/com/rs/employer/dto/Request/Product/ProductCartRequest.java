package com.rs.employer.dto.Request.Product;

import java.util.Set;

public class ProductCartRequest {
    Long cart_id;
    Set<Long> products_id;
    public ProductCartRequest(Long cart_id, Set<Long> products_id) {
        this.cart_id = cart_id;
        this.products_id = products_id;
    }
    public ProductCartRequest() {
    }
    public Long getCart_id() {
        return cart_id;
    }
    public void setCart_id(Long cart_id) {
        this.cart_id = cart_id;
    }
    public Set<Long> getProducts_id() {
        return products_id;
    }
    public void setProducts_id(Set<Long> products_id) {
        this.products_id = products_id;
    }

}

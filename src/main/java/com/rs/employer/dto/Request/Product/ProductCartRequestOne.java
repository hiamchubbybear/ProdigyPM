package com.rs.employer.dto.Request.Product;

public class ProductCartRequestOne {
    Long cart_id;
    Long product_id;
    int quantity;
    public Long getCart_id() {
        return cart_id;
    }
    public void setCart_id(Long cart_id) {
        this.cart_id = cart_id;
    }
    public Long getProduct_id() {
        return product_id;
    }
    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public ProductCartRequestOne(Long cart_id, Long product_id, int quantity) {
        this.cart_id = cart_id;
        this.product_id = product_id;
    this.quantity = quantity;
    }
    public ProductCartRequestOne() {
    }

}

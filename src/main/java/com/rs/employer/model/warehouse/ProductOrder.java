package com.rs.employer.model.warehouse;

import com.rs.employer.model.customer.Customer;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productOrderId;
    private String orderNo;
    @ManyToOne
    @JoinColumn(name = "product_order")
    private Product product;
    private BigDecimal plannedQuantity;
    private Date plannedDate;
    private Integer status;
    @ManyToOne
    @JoinColumn(name = "customer_order")
    private Customer customer;
    private Date createDate;

    public ProductOrder() {
    }

    public Long getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(Long productOrderId) {
        this.productOrderId = productOrderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getPlannedQuantity() {
        return plannedQuantity;
    }

    public void setPlannedQuantity(BigDecimal plannedQuantity) {
        this.plannedQuantity = plannedQuantity;
    }

    public Date getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(Date plannedDate) {
        this.plannedDate = plannedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public ProductOrder(Long productOrderId, String orderNo, Product product, BigDecimal plannedQuantity, Date plannedDate, Integer status, Customer customer, Date createDate) {
        this.productOrderId = productOrderId;
        this.orderNo = orderNo;
        this.product = product;
        this.plannedQuantity = plannedQuantity;
        this.plannedDate = plannedDate;
        this.status = status;
        this.customer = customer;
        this.createDate = createDate;
    }
}
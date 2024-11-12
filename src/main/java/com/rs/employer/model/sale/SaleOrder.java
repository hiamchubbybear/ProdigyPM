package com.rs.employer.model.sale;

import com.rs.employer.model.customer.Customer;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
@Entity
public class SaleOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    private String orderNumber;
    @ManyToOne
    @JoinColumn(name = "userSales")
    private User user;
    private Date orderDate;
    private Date deliveryDate;
    private BigDecimal total;
    private Integer status;
    @ManyToOne
    @JoinColumn(name = "createBy")
    private Customer createBy;
    private Date createDate;

    public SaleOrder() {
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Customer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Customer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public SaleOrder(Integer orderId, String orderNumber, User user, Date orderDate, Date deliveryDate, BigDecimal total, Integer status, Customer createBy, Date createDate) {
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        this.user = user;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.total = total;
        this.status = status;
        this.createBy = createBy;
        this.createDate = createDate;
    }
}
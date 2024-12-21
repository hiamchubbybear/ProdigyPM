package com.rs.employer.dto;

import com.rs.employer.model.temp.OrderItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderDTO {
    public OrderDTO() {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderDTO(Integer orderId, String orderNumber, Long userId, Date orderDate, Date deliveryDate, BigDecimal total, Integer status, String createBy, Date createDate, List<OrderItem> orderItems) {
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        this.userId = userId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.total = total;
        this.status = status;
        this.createBy = createBy;
        this.createDate = createDate;
        this.orderItems = orderItems;
    }

    private Integer orderId;
    private String orderNumber;
    private Long userId;
    private Date orderDate;
    private Date deliveryDate;
    private BigDecimal total;
    private Integer status;
    private String createBy;
    private Date createDate;
    private List<OrderItem> orderItems;
}
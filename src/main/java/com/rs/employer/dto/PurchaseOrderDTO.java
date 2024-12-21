package com.rs.employer.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


public class PurchaseOrderDTO {
    private Long purchaseOrderId;
    private String orderNumber;
    private Integer supplierId;
    private Date orderDate;
    private Date deliveryDate;
    private BigDecimal totalAmount;
    private Integer status;
    private Integer createByCustomerId;
    private Date createDate;

    public PurchaseOrderDTO() {
    }

    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreateByCustomerId() {
        return createByCustomerId;
    }

    public void setCreateByCustomerId(Integer createByCustomerId) {
        this.createByCustomerId = createByCustomerId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public PurchaseOrderDTO(Long purchaseOrderId, String orderNumber, Integer supplierId, Date orderDate, Date deliveryDate, BigDecimal totalAmount, Integer status, Integer createByCustomerId, Date createDate) {
        this.purchaseOrderId = purchaseOrderId;
        this.orderNumber = orderNumber;
        this.supplierId = supplierId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createByCustomerId = createByCustomerId;
        this.createDate = createDate;
    }
}
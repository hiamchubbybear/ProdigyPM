package com.rs.employer.model.purchase;

import com.rs.employer.model.customer.Customer;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long purchaseOrderId;
    private String orderNumber;
    @ManyToOne
    @JoinColumn(name = "po_Supplier")
    private Supplier supplier;
    private Date orderDate;
    private Date deliveryDate;
    private BigDecimal totalAmount;
    private Integer status;
    @ManyToOne
    @JoinColumn(name = "po_Customer")
    private Customer createBy;
    private Date createDate;

    public PurchaseOrder() {
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

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
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

    public PurchaseOrder(Long purchaseOrderId, String orderNumber, Supplier supplier, Date orderDate, Date deliveryDate, BigDecimal totalAmount, Integer status, Customer createBy, Date createDate) {
        this.purchaseOrderId = purchaseOrderId;
        this.orderNumber = orderNumber;
        this.supplier = supplier;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createBy = createBy;
        this.createDate = createDate;
    }
}

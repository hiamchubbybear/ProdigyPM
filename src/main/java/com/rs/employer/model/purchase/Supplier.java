package com.rs.employer.model.purchase;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer supplierId;
    private String supplierName;
    private String supplierCode;
    private String supplierAddress;
    private String supplierPhone;
    private String supplierEmail;
    private String supplierDescription;
    private String taxCode;
    private Integer status;
    @OneToMany(mappedBy = "supplier")
    private Set<PurchaseOrder> orders;

    public Supplier() {
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierPhone() {
        return supplierPhone;
    }

    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public String getSupplierDescription() {
        return supplierDescription;
    }

    public void setSupplierDescription(String supplierDescription) {
        this.supplierDescription = supplierDescription;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<PurchaseOrder> getOrders() {
        return orders;
    }

    public void setOrders(Set<PurchaseOrder> orders) {
        this.orders = orders;
    }

    public Supplier(Integer supplierId, String supplierName, String supplierCode, String supplierAddress, String supplierPhone, String supplierEmail, String supplierDescription, String taxCode, Integer status, Set<PurchaseOrder> orders) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.supplierCode = supplierCode;
        this.supplierAddress = supplierAddress;
        this.supplierPhone = supplierPhone;
        this.supplierEmail = supplierEmail;
        this.supplierDescription = supplierDescription;
        this.taxCode = taxCode;
        this.status = status;
        this.orders = orders;
    }
}

package com.rs.employer.model.warehouse;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class WareHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer warehouseId;
    private String warehouseName;
    private String warehouseCode;
    private String wareHouseAddress;
    private Integer status;
    @OneToMany()
    private Set<Inventory> inventories;


    public WareHouse() {
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getWareHouseAddress() {
        return wareHouseAddress;
    }

    public void setWareHouseAddress(String wareHouseAddress) {
        this.wareHouseAddress = wareHouseAddress;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(Set<Inventory> inventories) {
        this.inventories = inventories;
    }

    public WareHouse(Integer warehouseId, String warehouseName, String warehouseCode, String wareHouseAddress, Integer status, Set<Inventory> inventories) {
        this.warehouseId = warehouseId;
        this.warehouseName = warehouseName;
        this.warehouseCode = warehouseCode;
        this.wareHouseAddress = wareHouseAddress;
        this.status = status;
        this.inventories = inventories;
    }
}

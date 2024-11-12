package com.rs.employer.model.manufacture;

import com.rs.employer.model.warehouse.Product;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class BOM {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bomId;
    @ManyToOne
    @JoinColumn(name = "bom_product")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "bom_material")
    private Product material;
    private BigDecimal quantity;

    public BOM(Long bomId, Product product, Product material, BigDecimal quantity) {
        this.bomId = bomId;
        this.product = product;
        this.material = material;
        this.quantity = quantity;
    }

    public BOM() {
    }

    public Long getBomId() {
        return bomId;
    }

    public void setBomId(Long bomId) {
        this.bomId = bomId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getMaterial() {
        return material;
    }

    public void setMaterial(Product material) {
        this.material = material;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
}

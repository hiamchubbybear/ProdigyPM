package com.rs.employer.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rs.employer.model.Customer;
import com.rs.employer.model.Product;

// Database JPA repository for Customer

@Repository
public interface CustomerRepo extends JpaRepository<Customer, UUID> {
    boolean existsByUsername(String username);

    void deleteByUsername(String username);

    // @Query(value = "select " +
    // " w.product_id as Product_Id, " +
    // " w.name_product as Product_Name, " +
    // " w.product_type as Product_Type, " +
    // " w.product_size as Product_Size, " +
    // " w.expire_date_product as Product_Exp, " +
    // " w.subtitle_product as Product_Title, " +
    // "from product_schema w", nativeQuery = true)
    // Set<ProductDto> getProductDetail();

    // @Query(value = "select " +
    //         " w.product_id as Product_Id , " +
    //         " w.name_product as Product_Name , " +
    //         " w.product_type as Product_Type , " +
    //         " w.product_size as Product_Size , " +
    //         " w.product_weight as Product_Weight , " +
    //         " w.product_weight_unit as Product_Weight_Unit , " +
    //         " w.product_size_unit as Product_Size_Unit , " +
    //         " w.expire_date_product as Product_Exp , " +
    //         " w.create_at as Create_At , " +
    //         " w.update_at as Product_Exp , " +
    //         " w.subtitle_product as Product_Subtitle , " +
    //         " w.unit_product as Product_Unit " +
    //         "from schema_product w", nativeQuery = true)
    // List<Product> getAllProductDetail();
    @Query(value = "select " +
    "w.product_id as Product_Id, " +
    "w.name_product as Product_Name, " +
    "w.product_type as Product_Type, " +
    "w.product_size as Product_Size, " +
    "w.product_weight as Product_Weight, " +
    "w.product_weight_unit as Product_Weight_Unit, " +
    "w.product_size_unit as Product_Size_Unit, " +
    "w.expire_date_product as Product_Exp, " +
    "w.create_at as Create_At, " +
    "w.update_at as Update_At, " +
    "w.subtitle_product as Product_Subtitle, " +
    "w.unit_product as Product_Unit " +
    "from schema_product w", nativeQuery = true)
    List<Product> getAllProductDetail();
}
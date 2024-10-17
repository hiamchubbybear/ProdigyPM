package com.rs.employer.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rs.employer.model.Customer;
import com.rs.employer.model.Product;


@Repository
public interface CustomerRepo extends JpaRepository<Customer, UUID> {
        boolean existsByUsername(String username);
        boolean existsByEmail(String email);

        Optional<Customer> findByUsername(String username);
//        Boolean addCustomer(Customer customer);
//        @Query(value = "SELECT * FROM Product u WHERE u.name = :name", nativeQuery = true)
//        List<Product> findAllDepartment(@Param("name") String name);
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

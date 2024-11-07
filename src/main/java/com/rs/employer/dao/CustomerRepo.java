package com.rs.employer.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rs.employer.model.Customer;
import com.rs.employer.model.Product;
import org.springframework.web.bind.annotation.PathVariable;


@Repository
@EnableJpaRepositories
public interface CustomerRepo extends JpaRepository<Customer, UUID> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<Customer> findByUsername(String username);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Customer c WHERE c.username = :username AND c.email = :email")
    boolean existsByUsernameAndEmail(@Param("username") String username,@Param("email") String email);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Customer c SET c.status = true WHERE c.username = :username")
    int updateStatus(@Param("username") String username);

    @Query("SELECT c.status FROM Customer c WHERE (c.username = :username)")
    Boolean findStatusByUsernameAndEmail(@Param("username") String username);
    @Modifying
    @Transactional
    @Query("UPDATE Customer c SET c.image = :image WHERE c.username = :username")
    void updateCustomerImage(@Param("username") String username, @Param("image") byte[] image);

    @Query("SELECT c.email from Customer c where  (c.username = :username)")
    String findEmailByUsername(@Param("username") String username);
    @Modifying
    @Transactional
    @Query("UPDATE Customer c SET c.resetToken = :resetToken WHERE c.username = :username")
    void updateResetToken(@Param("username") String username, @Param("resetToken") String resetToken);
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

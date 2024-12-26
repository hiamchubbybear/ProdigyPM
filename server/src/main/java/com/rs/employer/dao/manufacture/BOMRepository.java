package com.rs.employer.dao.manufacture;

import com.rs.employer.model.manufacture.BOM;
import com.rs.employer.model.warehouse.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BOMRepository extends JpaRepository<BOM, Long> {
    List<BOM> findByProduct(Product product);

    List<BOM> findByMaterial(Product material);
}

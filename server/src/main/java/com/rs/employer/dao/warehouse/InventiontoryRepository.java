package com.rs.employer.dao.warehouse;

import com.rs.employer.model.warehouse.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventiontoryRepository extends JpaRepository<Inventory,Long> {
}

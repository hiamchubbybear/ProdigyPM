package com.rs.employer.dao.warehouse;

import com.rs.employer.model.warehouse.WareHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository  extends JpaRepository<WareHouse, Integer> {
}

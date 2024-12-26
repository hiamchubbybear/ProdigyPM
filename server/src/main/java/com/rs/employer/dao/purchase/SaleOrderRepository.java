package com.rs.employer.dao.purchase;

import com.rs.employer.model.sale.SaleOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleOrderRepository  extends JpaRepository<SaleOrder, Long> {
}

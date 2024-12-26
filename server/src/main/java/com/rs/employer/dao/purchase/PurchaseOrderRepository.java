package com.rs.employer.dao.purchase;

import com.rs.employer.model.purchase.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
    List<PurchaseOrder> findByOrderDateBetween(Date startDate, Date endDate);

    List<PurchaseOrder> findBySupplier_SupplierId(Integer supplierId);
}

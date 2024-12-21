package com.rs.employer.dao.temp;


import com.rs.employer.model.sale.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.image.TileObserver;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByOrderDateBetween(Date startDate, Date endDate);

    List<Order> findByStatus(Integer status);

    List<Order> findByUserId(Integer userId);

    Optional<Order> findByOrderNumber(String orderNumber);
}

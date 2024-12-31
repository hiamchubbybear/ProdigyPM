package com.rs.employer.service.sale;

import com.rs.employer.dao.customer.CustomerRepository;
import com.rs.employer.dao.purchase.UserRepository;
import com.rs.employer.dao.temp.OrderRepository;
import com.rs.employer.dto.OrderDTO;

import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.mapper.OrderMapper;
import com.rs.employer.model.sale.Order;
import com.rs.employer.model.temp.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;
    private final CustomerRepository customerRepo;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository, OrderMapper orderMapper, CustomerRepository customerRepo) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderMapper = orderMapper;
        this.customerRepo = customerRepo;
    }

    public OrderDTO updateOrder(Integer orderId, OrderDTO orderDTO) {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.PURCHASE_ORDER_NOT_FOUND));

        existingOrder.setOrderNumber(orderDTO.getOrderNumber());
        existingOrder.setOrderDate(orderDTO.getOrderDate());
        existingOrder.setDeliveryDate(orderDTO.getDeliveryDate());
        existingOrder.setTotal(orderDTO.getTotal());
        existingOrder.setStatus(orderDTO.getStatus());
        existingOrder.setCreateBy(customerRepo.findByUsername(orderDTO.getCreateBy()).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND))); // Customer ID
        existingOrder.setCreateDate(orderDTO.getCreateDate());

        existingOrder.setUser(userRepository.findById(orderDTO.getUserId().longValue())
                .orElseThrow(() -> new RuntimeException("User not found")));

        Order finalExistingOrder = existingOrder;
        List<OrderItem> orderItems = orderDTO.getOrderItems().stream()
                .map(itemDTO -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setQuantity(itemDTO.getQuantity());
                    orderItem.setPrice(itemDTO.getPrice());
                    orderItem.setOrder(finalExistingOrder);
                    return orderItem;
                })
                .collect(Collectors.toList());

        existingOrder.setOrderItems(orderItems);

        existingOrder = orderRepository.save(existingOrder);
        return orderMapper.toDTO(existingOrder);
    }

    public List<OrderDTO> getOrdersByStatus(Integer status) {
        return orderRepository.findByStatus(status).stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getOrdersByUser(Integer userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);
        order.setCreateBy(customerRepo.findByUsername(orderDTO.getCreateBy()).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND)));
        order.setUser(userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found")));

        // Tạo mới các OrderItems từ DTO
        Order finalOrder = order;
        List<OrderItem> orderItems = orderDTO.getOrderItems().stream()
                .map(itemDTO -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setQuantity(itemDTO.getQuantity());
                    orderItem.setPrice(itemDTO.getPrice());
                    orderItem.setOrder(finalOrder);
                    return orderItem;
                })
                .collect(Collectors.toList());

        order.setOrderItems(orderItems);

        order = orderRepository.save(order);
        return orderMapper.toDTO(order);
    }

    public boolean deleteOrder(Integer orderId) {
        Optional<Order> existingOrder = orderRepository.findById(orderId);
        if (existingOrder.isPresent()) {
            orderRepository.deleteById(orderId);
            return true;
        }
        return false;
    }

    public List<OrderDTO> getOrdersByDateRange(Date startDate, Date endDate) {
        return orderRepository.findByOrderDateBetween(startDate, endDate).stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO getOrderByOrderNumber(String orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber)
                .map(orderMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
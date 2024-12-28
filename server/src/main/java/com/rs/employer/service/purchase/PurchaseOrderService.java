package com.rs.employer.service.purchase;

import com.rs.employer.dao.purchase.PurchaseOrderRepository;
import com.rs.employer.dao.purchase.SupplierRepository;
import com.rs.employer.dto.PurchaseOrderDTO;
import com.rs.employer.dto.SupplierDTO;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.mapper.PurchaseOrderMapper;
import com.rs.employer.mapper.SupplierMapper;
import com.rs.employer.model.purchase.PurchaseOrder;

import com.rs.employer.model.purchase.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final SupplierRepository supplierRepository;
    private final PurchaseOrderMapper purchaseOrderMapper;

    @Autowired
    public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository, SupplierRepository supplierRepository, PurchaseOrderMapper purchaseOrderMapper) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.supplierRepository = supplierRepository;
        this.purchaseOrderMapper = purchaseOrderMapper;
    }

    public PurchaseOrderDTO createPurchaseOrder(PurchaseOrderDTO dto) {
        Supplier supplier = supplierRepository.findById(dto.getSupplierId())
                .orElseThrow(() -> new AppException(ErrorCode.SUPPLIER_NOT_FOUND));
        PurchaseOrder purchaseOrder = purchaseOrderMapper.toEntity(dto);
        purchaseOrder.setSupplier(supplier);
        return purchaseOrderMapper.toDTO(purchaseOrderRepository.save(purchaseOrder));
    }

    public List<PurchaseOrderDTO> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll()
                .stream()
                .map(purchaseOrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PurchaseOrderDTO getPurchaseOrderById(Long id) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PURCHASE_ORDER_NOT_FOUND));
        return purchaseOrderMapper.toDTO(purchaseOrder);
    }

    public List<PurchaseOrderDTO> getPurchaseOrdersBySupplierId(Integer supplierId) {
        List<PurchaseOrder> orders = purchaseOrderRepository.findBySupplier_SupplierId(supplierId);
        return orders.stream().map(purchaseOrderMapper::toDTO).collect(Collectors.toList());
    }

    public boolean deletePurchaseOrder(Long id) {
        if (!purchaseOrderRepository.existsById(id)) {
            return false;
        }
        purchaseOrderRepository.deleteById(id);
        return true;
    }

    @Transactional
    public PurchaseOrderDTO updateStatus(Long id, Integer status) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PURCHASE_ORDER_NOT_FOUND));
        purchaseOrder.setStatus(status);
        return purchaseOrderMapper.toDTO(purchaseOrder);
    }

    public PurchaseOrderDTO updatePurchaseOrder(Long id, PurchaseOrderDTO dto) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PURCHASE_ORDER_NOT_FOUND));
        Supplier supplier = supplierRepository.findById(dto.getSupplierId())
                .orElseThrow(() -> new AppException(ErrorCode.SUPPLIER_NOT_FOUND));
        purchaseOrder.setSupplier(supplier);
        purchaseOrder.setOrderNumber(dto.getOrderNumber());
        purchaseOrder.setOrderDate(dto.getOrderDate());
        purchaseOrder.setDeliveryDate(dto.getDeliveryDate());
        purchaseOrder.setTotalAmount(dto.getTotalAmount());
        purchaseOrder.setStatus(dto.getStatus());
        return purchaseOrderMapper.toDTO(purchaseOrderRepository.save(purchaseOrder));
    }

    public BigDecimal calculateTotalAmountBySupplier(Integer supplierId) {
        List<PurchaseOrder> orders = purchaseOrderRepository.findBySupplier_SupplierId(supplierId);
        return orders.stream()
                .map(PurchaseOrder::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<PurchaseOrderDTO> findByDateRange(Date startDate, Date endDate) {
        List<PurchaseOrder> orders = purchaseOrderRepository.findByOrderDateBetween(startDate, endDate);
        return orders.stream().map(purchaseOrderMapper::toDTO).collect(Collectors.toList());
    }
}
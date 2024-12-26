

package com.rs.employer.controller.purchase;

import com.rs.employer.apirespone.ApiRespone;
import com.rs.employer.dto.PurchaseOrderDTO;

import com.rs.employer.service.purchase.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
@CrossOrigin("*")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    @Autowired
    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @PostMapping
    public ApiRespone<PurchaseOrderDTO> createPurchaseOrder(@RequestBody PurchaseOrderDTO purchaseOrderDTO) {
        return new ApiRespone<>(purchaseOrderService.createPurchaseOrder(purchaseOrderDTO));
    }

    @GetMapping
    public ApiRespone<List<PurchaseOrderDTO>> getAllPurchaseOrders() {
        return new ApiRespone<>(purchaseOrderService.getAllPurchaseOrders());
    }

    @GetMapping("/{id}")
    public ApiRespone<PurchaseOrderDTO> getPurchaseOrderById(@PathVariable Long id) {
        return new ApiRespone<>(purchaseOrderService.getPurchaseOrderById(id));
    }

    @GetMapping("/supplier/{supplierId}")
    public ApiRespone<List<PurchaseOrderDTO>> getPurchaseOrdersBySupplierId(@PathVariable Integer supplierId) {
        return new ApiRespone<>(purchaseOrderService.getPurchaseOrdersBySupplierId(supplierId));
    }

    @DeleteMapping("/{id}")
    public ApiRespone<Boolean> deletePurchaseOrder(@PathVariable Long id) {
        return new ApiRespone<>(purchaseOrderService.deletePurchaseOrder(id));
    }

    @PutMapping("/{id}/status")
    public ApiRespone<PurchaseOrderDTO> updateStatus(@PathVariable Long id, @RequestParam Integer status) {
        return new ApiRespone<>(purchaseOrderService.updateStatus(id, status));
    }

    @PutMapping("/{id}")
    public ApiRespone<PurchaseOrderDTO> updatePurchaseOrder(@PathVariable Long id, @RequestBody PurchaseOrderDTO purchaseOrderDTO) {
        return new ApiRespone<>(purchaseOrderService.updatePurchaseOrder(id, purchaseOrderDTO));
    }

    @GetMapping("/supplier/{supplierId}/total-amount")
    public ApiRespone<BigDecimal> calculateTotalAmountBySupplier(@PathVariable Integer supplierId) {
        return new ApiRespone<>(purchaseOrderService.calculateTotalAmountBySupplier(supplierId));
    }

    @GetMapping("/date-range")
    public ApiRespone<List<PurchaseOrderDTO>> findByDateRange(@RequestParam Date startDate, @RequestParam Date endDate) {
        return new ApiRespone<>(purchaseOrderService.findByDateRange(startDate, endDate));
    }
}
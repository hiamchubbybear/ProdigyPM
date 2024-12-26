package com.rs.employer.controller.purchase;


import com.rs.employer.apirespone.ApiRespone;
import com.rs.employer.dto.SupplierDTO;
import com.rs.employer.service.purchase.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@CrossOrigin("*")
public class SupplierController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    public ApiRespone<SupplierDTO> createSupplier(@RequestBody SupplierDTO supplierDTO) {
        return new ApiRespone<>(supplierService.createSupplier(supplierDTO));
    }

    @GetMapping
    public ApiRespone<List<SupplierDTO>> getAllSuppliers() {
        return new ApiRespone<>(supplierService.getAllSuppliers());
    }

    @GetMapping("/{id}")
    public ApiRespone<SupplierDTO> getSupplierById(@PathVariable Integer id) {
        return new ApiRespone<>(supplierService.getSupplierById(id));
    }

    @DeleteMapping("/{id}")
    public ApiRespone<Boolean> deleteSupplier(@PathVariable Integer id) {
        return new ApiRespone<>(supplierService.deleteSupplier(id));
    }

    @PutMapping("/{id}")
    public ApiRespone<SupplierDTO> updateSupplier(@PathVariable Integer id, @RequestBody SupplierDTO supplierDTO) {
        return new ApiRespone<>(supplierService.updateSupplier(id, supplierDTO));
    }

    @GetMapping("/status")
    public ApiRespone<List<SupplierDTO>> findSuppliersByStatus(@RequestParam Integer status) {
        return new ApiRespone<>(supplierService.findSuppliersByStatus(status));
    }
}
package com.rs.employer.service.purchase;

import com.rs.employer.dao.purchase.SupplierRepository;
import com.rs.employer.dto.SupplierDTO;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.mapper.SupplierMapper;
import com.rs.employer.model.purchase.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository, SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
    }

    public SupplierDTO createSupplier(SupplierDTO dto) {
        Supplier supplier = supplierMapper.toEntity(dto);
        return supplierMapper.toDTO(supplierRepository.save(supplier));
    }

    public List<SupplierDTO> getAllSuppliers() {
        return supplierRepository.findAll()
                .stream()
                .map(supplierMapper::toDTO)
                .collect(Collectors.toList());
    }

    public SupplierDTO getSupplierById(Integer id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SUPPLIER_NOT_FOUND));
        return supplierMapper.toDTO(supplier);
    }

    public boolean deleteSupplier(Integer id) {
        if (!supplierRepository.existsById(id)) {
            return false;
        }
        supplierRepository.deleteById(id);
        return true;
    }

    public SupplierDTO updateSupplier(Integer id, SupplierDTO dto) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SUPPLIER_NOT_FOUND));
        supplier.setSupplierName(dto.getSupplierName());
        supplier.setSupplierCode(dto.getSupplierCode());
        supplier.setSupplierAddress(dto.getSupplierAddress());
        supplier.setSupplierPhone(dto.getSupplierPhone());
        supplier.setSupplierEmail(dto.getSupplierEmail());
        supplier.setSupplierDescription(dto.getSupplierDescription());
        supplier.setTaxCode(dto.getTaxCode());
        supplier.setStatus(dto.getStatus());
        return supplierMapper.toDTO(supplierRepository.save(supplier));
    }

    public List<SupplierDTO> findSuppliersByStatus(Integer status) {
        List<Supplier> suppliers = supplierRepository.findByStatus(status);
        return suppliers.stream().map(supplierMapper::toDTO).collect(Collectors.toList());
    }
}

package com.rs.employer.service.manufacture;

import com.rs.employer.dao.manufacture.BOMRepository;
import com.rs.employer.dao.warehouse.ProductRepository;
import com.rs.employer.dto.Request.Manufacture.BOMRequest;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.model.manufacture.BOM;
import com.rs.employer.model.warehouse.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BOMService {

    private final BOMRepository bomRepository;
    private final ProductRepository productRepository;

    @Autowired
    public BOMService(BOMRepository bomRepository, ProductRepository productRepository) {
        this.bomRepository = bomRepository;
        this.productRepository = productRepository;
    }

    public BOM createBOM(BOMRequest bomRequest) {
        Product product = productRepository.findById(bomRequest.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOTFOUND));
        Product material = productRepository.findById(bomRequest.getMaterialId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOTFOUND));

        BOM bom = new BOM();
        bom.setProduct(product);
        bom.setMaterial(material);
        bom.setQuantity(bomRequest.getQuantity());

        return bomRepository.save(bom);
    }

    public BOM updateBOM(Long bomId, BOMRequest bomRequest) {
        BOM existingBOM = bomRepository.findById(bomId)
                .orElseThrow(() -> new AppException(ErrorCode.BOM_NOT_FOUND));

        Product product = productRepository.findById(bomRequest.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOTFOUND));
        Product material = productRepository.findById(bomRequest.getMaterialId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOTFOUND));

        existingBOM.setProduct(product);
        existingBOM.setMaterial(material);
        existingBOM.setQuantity(bomRequest.getQuantity());

        return bomRepository.save(existingBOM);
    }

    public List<BOM> getAllBOMs() {
        return bomRepository.findAll();
    }

    public BOM getBOMById(Long bomId) {
        return bomRepository.findById(bomId)
                .orElseThrow(() -> new AppException(ErrorCode.BOM_NOT_FOUND));
    }

    public List<BOM> findBOMsByProductId(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOTFOUND));
        return bomRepository.findByProduct(product);
    }

    public List<BOM> findBOMsByMaterialId(Long materialId) {
        Product material = productRepository.findById(materialId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOTFOUND));
        return bomRepository.findByMaterial(material);
    }

    public void deleteBOM(Long bomId) {
        if (!bomRepository.existsById(bomId)) {
            throw new AppException(ErrorCode.BOM_NOT_FOUND);
        }
        bomRepository.deleteById(bomId);
    }
}
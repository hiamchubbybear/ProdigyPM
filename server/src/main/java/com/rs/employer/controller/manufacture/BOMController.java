package com.rs.employer.controller.manufacture;

import com.rs.employer.apirespone.ApiRespone;
import com.rs.employer.dto.Request.Manufacture.BOMRequest;
import com.rs.employer.model.manufacture.BOM;
import com.rs.employer.service.manufacture.BOMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boms")
@CrossOrigin("*")
public class BOMController {

    private final BOMService bomService;

    @Autowired
    public BOMController(BOMService bomService) {
        this.bomService = bomService;
    }

    @PostMapping
    public ApiRespone<BOM> createBOM(@RequestBody BOMRequest bomRequest) {
        return new ApiRespone<>(bomService.createBOM(bomRequest));
    }

    @PutMapping("/{id}")
    public ApiRespone<BOM> updateBOM(@PathVariable Long id, @RequestBody BOMRequest bomRequest) {
        return new ApiRespone<>(bomService.updateBOM(id, bomRequest));
    }

    @GetMapping
    public ApiRespone<List<BOM>> getAllBOMs() {
        return new ApiRespone<>(bomService.getAllBOMs());
    }

    @GetMapping("/{id}")
    public ApiRespone<BOM> getBOMById(@PathVariable Long id) {
        return new ApiRespone<>(bomService.getBOMById(id));
    }

    @GetMapping("/product/{productId}")
    public ApiRespone<List<BOM>> findBOMsByProductId(@PathVariable Long productId) {
        return new ApiRespone<>(bomService.findBOMsByProductId(productId));
    }

    @GetMapping("/material/{materialId}")
    public ApiRespone<List<BOM>> findBOMsByMaterialId(@PathVariable Long materialId) {
        return new ApiRespone<>(bomService.findBOMsByMaterialId(materialId));
    }

    @DeleteMapping("/{id}")
    public ApiRespone<Void> deleteBOM(@PathVariable Long id) {
        bomService.deleteBOM(id);
        return new ApiRespone<>(null);
    }
}
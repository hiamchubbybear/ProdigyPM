package com.rs.employer.controller.human_resource;

import com.rs.employer.apirespone.ApiRespone;
import com.rs.employer.dto.Request.human_resource.PayrollRequest;
import com.rs.employer.model.human_resource.Payroll;
import com.rs.employer.service.human_resource.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payrolls")
@CrossOrigin(origins = "*")
public class PayrollController {

    private final PayrollService payrollService;

    @Autowired
    public PayrollController(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    // Lấy danh sách tất cả Payrolls
    @GetMapping
    public ApiRespone<List<Payroll>> getAllPayrolls() {
        return new ApiRespone<>(payrollService.getAllPayrolls());
    }

    // Lấy thông tin Payroll theo ID
    @GetMapping("/{id}")
    public ApiRespone<Payroll> getPayrollById(@PathVariable Long id) {
        return new ApiRespone<>(payrollService.getPayrollById(id));
    }

    // Tạo Payroll mới
    @PostMapping
    public ApiRespone<Payroll> createPayroll(@RequestBody PayrollRequest payrollRequest) {
        return new ApiRespone<>(payrollService.createPayroll(payrollRequest));
    }

    // Cập nhật Payroll theo ID
    @PutMapping("/{id}")
    public ApiRespone<Payroll> updatePayroll(@PathVariable Long id, @RequestBody PayrollRequest payrollRequest) {
        return new ApiRespone<>(payrollService.updatePayroll(payrollRequest, id));
    }

    // Xóa Payroll theo ID
    @DeleteMapping("/{id}")
    public ApiRespone<Void> deletePayroll(@PathVariable Long id) {
        payrollService.deletePayroll(id);
        return new ApiRespone<>(null);
    }

    // Lấy Payroll của nhân viên theo tên
    @GetMapping("/employee/{employeeName}")
    public ApiRespone<List<Payroll>> getPayrollsByEmployeeName(@PathVariable String employeeName) {
        return new ApiRespone<>(payrollService.getPayrollByEmployeeName(employeeName));
    }

    // Tìm kiếm Payroll với các bộ lọc
    @GetMapping("/search")
    public ApiRespone<List<Payroll>> searchPayrolls(@RequestParam Map<String, String> filters) {
        return new ApiRespone<>(payrollService.searchPayrolls(filters));
    }
}
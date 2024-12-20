package com.rs.employer.service.human_resource;

import com.rs.employer.dao.customer.CustomerRepo;
import com.rs.employer.dao.human_resource.EmployeeRepository;
import com.rs.employer.dao.human_resource.PayrollRepository;
import com.rs.employer.dto.Request.human_resource.PayrollRequest;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.mapper.human_resource.PayrollMapper;
import com.rs.employer.model.customer.Customer;
import com.rs.employer.model.human_resource.Employee;
import com.rs.employer.model.human_resource.Payroll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PayrollService {

    private final PayrollRepository payrollRepository;
    private final PayrollMapper payrollMapper;
    private final CustomerRepo customerRepo;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public PayrollService(PayrollRepository payrollRepository, PayrollMapper payrollMapper, CustomerRepo customerRepo, EmployeeRepository employeeRepository) {
        this.payrollRepository = payrollRepository;
        this.payrollMapper = payrollMapper;
        this.customerRepo = customerRepo;
        this.employeeRepository = employeeRepository;
    }

    // Lấy tất cả Payrolls
    public List<Payroll> getAllPayrolls() {
        return payrollRepository.findAll();
    }

    // Lấy Payroll theo ID
    public Payroll getPayrollById(Long id) {
        return payrollRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION));
    }

    // Tạo Payroll mới
    public Payroll createPayroll(PayrollRequest payrollRequest) {
        // Chuyển đổi từ PayrollRequest thành Payroll entity
        Payroll payroll = payrollMapper.payrollFromPayRollRequest(payrollRequest);

        // Lấy Employee và Customer từ các repo
        Employee employee = employeeRepository.findByEmployeeName(payrollRequest.getEmployeeName())
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION));
        Customer customer = customerRepo.findByUsername(payrollRequest.getCustomerName())
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION));

        // Gán Employee và Customer vào Payroll
        payroll.setEmployee(employee);
        payroll.setCustomer(customer);

        // Tính lương thực nhận
        payroll.setNetsalary(calculateNetSalary(payroll.getBasicSalary(), payroll.getAllowance(), payroll.getDeductions()));

        // Lưu Payroll vào cơ sở dữ liệu
        payroll.setCreateDate(new Date());
        return payrollRepository.save(payroll);
    }

    // Cập nhật Payroll
    public Payroll updatePayroll(PayrollRequest payrollRequest, Long id) {
        // Tìm Payroll hiện tại từ ID
        Payroll existingPayroll = payrollRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION));

        // Cập nhật các trường thông tin
        existingPayroll.setPeriod(payrollRequest.getPeriod());
        existingPayroll.setBasicSalary(payrollRequest.getBasicSalary());
        existingPayroll.setAllowance(payrollRequest.getAllowance());
        existingPayroll.setDeductions(payrollRequest.getDeductions());
        existingPayroll.setStatus(payrollRequest.getStatus());

        // Tính lại lương thực nhận
        existingPayroll.setNetsalary(calculateNetSalary(existingPayroll.getBasicSalary(),
                existingPayroll.getAllowance(),
                existingPayroll.getDeductions()));

        // Cập nhật thời gian cập nhật
        existingPayroll.setCreateDate(new Date());

        return payrollRepository.save(existingPayroll);
    }

    // Xóa Payroll
    public void deletePayroll(Long id) {
        Payroll payroll = payrollRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION));
        payrollRepository.delete(payroll);
    }

    // Tính toán lương thực nhận (sau khi trừ phụ cấp và khấu trừ)
    private BigDecimal calculateNetSalary(BigDecimal basicSalary, BigDecimal allowance, BigDecimal deductions) {
        // Tính lương thực nhận sau khi cộng phụ cấp và trừ khấu trừ
        if (basicSalary == null || allowance == null || deductions == null) {
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
        }
        BigDecimal totalSalary = basicSalary.add(allowance).subtract(deductions);
        return totalSalary;
    }

    // Lấy Payroll của nhân viên theo tên
    public List<Payroll> getPayrollByEmployeeName(String employeeName) {
        Employee employee = employeeRepository.findByEmployeeName(employeeName)
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION));
        return payrollRepository.findByEmployee(employee);
    }

    // Tìm kiếm Payroll theo các bộ lọc
    public List<Payroll> searchPayrolls(Map<String, String> filters) {
        String employeeName = filters.get("employeeName");
        String customerName = filters.get("customerName");
        String status = filters.get("status");

        if (employeeName != null) {
            Employee employee = employeeRepository.findByEmployeeName(employeeName)
                    .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION));
            return payrollRepository.findByEmployeeAndStatus(employee, status);
        }

        if (customerName != null) {
            Customer customer = customerRepo.findByUsername(customerName)
                    .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION));
            return payrollRepository.findByCustomerAndStatus(customer, status);
        }
        return payrollRepository.findAll(); // Trả về tất cả nếu không có bộ lọc
    }
}
package com.rs.employer.service.human_resource;

import com.rs.employer.dao.customer.CustomerRepository;
import com.rs.employer.dao.human_resource.EmployeeRepository;
import com.rs.employer.dao.human_resource.PayrollRepository;
import com.rs.employer.dto.Request.human_resource.PayrollRequest;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.mapper.PayrollMapper;
import com.rs.employer.model.customer.Customer;
import com.rs.employer.model.human_resource.Employee;
import com.rs.employer.model.human_resource.Payroll;
import jakarta.transaction.Transactional;
import jdk.jfr.TransitionTo;
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
    private final CustomerRepository customerRepo;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public PayrollService(PayrollRepository payrollRepository, PayrollMapper payrollMapper, CustomerRepository customerRepo, EmployeeRepository employeeRepository) {
        this.payrollRepository = payrollRepository;
        this.payrollMapper = payrollMapper;
        this.customerRepo = customerRepo;
        this.employeeRepository = employeeRepository;
    }

    public List<Payroll> getAllPayrolls() {
        return payrollRepository.findAll();
    }

    public Payroll getPayrollById(Long id) {
        return payrollRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION));
    }

    public Payroll createPayroll(PayrollRequest payrollRequest) {
        Payroll payroll = payrollMapper.payrollFromPayRollRequest(payrollRequest);
        Employee employee = employeeRepository.findByEmployeeName(payrollRequest.getEmployeeName())
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_FOUND));
        Customer customer = customerRepo.findByUsername(payrollRequest.getCustomerName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        payroll.setEmployee(employee);
        payroll.setCustomer(customer);
        payroll.setNetsalary(calculateNetSalary(payroll.getBasicSalary(), payroll.getAllowance(), payroll.getDeductions()));
        payroll.setCreateDate(new Date());
        return payrollRepository.save(payroll);
    }
    @Transactional
    public Payroll updatePayroll(PayrollRequest payrollRequest, Long id) {
        Payroll existingPayroll = payrollRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION));
        existingPayroll.setPeriod(payrollRequest.getPeriod());
        existingPayroll.setBasicSalary(payrollRequest.getBasicSalary());
        existingPayroll.setAllowance(payrollRequest.getAllowance());
        existingPayroll.setDeductions(payrollRequest.getDeductions());
        existingPayroll.setStatus(payrollRequest.getStatus());
        existingPayroll.setNetsalary(calculateNetSalary(existingPayroll.getBasicSalary(),
                existingPayroll.getAllowance(),
                existingPayroll.getDeductions()));
        existingPayroll.setCreateDate(new Date());
        return payrollRepository.save(existingPayroll);
    }


    public void deletePayroll(Long id) {
        Payroll payroll = payrollRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION));
        payrollRepository.delete(payroll);
    }


    private BigDecimal calculateNetSalary(BigDecimal basicSalary, BigDecimal allowance, BigDecimal deductions) {

        if (basicSalary == null || allowance == null || deductions == null) {
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
        }
        BigDecimal totalSalary = basicSalary.add(allowance).subtract(deductions);
        return totalSalary;
    }


    public List<Payroll> getPayrollByEmployeeName(String employeeName) {
        Employee employee = employeeRepository.findByEmployeeName(employeeName)
                .orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION));
        return payrollRepository.findByEmployee(employee);
    }

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
        return payrollRepository.findAll();
    }
}
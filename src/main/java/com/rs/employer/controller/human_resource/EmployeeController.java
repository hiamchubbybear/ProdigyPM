package com.rs.employer.controller.human_resource;

import com.rs.employer.dao.human_resource.EmployeeRepository;
import com.rs.employer.dao.human_resource.PayrollRepository;
import com.rs.employer.dao.others.DepartmentRepository;
import com.rs.employer.dto.Request.EmployeeRequest;
import com.rs.employer.mapper.human_resource.EmployeeMapper;
import com.rs.employer.model.human_resource.Employee;
import com.rs.employer.model.human_resource.Payroll;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/hr")
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper mapper;
    private final DepartmentRepository departmentRepository;
    private final PayrollRepository payrollRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository, EmployeeMapper mapper,
                              DepartmentRepository departmentRepository, PayrollRepository payrollRepository) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
        this.departmentRepository = departmentRepository;
        this.payrollRepository = payrollRepository;
    }

    // Lấy tất cả nhân viên
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeRepository.findAll());
    }

    // Lấy nhân viên theo ID
    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_FOUND));
    }

    // Thêm nhân viên mới
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // Cập nhật thông tin nhân viên
    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody EmployeeRequest employeeRequest) {
        Set<Long> payrollIds = employeeRequest.getPayrolls().stream()
                .map(Long::valueOf)
                .collect(Collectors.toSet());
        Employee employee = mapper.toEmployeeRequest(employeeRequest);
        // Lấy thông tin bộ phận
        departmentRepository.findByDepartmentName(employeeRequest.getDepartmentName())
                .ifPresentOrElse(employee::setDepartment,
                        () -> {
                            throw new AppException(ErrorCode.DEPARTMENT_NOT_FOUND);
                        });

        // Lấy tất cả Payrolls từ Payroll IDs
        Set<Payroll> payrolls = new HashSet<>(payrollRepository.findAllById(payrollIds));
        employee.setPayrolls(payrolls);

        return employeeRepository.save(employee);
    }
    // Xóa nhân viên
    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_FOUND));
        employeeRepository.delete(employee);
    }

    // Tìm kiếm nhân viên theo tên
    @GetMapping("/employees/search/name")
    public List<Employee> searchEmployeesByName(@RequestParam String name) {
        return employeeRepository.findByEmployeeNameContainingIgnoreCase(name);
    }

    // Tìm kiếm nhân viên theo bộ phận
    @GetMapping("/employees/search/department")
    public List<Employee> searchEmployeesByDepartment(@RequestParam String departmentName) {
        return employeeRepository.findByDepartmentDepartmentNameContainingIgnoreCase(departmentName);
    }

    // Tìm kiếm nhân viên theo tên hoặc bộ phận
    @GetMapping("/employees/search")
    public List<Employee> searchEmployees(@RequestParam Map<String, String> filters) {
        String name = filters.get("name");
        String departmentName = filters.get("departmentName");

        if (name != null && departmentName != null) {
            return employeeRepository.findByEmployeeNameContainingIgnoreCaseAAndDepartmentDepartmentNameContainingIgnoreCase
                    (name, departmentName);
        } else if (name != null) {
            return searchEmployeesByName(name);
        } else if (departmentName != null) {
            return searchEmployeesByDepartment(departmentName);
        } else {
            return getAllEmployees();
        }
    }
}
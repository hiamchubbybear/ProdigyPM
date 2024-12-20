package com.rs.employer.service.human_resource;

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
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper mapper;
    private final DepartmentRepository departmentRepository;
    private final PayrollRepository payrollRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper mapper,
                           DepartmentRepository departmentRepository, PayrollRepository payrollRepository) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
        this.departmentRepository = departmentRepository;
        this.payrollRepository = payrollRepository;
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeRepository.findAll());
    }

    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_FOUND));
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(EmployeeRequest employeeRequest) {
        Set<Long> payrollIds = employeeRequest.getPayrolls().stream()
                .map(Long::valueOf)
                .collect(Collectors.toSet());
        Employee employee = mapper.toEmployeeRequest(employeeRequest);
        departmentRepository.findByDepartmentName(employeeRequest.getDepartmentName())
                .ifPresentOrElse(employee::setDepartment,
                        () -> {
                            throw new AppException(ErrorCode.DEPARTMENT_NOT_FOUND);
                        });
        Set<Payroll> payrolls = new HashSet<>(payrollRepository.findAllById(payrollIds));
        employee.setPayrolls(payrolls);

        return employeeRepository.save(employee);
    }

    public void deleteEmployee(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_FOUND));
        employeeRepository.delete(employee);
    }

    public List<Employee> searchEmployeesByName(String name) {
        return employeeRepository.findByEmployeeNameContainingIgnoreCase(name);
    }

    public List<Employee> searchEmployeesByDepartment(String departmentName) {
        return employeeRepository.findByEmployeeNameContainingIgnoreCase(departmentName);
    }

    public List<Employee> searchEmployees(Map<String, String> filters) {
        String name = filters.get("name");
        String departmentName = filters.get("departmentName");

        if (name != null && departmentName != null) {
            return employeeRepository.findByEmployeeNameContainingIgnoreCaseAAndDepartmentDepartmentNameContainingIgnoreCase(name, departmentName);
        } else if (name != null) {
            return searchEmployeesByName(name);
        } else if (departmentName != null) {
            return searchEmployeesByDepartment(departmentName);
        } else {
            return getAllEmployees();
        }
    }
}
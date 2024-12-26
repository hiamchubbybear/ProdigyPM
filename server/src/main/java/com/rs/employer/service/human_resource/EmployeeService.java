package com.rs.employer.service.human_resource;

import com.rs.employer.controller.human_resource.EmployeeController;
import com.rs.employer.dao.human_resource.EmployeeRepository;
import com.rs.employer.dao.human_resource.PayrollRepository;
import com.rs.employer.dao.others.DepartmentRepository;
import com.rs.employer.dto.Request.EmployeeRequest;
import com.rs.employer.mapper.EmployeeMapper;
import com.rs.employer.model.human_resource.Employee;
import com.rs.employer.model.human_resource.Payroll;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
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

    public Employee addEmployee(EmployeeRequest request) {
        Employee employee = mapper.toEmployeeRequest(request);
        if (!request.getPayrolls().isEmpty()) {
            employee.setPayrolls(new HashSet<>(payrollRepository.findAllById(request.getPayrolls())));
        }
        if (!request.getDepartmentName().isEmpty()) {
            employee.setDepartment(departmentRepository.findByDepartmentName(
                    request.getDepartmentName()).orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_FOUND)));
        }
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(EmployeeRequest employeeRequest) {
        logger.info("Request to update employee : {}", employeeRequest);
        Set<Long> payrollIds = employeeRequest.getPayrolls().stream()
                .collect(Collectors.toSet());
        Set<Payroll> payrolls = new HashSet<>(payrollRepository.findAllById(payrollIds));
        Employee employee = mapper.toEmployeeRequest(employeeRequest);
        logger.info("Updating employee: {}", employee);
        if (employee.getDepartment() != null) {
            departmentRepository.findByDepartmentName(employeeRequest.getDepartmentName())
                    .ifPresentOrElse(employee::setDepartment,
                            () -> {
                                throw new AppException(ErrorCode.DEPARTMENT_NOT_FOUND);
                            });
        } else {
            employee.setDepartment(null);
        }
        employee.setPayrolls(payrolls);
        return employeeRepository.save(employee);
    }

    public Boolean deleteEmployee(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_FOUND));
        employeeRepository.delete(employee);
        return true;

    }

    /*
        @status : suspend
         */
    public List<Employee> searchEmployeesByName(String name) {
        return employeeRepository.findByEmployeeNameContainingIgnoreCase(name);
    }

    /*
        @status : suspend
         */
    public List<Employee> searchEmployeesByDepartment(String departmentName) {
        return employeeRepository.findByEmployeeNameContainingIgnoreCase(departmentName);
    }

    /*
    @status : suspend
     */
    public List<Employee> searchEmployees(Map<String, String> filters) {
        logger.info("Search employees called with filters: {}", filters);  // Log tham số đầu vào
        String name = filters.get("name");
        String departmentName = filters.get("departmentName");
        List<Employee> employees;
        if (name != null && departmentName != null) {
            logger.info("Searching employees by name: {} and department: {}", name, departmentName);
            employees = employeeRepository.findByEmployeeNameContainingIgnoreCaseAndDepartmentDepartmentNameContainingIgnoreCase(name, departmentName);
        } else if (name != null) {
            logger.info("Searching employees by name: {}", name);
            employees = employeeRepository.findByEmployeeNameContainingIgnoreCase(name);
        } else if (departmentName != null) {
            logger.info("Searching employees by department: {}", departmentName);
            employees = employeeRepository.findByDepartmentDepartmentNameContainingIgnoreCase(departmentName);
        } else {
            logger.info("Fetching all employees as no filters are provided");
            employees = new ArrayList<>(employeeRepository.findAll());
        }
        logger.info("Found {} employees", employees.size());  // Log số lượng nhân viên tìm được
        return employees;
    }
}
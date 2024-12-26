package com.rs.employer.controller.human_resource;

import com.rs.employer.apirespone.ApiRespone;
import com.rs.employer.dao.human_resource.EmployeeRepository;
import com.rs.employer.dao.human_resource.PayrollRepository;
import com.rs.employer.dao.others.DepartmentRepository;
import com.rs.employer.dto.Request.EmployeeRequest;
import com.rs.employer.mapper.EmployeeMapper;
import com.rs.employer.model.human_resource.Employee;
import com.rs.employer.service.human_resource.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/hr")
@CrossOrigin(origins = "*")
public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository, EmployeeMapper mapper, DepartmentRepository departmentRepository, PayrollRepository payrollRepository, EmployeeService employeeService) {
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ApiRespone<List<Employee>> getAllEmployees() {
        return new ApiRespone<>(employeeService.getAllEmployees());
    }

    @GetMapping("/employees/{id}")
    public ApiRespone<Employee> getEmployeeById(@PathVariable int id) {
        return new ApiRespone<>(employeeService.getEmployeeById(id));
    }

    @PostMapping("/employees")
    public ApiRespone<Employee> addEmployee(@RequestBody EmployeeRequest employee) {
        return new ApiRespone<>(employeeService.addEmployee(employee));
    }

    @PutMapping("/employees")
    public ApiRespone<Employee> updateEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return new ApiRespone<>(employeeService.updateEmployee(employeeRequest));
    }

    @DeleteMapping("/employees/{id}")
    public ApiRespone<Boolean> deleteEmployee(@PathVariable int id) {
        return new ApiRespone<>(employeeService.deleteEmployee(id));
    }

    @GetMapping("/employees/search/name")
    public ApiRespone<List<Employee>> searchEmployeesByName(@RequestParam String name) {
        return new ApiRespone<>(employeeRepository.findByEmployeeNameContainingIgnoreCase(name));
    }

    @GetMapping("/employees/search/department")
    public ApiRespone<List<Employee>> searchEmployeesByDepartment(@RequestParam String departmentName) {
        return new ApiRespone<>(employeeRepository.findByDepartmentDepartmentNameContainingIgnoreCase(departmentName));
    }

    @GetMapping("/employees/search")
    public ApiRespone<List<Employee>> searchEmployees(@RequestParam Map<String, String> filters) {
        return new ApiRespone<>(employeeService.searchEmployees(filters));
    }
}
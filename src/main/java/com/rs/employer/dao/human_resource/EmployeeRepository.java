package com.rs.employer.dao.human_resource;

import com.rs.employer.model.human_resource.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByEmployeeName(String employeeId);

    List<Employee> findByEmployeeNameContainingIgnoreCase(String name);

    List<Employee> findByDepartmentDepartmentNameContainingIgnoreCase(String departmentName);

    List<Employee> findByEmployeeNameContainingIgnoreCaseAAndDepartmentDepartmentNameContainingIgnoreCase(String name, String departmentName);
}

//findByEmployeeNameContainingIgnoreCaseAndDepartmentContainingIgnoreCase
package com.rs.employer.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rs.employer.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}


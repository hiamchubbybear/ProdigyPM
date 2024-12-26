package com.rs.employer.dao.human_resource;

import com.rs.employer.model.customer.Customer;
import com.rs.employer.model.human_resource.Employee;
import com.rs.employer.model.human_resource.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll  , Long> {
    List<Payroll> findByEmployeeAndStatus(Employee employee, String status);

    List<Payroll> findByEmployee(Employee employee);

    List<Payroll> findByCustomerAndStatus(Customer customer, String status);
}

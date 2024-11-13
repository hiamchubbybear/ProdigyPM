package com.rs.employer.dao.humanresource;

import com.rs.employer.model.human_resource.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll,Long> {
}

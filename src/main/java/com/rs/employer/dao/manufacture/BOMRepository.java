package com.rs.employer.dao.manufacture;

import com.rs.employer.model.manufacture.BOM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BOMRepository extends JpaRepository<BOM, Long> {
}

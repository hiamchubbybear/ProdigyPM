package com.rs.employer.dao;

import com.rs.employer.model.account.ChartOfAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<ChartOfAccounts,String> {
    ChartOfAccounts findByAccountId(String accountNumber);
}

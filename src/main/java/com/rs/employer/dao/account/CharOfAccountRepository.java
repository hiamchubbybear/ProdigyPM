package com.rs.employer.dao.account;

import com.rs.employer.model.account.ChartOfAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharOfAccountRepository extends JpaRepository<ChartOfAccounts,String> {

}

package com.rs.employer.dao.account;

import com.rs.employer.model.account.ChartOfAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChartOfAccountRepository extends JpaRepository<ChartOfAccounts,String> {
    @Query("SELECT c.balance from ChartOfAccounts c where c.parentAccount.accountName= :name and c.accountId =:id")
    public List<ChartOfAccounts> findByAccountName(@Param("name") String name , @Param("id") String id);

}

package com.rs.employer.model.account;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class ChartOfAccounts {
    @Id
//    @GeneratedValue( strategy = GenerationType.AUTO)
    private String accountId;
    private String accountName;
    private String accountType;
    @ManyToOne
    @JoinColumn(name = "parent_account")
    private ChartOfAccounts parentAccount;
    @OneToMany(mappedBy = "parentAccount")
    private List<ChartOfAccounts> childrenAccounts;
    private java.math.BigDecimal balance;

    public ChartOfAccounts() {
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public ChartOfAccounts getParentAccount() {
        return parentAccount;
    }

    public void setParentAccount(ChartOfAccounts parentAccount) {
        this.parentAccount = parentAccount;
    }

    public List<ChartOfAccounts> getChildrenAccounts() {
        return childrenAccounts;
    }

    public void setChildrenAccounts(List<ChartOfAccounts> childrenAccounts) {
        this.childrenAccounts = childrenAccounts;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public ChartOfAccounts(String accountId, String accountName, String accountType, ChartOfAccounts parentAccount, List<ChartOfAccounts> childrenAccounts, BigDecimal balance) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountType = accountType;
        this.parentAccount = parentAccount;
        this.childrenAccounts = childrenAccounts;
        this.balance = balance;
    }
}

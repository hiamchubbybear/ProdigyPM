package com.rs.employer.model.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class ChartOfAccounts {
    @Id
    private String accountId;
    private String accountName;
    private String accountType;
    @ManyToOne
    @JoinColumn(name = "parent_account")
    private ChartOfAccounts parentAccount;
    @JsonIgnore
    @OneToMany(mappedBy = "parentAccount")
    private List<ChartOfAccounts> childrenAccounts;

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

    public ChartOfAccounts(String accountId, String accountName, String accountType, ChartOfAccounts parentAccount, List<ChartOfAccounts> childrenAccounts) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountType = accountType;
        this.parentAccount = parentAccount;
        this.childrenAccounts = childrenAccounts;
    }
}

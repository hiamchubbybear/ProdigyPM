package com.rs.employer.model.account;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

@Entity
public class JournalEntryDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer entryDetailId;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "journalEntry_Detail")
    private JournalEntry journalEntry;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "account_journeydetail")
    private ChartOfAccounts account;
    private java.math.BigDecimal debit;
    private java.math.BigDecimal credit;
    private LocalDate createDate;
    private String createBy;
    private Currency currency;


    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public JournalEntryDetail(Integer entryDetailId, JournalEntry journalEntry, ChartOfAccounts account, BigDecimal debit, BigDecimal credit, LocalDate createDate, String createBy, Currency currency) {
        this.entryDetailId = entryDetailId;
        this.journalEntry = journalEntry;
        this.account = account;
        this.debit = debit;
        this.credit = credit;
        this.createDate = createDate;
        this.createBy = createBy;
        this.currency = currency;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public JournalEntryDetail(Integer entryDetailId, JournalEntry journalEntry, ChartOfAccounts account, BigDecimal debit, BigDecimal credit, LocalDate createDate, Currency currency) {
        this.entryDetailId = entryDetailId;
        this.journalEntry = journalEntry;
        this.account = account;
        this.debit = debit;
        this.credit = credit;
        this.createDate = createDate;
        this.currency = currency;
    }

    public JournalEntryDetail(Integer entryDetailId, JournalEntry journalEntry, ChartOfAccounts account, BigDecimal debit, BigDecimal credit, Currency currency) {
        this.entryDetailId = entryDetailId;
        this.journalEntry = journalEntry;
        this.account = account;
        this.debit = debit;
        this.credit = credit;
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public JournalEntryDetail() {
    }

    public Integer getEntryDetailId() {
        return entryDetailId;
    }

    public void setEntryDetailId(Integer entryDetailId) {
        this.entryDetailId = entryDetailId;
    }

    public JournalEntry getJournalEntry() {
        return journalEntry;
    }

    public void setJournalEntry(JournalEntry journalEntry) {
        this.journalEntry = journalEntry;
    }

    public ChartOfAccounts getAccount() {
        return account;
    }

    public void setAccount(ChartOfAccounts account) {
        this.account = account;
    }

    public BigDecimal getDebit() {
        return debit;
    }

    public void setDebit(BigDecimal debit) {
        this.debit = debit;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public JournalEntryDetail(Integer entryDetailId, JournalEntry journalEntry, ChartOfAccounts account, BigDecimal debit, BigDecimal credit) {
        this.entryDetailId = entryDetailId;
        this.journalEntry = journalEntry;
        this.account = account;
        this.debit = debit;
        this.credit = credit;
    }
}

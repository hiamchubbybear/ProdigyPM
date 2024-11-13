package com.rs.employer.model.account;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Currency;

@Entity
public class JournalEntryDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer entryDetailId;
    @ManyToOne
    @JoinColumn(name = "journalEntry_Detail")
    private JournalEntry journalEntry;
    @ManyToOne
    @JoinColumn(name = "account_journeydetail")
    private ChartOfAccounts account;
    private java.math.BigDecimal debit;
    private java.math.BigDecimal credit;
    private Currency currency;


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

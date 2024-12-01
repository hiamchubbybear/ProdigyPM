package com.rs.employer.dto.Request;

import java.math.BigDecimal;
import java.time.LocalDate;

public class JournalEntryDetailRequestDTO {

    private LocalDate journalEntryDate;
    private String chartOfAccountCode;
    private BigDecimal debit;
    private BigDecimal credit;
    private String currency;

    public LocalDate getJournalEntryDate() {
        return journalEntryDate;
    }

    public void setJournalEntryDate(LocalDate journalEntryDate) {
        this.journalEntryDate = journalEntryDate;
    }

    public String getChartOfAccountCode() {
        return chartOfAccountCode;
    }

    public void setChartOfAccountCode(String chartOfAccountCode) {
        this.chartOfAccountCode = chartOfAccountCode;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public JournalEntryDetailRequestDTO(LocalDate journalEntryDate, String chartOfAccountCode, BigDecimal debit, BigDecimal credit, String currency) {
        this.journalEntryDate = journalEntryDate;
        this.chartOfAccountCode = chartOfAccountCode;
        this.debit = debit;
        this.credit = credit;
        this.currency = currency;
    }
}

package com.rs.employer.dto.Request;

import java.math.BigDecimal;

public class JournalEntryDetailRequestDTO {

    private Integer entryDetailId;
    private Integer journalEntryId;
    private String chartOfAccountCode;
    private BigDecimal debit;
    private BigDecimal credit;
    private String currency;

    public Integer getEntryDetailId() {
        return entryDetailId;
    }

    public void setEntryDetailId(Integer entryDetailId) {
        this.entryDetailId = entryDetailId;
    }

    public Integer getJournalEntryId() {
        return journalEntryId;
    }

    public void setJournalEntryId(Integer journalEntryId) {
        this.journalEntryId = journalEntryId;
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
}

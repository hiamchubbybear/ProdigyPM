package com.rs.employer.model.account;

import jakarta.persistence.*;

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

}

package com.rs.employer.model.account;

import com.rs.employer.model.customer.Customer;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
@Entity
public class JournalEntry {
    @Id
    private Integer entryId;
    private java.time.LocalDate entryDate;
    private String description;
    private Integer status;
    @ManyToOne
    @JoinColumn(name = "createBy")
    private Customer createdBy;
    private java.time.LocalDateTime createdDate;
    @OneToMany(mappedBy = "journalEntry")
    private Set<JournalEntryDetail> details;

    public JournalEntry() {
    }

    public Integer getEntryId() {
        return entryId;
    }

    public void setEntryId(Integer entryId) {
        this.entryId = entryId;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Customer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Customer createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Set<JournalEntryDetail> getDetails() {
        return details;
    }

    public void setDetails(Set<JournalEntryDetail> details) {
        this.details = details;
    }

    public JournalEntry(Integer entryId, LocalDate entryDate, String description, Integer status, Customer createdBy, LocalDateTime createdDate, Set<JournalEntryDetail> details) {
        this.entryId = entryId;
        this.entryDate = entryDate;
        this.description = description;
        this.status = status;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.details = details;
    }
}
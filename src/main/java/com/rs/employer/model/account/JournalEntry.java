package com.rs.employer.model.account;

import com.rs.employer.model.customer.Customer;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
@Entity
public class JournalEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer entryId;
    @Column(unique = true)
    private java.time.LocalDate entryDate;
    private String description;
    private Integer status;
    @ManyToOne
    private Customer createdBy;
    @CreatedDate
    private java.time.LocalDateTime createdDate;
    @OneToMany(mappedBy = "journalEntry")
    private List<JournalEntryDetail> details;
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

    public List<JournalEntryDetail> getDetails() {
        return details;
    }

    public void setDetails(List<JournalEntryDetail> details) {
        this.details = details;
    }

    public JournalEntry(Integer entryId, LocalDate entryDate, String description, Integer status, Customer createdBy, LocalDateTime createdDate, List<JournalEntryDetail> details) {
        this.entryId = entryId;
        this.entryDate = entryDate;
        this.description = description;
        this.status = status;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.details = details;
    }
}
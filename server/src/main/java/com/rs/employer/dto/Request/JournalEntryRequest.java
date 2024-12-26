package com.rs.employer.dto.Request;

import com.rs.employer.model.account.JournalEntryDetail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class JournalEntryRequest {
    private Integer entryId;
    private LocalDate entryDate;
    private String description;
    private Integer status;
    private String createBy;
    private LocalDateTime createdDate;


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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public JournalEntryRequest(Integer entryId, LocalDate entryDate, String description, Integer status, String createBy, LocalDateTime createdDate) {
        this.entryId = entryId;
        this.entryDate = entryDate;
        this.description = description;
        this.status = status;
        this.createBy = createBy;
        this.createdDate = createdDate;
    }
}

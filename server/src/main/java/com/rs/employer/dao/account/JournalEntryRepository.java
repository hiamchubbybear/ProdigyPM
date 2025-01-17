package com.rs.employer.dao.account;


import com.rs.employer.model.account.JournalEntry;
import com.rs.employer.model.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface JournalEntryRepository extends JpaRepository<JournalEntry, Integer> {
    @Query("SELECT je FROM JournalEntry je WHERE je.createdBy = :username")
    Optional<JournalEntry> findByCreatedByUsername(String username);
    Boolean existsByEntryDate(LocalDate date);
    Page<JournalEntry> findByCreatedByUsername(String createBy , Pageable pageable);

    List<JournalEntry> findByStatus(Integer status);
    List<JournalEntry> findByDescriptionContaining(String description);
    Optional<JournalEntry> findByEntryDate(LocalDate date);
    List<JournalEntry> findByEntryDateBetween(java.time.LocalDate fromDate, java.time.LocalDate toDate);
    @Query("SELECT j.entryId FROM JournalEntry j ")
    List<Integer> findAllEntryIds();
    @Query("SELECT j FROM JournalEntry j WHERE j.createdBy = :createdBy AND j.entryDate BETWEEN :startDate AND :endDate")
    List<JournalEntry> findJournalEntriesByCustomerAndDateRange(@Param("createdBy") Customer createdBy,
                                                                @Param("startDate") LocalDate startDate,
                                                                @Param("endDate") LocalDate endDate);
    JournalEntry findByEntryIdAndAndCreatedBy(Integer id, Customer createdBy);
}



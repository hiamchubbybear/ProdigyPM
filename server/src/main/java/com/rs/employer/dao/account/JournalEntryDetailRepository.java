package com.rs.employer.dao.account;

import com.rs.employer.model.account.JournalEntryDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface JournalEntryDetailRepository extends JpaRepository<JournalEntryDetail, Integer> {
    @Query("SELECT j FROM JournalEntryDetail j WHERE FUNCTION('MONTH', j.createDate) = :month AND FUNCTION('YEAR', j.createDate) = :year")
    List<JournalEntryDetail> findAllByMonthAndYear(@Param("month") int month, @Param("year") int year);
    List<JournalEntryDetail> findAllByCreateDateBetween(LocalDate startOfMonth, LocalDate endOfMonth);
}
package com.rs.employer.dao.account;

import com.rs.employer.model.account.JournalEntryDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalEntryDetailRepository extends JpaRepository<JournalEntryDetail, Integer> {
}

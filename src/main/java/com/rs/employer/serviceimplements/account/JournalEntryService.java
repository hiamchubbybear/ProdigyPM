package com.rs.employer.serviceimplements.account;

import com.rs.employer.dao.account.JournalEntryRepository;
import com.rs.employer.dao.customer.CustomerRepo;
import com.rs.employer.dto.Request.JournalEntryRequest;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.mapper.JournalEntryMapper;
import com.rs.employer.model.account.JournalEntry;
import com.rs.employer.model.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

	private JournalEntryRepository journalEntryRepository;
	private JournalEntryMapper journalEntryMapper;
	private CustomerRepo customerRepo;

	@Autowired
	JournalEntryService(JournalEntryRepository journalEntryRepository, JournalEntryMapper journalEntryMapper, CustomerRepo customerRepo) {
		this.journalEntryRepository = journalEntryRepository;
		this.journalEntryMapper = journalEntryMapper;
		this.customerRepo = customerRepo;
	}


	public List<JournalEntry> getAllJournalEntries() {
		String createBy = SecurityContextHolder.getContext().getAuthentication().getName();
		Optional<List<JournalEntry>> optionalJournalEntries = journalEntryRepository.findAllByCreatedBy(createBy);
		return journalEntryRepository.findAllByCreatedBy(createBy).orElseThrow(()
				-> new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION));

	}

	public JournalEntry getJournalEntryById(Integer id) {
		return journalEntryRepository.findById(id)
				.orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION));
	}


	public JournalEntry updateJournalEntry(Integer id, JournalEntryRequest req) {
		if (!journalEntryRepository.existsById(id)) {
			throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
		}
		JournalEntry res = journalEntryMapper.toJournalEntry(req);
		res.setEntryId(id);
		return journalEntryRepository.save(res);
	}

	public Optional<JournalEntry> getJournalEntryByUsername(String username) {
		return journalEntryRepository.findByCreatedByUsername(username);
	}

	public List<JournalEntry> getJournalEntriesByDate(LocalDate entryDate) {
		return journalEntryRepository.findByEntryDate(entryDate);
	}

	public List<JournalEntry> getJournalEntriesByStatus(Integer status) {
		return journalEntryRepository.findByStatus(status);
	}

	public List<JournalEntry> getJournalEntriesByDescription(String description) {
		return journalEntryRepository.findByDescriptionContaining(description);
	}

	public List<JournalEntry> getJournalEntriesByDateRange(LocalDate fromDate, LocalDate toDate) {
		return journalEntryRepository.findByEntryDateBetween(fromDate, toDate);
	}

	public List<JournalEntry> getJournalEntriesByCustomerAndDateRange(Customer customer, LocalDate startDate, LocalDate endDate) {
		return journalEntryRepository.findJournalEntriesByCustomerAndDateRange(customer, startDate, endDate);
	}

	public JournalEntry createJournalEntry(JournalEntryRequest journalEntry) {
		String createBy = SecurityContextHolder.getContext().getAuthentication().getName();
		JournalEntry entry = journalEntryMapper.toJournalEntry(journalEntry);
		if (!customerRepo.existsByUsername(createBy))
			throw new AppException(ErrorCode.USER_NOTFOUND);
		else
			entry.setCreatedBy(createBy);
		entry.setCreatedDate(LocalDateTime.now());
		return journalEntryRepository.save(entry);
	}


	public void deleteJournalEntry(Integer entryId) {
		if (!journalEntryRepository.existsById(entryId)) {
			throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
		}
		journalEntryRepository.deleteById(entryId);
	}

	public Optional<JournalEntry> getJournalEntryByCustomer(String customer) {
		return null;
	}
}


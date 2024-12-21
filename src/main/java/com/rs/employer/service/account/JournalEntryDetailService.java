package com.rs.employer.service.account;

import com.rs.employer.dao.account.ChartOfAccountRepository;
import com.rs.employer.dao.account.JournalEntryDetailRepository;
import com.rs.employer.dao.account.JournalEntryRepository;
import com.rs.employer.dao.customer.AccountRepository;
import com.rs.employer.dao.customer.CustomerRepo;
import com.rs.employer.dto.Request.JournalEntryDetailRequestDTO;
import com.rs.employer.dto.Response.BalanceMonthlyResponse;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.mapper.JournalEntryDetailMapper;
import com.rs.employer.model.account.JournalEntry;
import com.rs.employer.model.account.JournalEntryDetail;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryDetailService {
    private final JournalEntryDetailRepository journalEntryDetailRepository;
    private final JournalEntryDetailMapper journalEntryDetailMapper;
    private final JournalEntryRepository journalEntryRepository;
    private final CustomerRepo customerRepo;
    private final AccountRepository accountRepository;

    @Autowired
    public JournalEntryDetailService(JournalEntryDetailRepository journalEntryDetailRepository,
                                     JournalEntryDetailMapper journalEntryDetailMapper,
                                     ChartOfAccountRepository chartOfAccountRepository,
                                     JournalEntryRepository journalEntryRepository,
                                     CustomerRepo customerRepo, AccountRepository accountRepository) {
        this.journalEntryDetailRepository = journalEntryDetailRepository;
        this.journalEntryDetailMapper = journalEntryDetailMapper;
        this.journalEntryRepository = journalEntryRepository;
        this.customerRepo = customerRepo;
        this.accountRepository = accountRepository;
    }


    public List<JournalEntryDetail> getAllJournalEntryDetails() {
        return journalEntryDetailRepository.findAll();
    }


    public Optional<JournalEntryDetail> getJournalEntryDetailById(Integer id) {
        return journalEntryDetailRepository.findById(id);
    }

    @Transactional
    public Boolean createJournalEntryDetail(JournalEntryDetailRequestDTO journalEntryRequest) {
        JournalEntryDetail journalEntryDetail = journalEntryDetailMapper.map(journalEntryRequest);
        journalEntryDetail.setCreateDate(LocalDate.now());
        journalEntryDetail.setCreateBy(journalEntryRequest.getCreateBy());
        var account = accountRepository.findByAccountId(journalEntryRequest.getChartOfAccountCode());
        if (account == null) {
            throw new IllegalArgumentException("Account không tồn tại!");
        }
        journalEntryDetail.setAccount(account);
        JournalEntry journal = journalEntryRepository.findByEntryDate(journalEntryDetail.getCreateDate())
                .orElseGet(() -> {
                    JournalEntry newJournalEntry = new JournalEntry(
                            Integer.MAX_VALUE,
                            journalEntryRequest.getJournalEntryDate(),
                            null,
                            0,
                            customerRepo.findByUsername(journalEntryRequest.getCreateBy()).get(),
                            LocalDateTime.now(),
                            List.of(journalEntryDetail)
                    );
                    return journalEntryRepository.save(newJournalEntry);
                });
        journalEntryDetail.setJournalEntry(journal);
        journalEntryDetailRepository.save(journalEntryDetail);
        return true;
    }


    public JournalEntryDetail updateJournalEntryDetail(Integer id, JournalEntryDetailRequestDTO journalEntryRequest) {
        String authentication = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!journalEntryDetailRepository.existsById(id) || customerRepo.existsByUsername(authentication)) {
            throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
        }
        JournalEntryDetail journalEntryDetail = journalEntryDetailMapper.map(journalEntryRequest);
        journalEntryDetail.setEntryDetailId(id);
        return journalEntryDetailRepository.save(journalEntryDetail);
    }


    public void deleteJournalEntryDetail(Integer id) {
        if (journalEntryRepository.existsById(id))
            journalEntryDetailRepository.deleteById(id);
        else throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
    }

    public BalanceMonthlyResponse budgeRespone(int year) {
        BalanceMonthlyResponse response = new BalanceMonthlyResponse();
        for (int month = 1; month <= 12; month++) {
            Month currentMonth = Month.of(month);
            List<JournalEntryDetail> details = findEntriesByMonthAndYear(year, currentMonth);

            double totalCredit = 0;
            double totalDebit = 0;
            double balance = 0;

            // Tính tổng debit, credit và balance cho tháng
            for (JournalEntryDetail detail : details) {
                totalDebit += detail.getDebit().doubleValue();
                totalCredit += detail.getCredit().doubleValue();
            }

            balance = totalCredit - totalDebit;

            // Tạo dữ liệu cho tháng
            BalanceMonthlyResponse.MonthData monthData = new BalanceMonthlyResponse.MonthData(
                    currentMonth.name(), totalCredit, totalDebit, balance);

            // Cập nhật dữ liệu vào response
            response.getMonths()[month - 1] = monthData;
        }
        return response;
    }

    private List<JournalEntryDetail> findEntriesByMonthAndYear(int year, Month month) {
        LocalDate startOfMonth = LocalDate.of(year, month, 1);
        LocalDate endOfMonth = startOfMonth.with(TemporalAdjusters.lastDayOfMonth());

        return journalEntryDetailRepository.findAllByCreateDateBetween(startOfMonth, endOfMonth);
    }
}

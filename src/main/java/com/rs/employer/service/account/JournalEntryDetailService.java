package com.rs.employer.service.account;

import com.rs.employer.dao.account.ChartOfAccountRepository;
import com.rs.employer.dao.account.JournalEntryDetailRepository;
import com.rs.employer.dao.account.JournalEntryRepository;
import com.rs.employer.dao.customer.CustomerRepo;
import com.rs.employer.dto.Request.JournalEntryDetailRequestDTO;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.mapper.JournalEntryDetailMapper;
import com.rs.employer.model.account.JournalEntryDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryDetailService {
    private final JournalEntryDetailRepository journalEntryDetailRepository;
    private final JournalEntryDetailMapper journalEntryDetailMapper;
    private final ChartOfAccountRepository chartOfAccountRepository;
    private final JournalEntryRepository journalEntryRepository;
    private final CustomerRepo customerRepo;

    @Autowired
    public JournalEntryDetailService(JournalEntryDetailRepository journalEntryDetailRepository,
                                     JournalEntryDetailMapper journalEntryDetailMapper,
                                     ChartOfAccountRepository chartOfAccountRepository,
                                     JournalEntryRepository journalEntryRepository,
                                     CustomerRepo customerRepo) {
        this.journalEntryDetailRepository = journalEntryDetailRepository;
        this.journalEntryDetailMapper = journalEntryDetailMapper;
        this.chartOfAccountRepository = chartOfAccountRepository;
        this.journalEntryRepository = journalEntryRepository;
        this.customerRepo = customerRepo;
    }


    public List<JournalEntryDetail> getAllJournalEntryDetails() {
        return journalEntryDetailRepository.findAll();
    }


    public Optional<JournalEntryDetail> getJournalEntryDetailById(Integer id) {
        return journalEntryDetailRepository.findById(id);
    }


    public JournalEntryDetail createJournalEntryDetail(JournalEntryDetailRequestDTO journalEntryRequest) {
        String authenticator  = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("Người dùng là "  +authenticator);
        JournalEntryDetail journalEntryDetail = journalEntryDetailMapper.map(journalEntryRequest);
        if(!customerRepo.existsByUsername(authenticator))
            throw new AppException(ErrorCode.USER_NOTFOUND);
        journalEntryDetail.setCreateDate(LocalDate.now());
        journalEntryDetail.setCreateBy(authenticator);
        journalEntryDetail.setJournalEntry(journalEntryRepository.findById(journalEntryRequest.getJournalEntryId()).get());
        journalEntryDetail.setAccount(chartOfAccountRepository.findById(journalEntryRequest.getChartOfAccountCode()).get());
        return journalEntryDetailRepository.save(journalEntryDetail);
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
        if(journalEntryRepository.existsById(id))
        journalEntryDetailRepository.deleteById(id);
        else throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
    }
}

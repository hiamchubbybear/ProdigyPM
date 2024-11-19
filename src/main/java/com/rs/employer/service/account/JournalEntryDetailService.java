package com.rs.employer.service.account;

import com.rs.employer.dao.account.ChartOfAccountRepository;
import com.rs.employer.dao.account.JournalEntryDetailRepository;
import com.rs.employer.dao.account.JournalEntryRepository;
import com.rs.employer.dto.Request.JournalEntryDetailRequestDTO;
import com.rs.employer.mapper.JournalEntryDetailMapper;
import com.rs.employer.model.account.JournalEntryDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryDetailService {
    private final JournalEntryDetailRepository journalEntryDetailRepository;
    private final JournalEntryDetailMapper journalEntryDetailMapper;
    private final ChartOfAccountRepository chartOfAccountRepository;
    private final JournalEntryRepository journalEntryRepository;

    @Autowired
    public JournalEntryDetailService(JournalEntryDetailRepository journalEntryDetailRepository,
                                     JournalEntryDetailMapper journalEntryDetailMapper,
                                     ChartOfAccountRepository chartOfAccountRepository,
                                     JournalEntryRepository journalEntryRepository
    ) {
        this.journalEntryDetailRepository = journalEntryDetailRepository;
        this.journalEntryDetailMapper = journalEntryDetailMapper;
        this.chartOfAccountRepository = chartOfAccountRepository;
        this.journalEntryRepository = journalEntryRepository;
    }


    public List<JournalEntryDetail> getAllJournalEntryDetails() {
        return journalEntryDetailRepository.findAll();
    }


    public Optional<JournalEntryDetail> getJournalEntryDetailById(Integer id) {
        return journalEntryDetailRepository.findById(id);
    }


    public JournalEntryDetail createJournalEntryDetail(JournalEntryDetailRequestDTO journalEntryRequest) {
        JournalEntryDetail journalEntryDetail = journalEntryDetailMapper.map(journalEntryRequest);
        journalEntryDetail.setJournalEntry(journalEntryRepository.findById(journalEntryRequest.getJournalEntryId()).get());
        journalEntryDetail.setAccount(chartOfAccountRepository.findById(journalEntryRequest.getChartOfAccountCode()).get());
        return journalEntryDetailRepository.save(journalEntryDetail);
    }


    public JournalEntryDetail updateJournalEntryDetail(Integer id, JournalEntryDetailRequestDTO journalEntryRequest) {
        if (!journalEntryDetailRepository.existsById(id)) {
            return null;
        }
        JournalEntryDetail journalEntryDetail = journalEntryDetailMapper.map(journalEntryRequest);
        journalEntryDetail.setEntryDetailId(id);
        return journalEntryDetailRepository.save(journalEntryDetail);
    }


    public void deleteJournalEntryDetail(Integer id) {
        journalEntryDetailRepository.deleteById(id);
    }
}

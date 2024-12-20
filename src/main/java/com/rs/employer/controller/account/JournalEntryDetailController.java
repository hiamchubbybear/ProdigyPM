package com.rs.employer.controller.account;

import com.rs.employer.apirespone.ApiRespone;
import com.rs.employer.dto.Request.JournalEntryDetailRequestDTO;
import com.rs.employer.dto.Response.BalanceMonthlyResponse;
import com.rs.employer.model.account.JournalEntryDetail;
import com.rs.employer.serviceimplements.account.JournalEntryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/journal-entry-detail")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class JournalEntryDetailController {
    private final JournalEntryDetailService journalEntryDetailService;

    @Autowired
    public JournalEntryDetailController(JournalEntryDetailService journalEntryDetailService) {
        this.journalEntryDetailService = journalEntryDetailService;
    }

    @GetMapping
    public ApiRespone<List<JournalEntryDetail>> getAllJournalEntryDetails() {
        List<JournalEntryDetail> journalEntryDetails = journalEntryDetailService.getAllJournalEntryDetails();
        return new ApiRespone<>(journalEntryDetails);
    }

    @GetMapping("/{id}")
    public ApiRespone<JournalEntryDetail> getJournalEntryDetailById(@PathVariable Integer id) {
        Optional<JournalEntryDetail> journalEntryDetail = journalEntryDetailService.getJournalEntryDetailById(id);
        return journalEntryDetail.map(ApiRespone::new)
                .orElseGet(() -> new ApiRespone<>(null));
    }

    @PostMapping
    public ApiRespone<Boolean> createJournalEntryDetail(@RequestBody JournalEntryDetailRequestDTO journalEntryDetail) {
        return new ApiRespone<>(journalEntryDetailService.createJournalEntryDetail(journalEntryDetail));
    }

    @PutMapping("/{id}")
    public ApiRespone<JournalEntryDetail> updateJournalEntryDetail(@PathVariable Integer id, @RequestBody JournalEntryDetailRequestDTO journalEntryDetail) {
        JournalEntryDetail updatedJournalEntryDetail = journalEntryDetailService.updateJournalEntryDetail(id, journalEntryDetail);
        return updatedJournalEntryDetail != null ? new ApiRespone<>(updatedJournalEntryDetail)
                : new ApiRespone<>(null);
    }

    @DeleteMapping("/{id}")
    public ApiRespone<Void> deleteJournalEntryDetail(@PathVariable Integer id) {
        journalEntryDetailService.deleteJournalEntryDetail(id);
        return new ApiRespone<>(null);
    }

    @GetMapping("/monthly/{year}")
    public BalanceMonthlyResponse getBalanceMonthly(@PathVariable int year) {
        return journalEntryDetailService.budgeRespone(year);
    }
}

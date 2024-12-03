package com.rs.employer.controller.account;
import com.rs.employer.apirespone.ApiRespone;
import com.rs.employer.dao.account.JournalEntryRepository;
import com.rs.employer.dto.Request.JournalEntryRequest;

import com.rs.employer.model.account.JournalEntry;
import com.rs.employer.model.customer.Customer;
import com.rs.employer.serviceimplements.account.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping(value = "/api/journal-entries")
public class JournalEntryController {
    private final JournalEntryService journalEntryService;
    private final JournalEntryRepository journalEntryRepository;

    @Autowired
    public JournalEntryController(JournalEntryService journalEntryService, JournalEntryRepository journalEntryRepository) {
        this.journalEntryService = journalEntryService;
        this.journalEntryRepository = journalEntryRepository;
    }
    @GetMapping("/all")
    public ApiRespone<List<JournalEntry>> getAll () {
        return new ApiRespone<>(journalEntryRepository.findAll());
    }

    /**
     *
     * @param createBy
     * @param page
     * @param size
     * @exception : when user doesn't map with sub on bearer token
     * @apiNote 
     * @return : a page formated journal entry objects
     */
    @GetMapping("/createBy")
    public ApiRespone<Page<JournalEntry>> getAllJournalEntries(
            @RequestParam String createBy,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        System.out.println("createBy: " + createBy);
        Pageable pageable = PageRequest.of(page, size);
        Page<JournalEntry> journalEntries = journalEntryService.getAllJournalEntries(createBy, pageable);
        return new ApiRespone<>(journalEntries);
    }




    @GetMapping("/{entryId}")
    public ApiRespone<JournalEntry> getJournalEntryById(@PathVariable Integer entryId) {
        JournalEntry journalEntry = journalEntryService.getJournalEntryById(entryId);
        return new ApiRespone<>(journalEntry);
    }

    @PostMapping()
    public ApiRespone<JournalEntry> createJournalEntry(@RequestBody JournalEntryRequest journalEntry) {
        JournalEntry createdJournalEntry = journalEntryService.createJournalEntry(journalEntry);
        return new ApiRespone<>(createdJournalEntry);
    }

    @PutMapping()
    public ApiRespone<JournalEntry> updateJournalEntry(@RequestParam Integer entryId, @RequestBody JournalEntryRequest journalEntry) {
        JournalEntry updatedJournalEntry = journalEntryService.updateJournalEntry(entryId, journalEntry);
        return new ApiRespone<>(updatedJournalEntry);
    }

    @DeleteMapping()
    public ApiRespone<Void> deleteJournalEntry(@RequestParam Integer entryId) {
        journalEntryService.deleteJournalEntry(entryId);
        return new ApiRespone<>(null);
    }

    @GetMapping("/by-username/{username}")
    public ApiRespone<Optional<JournalEntry>> getJournalEntryByUsername(@RequestParam String username) {
        Optional<JournalEntry> journalEntry = journalEntryService.getJournalEntryByUsername(username);
        return new ApiRespone<>(journalEntry);
    }

    @GetMapping("/entryDate")
    public ApiRespone<JournalEntry> getJournalEntriesByDate(@RequestParam String entryDate) {
        JournalEntry journalEntries = journalEntryService.getJournalEntriesByDate(LocalDate.parse(entryDate));
        return new ApiRespone<>(journalEntries);
    }

    @GetMapping("/status")
    public ApiRespone<List<JournalEntry>> getJournalEntriesByStatus(@RequestParam Integer status) {
        List<JournalEntry> journalEntries = journalEntryService.getJournalEntriesByStatus(status);
        return new ApiRespone<>(journalEntries);
    }

    @GetMapping("/description")
    public ApiRespone<List<JournalEntry>> getJournalEntriesByDescription(@RequestParam String description) {
        List<JournalEntry> journalEntries = journalEntryService.getJournalEntriesByDescription(description);
        return new ApiRespone<>(journalEntries);
    }

    @GetMapping("/dateRange")
    public ApiRespone<List<JournalEntry>> getJournalEntriesByDateRange(@RequestParam LocalDate fromDate, @RequestParam LocalDate toDate) {
        List<JournalEntry> journalEntries = journalEntryService.getJournalEntriesByDateRange(fromDate, toDate);
        return new ApiRespone<>(journalEntries);
    }

    @GetMapping("/by-customer-and-date-range")
    public ApiRespone<List<JournalEntry>> getJournalEntriesByCustomerAndDateRange(
            @RequestBody Customer customer,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        List<JournalEntry> journalEntries = journalEntryService.getJournalEntriesByCustomerAndDateRange(customer, startDate, endDate);
        return new ApiRespone<>(journalEntries);
    }
    @GetMapping("/all-id")
    public ApiRespone<List<Integer>> getAllJournalEntries() {
        return new ApiRespone<>(journalEntryService.findAllJournalEntryIds());
    }
}

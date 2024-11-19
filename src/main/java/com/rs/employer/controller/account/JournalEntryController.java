package com.rs.employer.controller.account;

import com.rs.employer.apirespone.ApiRespone;
import com.rs.employer.dto.Request.JournalEntryRequest;
import com.rs.employer.model.account.JournalEntry;
import com.rs.employer.model.customer.Customer;
import com.rs.employer.serviceimplements.account.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping(value = "/api/journal-entries")
public class JournalEntryController {
    private final JournalEntryService journalEntryService;

    @Autowired
    public JournalEntryController(JournalEntryService journalEntryService) {
        this.journalEntryService = journalEntryService;
    }

    @GetMapping
    public ApiRespone<List<JournalEntry>> getAllJournalEntries() {
        List<JournalEntry> journalEntries = journalEntryService.getAllJournalEntries();
        return new ApiRespone<>(journalEntries);
    }

    @GetMapping("/{id}")
    public ApiRespone<JournalEntry> getJournalEntryById(@PathVariable Integer id) {
        JournalEntry journalEntry = journalEntryService.getJournalEntryById(id);
        return new ApiRespone<>(journalEntry);
    }

    @PostMapping
    public ApiRespone<JournalEntry> createJournalEntry(@RequestBody JournalEntryRequest journalEntry) {
        JournalEntry createdJournalEntry = journalEntryService.createJournalEntry(journalEntry);
        return new ApiRespone<>(createdJournalEntry);
    }

    @PutMapping("/{id}")
    public ApiRespone<JournalEntry> updateJournalEntry(@PathVariable Integer id, @RequestBody JournalEntryRequest journalEntry) {
        JournalEntry updatedJournalEntry = journalEntryService.updateJournalEntry(id, journalEntry);
        return new ApiRespone<>(updatedJournalEntry);
    }

    @DeleteMapping("/{id}")
    public ApiRespone<Void> deleteJournalEntry(@PathVariable Integer id) {
        journalEntryService.deleteJournalEntry(id);
        return new ApiRespone<>(null);
    }

    @GetMapping("/by-username/{username}")
    public ApiRespone<Optional<JournalEntry>> getJournalEntryByUsername(@PathVariable String username) {
        Optional<JournalEntry> journalEntry = journalEntryService.getJournalEntryByUsername(username);
        return new ApiRespone<>(journalEntry);
    }

    @GetMapping("/by-customer")
    public ApiRespone<Optional<JournalEntry>> getJournalEntryByCustomer(@RequestBody Customer customer) {
        Optional<JournalEntry> journalEntry = journalEntryService.getJournalEntryByCustomer(customer);
        return new ApiRespone<>(journalEntry);
    }

    @GetMapping("/by-date")
    public ApiRespone<List<JournalEntry>> getJournalEntriesByDate(@RequestParam LocalDate entryDate) {
        List<JournalEntry> journalEntries = journalEntryService.getJournalEntriesByDate(entryDate);
        return new ApiRespone<>(journalEntries);
    }

    @GetMapping("/by-status")
    public ApiRespone<List<JournalEntry>> getJournalEntriesByStatus(@RequestParam Integer status) {
        List<JournalEntry> journalEntries = journalEntryService.getJournalEntriesByStatus(status);
        return new ApiRespone<>(journalEntries);
    }

    @GetMapping("/by-description")
    public ApiRespone<List<JournalEntry>> getJournalEntriesByDescription(@RequestParam String description) {
        List<JournalEntry> journalEntries = journalEntryService.getJournalEntriesByDescription(description);
        return new ApiRespone<>(journalEntries);
    }

    @GetMapping("/by-date-range")
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
}

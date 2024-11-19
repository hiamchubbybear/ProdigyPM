package com.rs.employer.mapper;

import com.rs.employer.dto.Request.JournalEntryRequest;
import com.rs.employer.model.account.JournalEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JournalEntryMapper {
    @Mapping(ignore = true , target = "createdBy")
    JournalEntry toJournalEntry(JournalEntryRequest journalEntry);
}

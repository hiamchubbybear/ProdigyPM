package com.rs.employer.mapper;

import com.rs.employer.dto.Request.JournalEntryDetailRequestDTO;
import com.rs.employer.model.account.JournalEntryDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JournalEntryDetailMapper {
    @Mapping(target = "journalEntry" , ignore = true)
    @Mapping(target = "account" , ignore = true)
    JournalEntryDetail map(JournalEntryDetailRequestDTO journalEntryRequest);
}

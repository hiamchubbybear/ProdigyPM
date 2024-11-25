package com.rs.employer.applicationConfig;

import com.rs.employer.dao.customer.AccountRepository;
import com.rs.employer.enums.AccountEnum;
import com.rs.employer.enums.AccountingParrentEnum;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.model.account.ChartOfAccounts;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Configuration
public class AccountConfiguration {
    private final AccountRepository accountRepository;
    @Autowired
    public AccountConfiguration(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    private static final Map<Character, String> ACCOUNT_TYPE_MAP = Map.of(
            '1', "Current Assets",
            '2', "Non-Current Assets",
            '3', "Current Liabilities",
            '4', "Non-Current Liabilities",
            '5', "Owner's Equity",
            '6', "Revenue",
            '7', "Expenses"
    );

    @Transactional
    @Bean
    ApplicationRunner createAccount() {
        return args -> {
            for (AccountEnum accountEnum : AccountEnum.values()) {
                if (!accountRepository.existsById(accountEnum.getCode())) {
                    createAccountByType(accountEnum);
                }
            }
            createParentAccountRelationships();
        };
    }

    private void createAccountByType(AccountEnum accountEnum) {
        char accountType = accountEnum.getCode().charAt(0);
        String parentAccountId = ACCOUNT_TYPE_MAP.get(accountType);

        if (parentAccountId == null) return;

        var parentAccount = accountRepository.findByAccountId(parentAccountId);
        ChartOfAccounts childAccount = new ChartOfAccounts(
                accountEnum.getCode(),
                accountEnum.getDescription(),
                "C",
                parentAccount,
                null,
                BigDecimal.ZERO
        );

        parentAccount.setChildrenAccounts(Collections.singletonList(childAccount));

        accountRepository.save(childAccount);
        accountRepository.save(parentAccount);
    }

    private void createParentAccountRelationships() {
        for (AccountingParrentEnum accountEnum : AccountingParrentEnum.values()) {
            var parentAccount = accountRepository.findByAccountId(accountEnum.getDescription());

            if (parentAccount == null) {
                throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
            }

            List<String> ids = Collections.singletonList(accountEnum.getDescription());
            parentAccount.setChildrenAccounts(accountRepository.findAllById(ids));
            accountRepository.save(parentAccount);
        }
    }

    @Primary
    @Transactional
    @Bean
    ApplicationRunner createParrent() {
        return args -> {
            for (AccountingParrentEnum parrentEnum : AccountingParrentEnum.values()) {
                if (!accountRepository.existsById(parrentEnum.getDescription())) {
                    ChartOfAccounts chartOfAccounts = new ChartOfAccounts(
                            parrentEnum.getDescription(),
                            parrentEnum.getGroup(),
                            "P",
                            null,
                            null,
                            BigDecimal.ZERO
                    );
                    accountRepository.save(chartOfAccounts);
                }
            }
        };
    }
}
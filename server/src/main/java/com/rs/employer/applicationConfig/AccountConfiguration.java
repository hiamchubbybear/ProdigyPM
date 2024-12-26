package com.rs.employer.applicationConfig;

import com.rs.employer.dao.customer.AccountRepository;
import com.rs.employer.enums.AccountEnum;
import com.rs.employer.enums.AccountingParentEnum;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.model.account.ChartOfAccounts;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.data.repository.query.Param;

import java.beans.Transient;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Configuration
public class AccountConfiguration {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountConfiguration(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    @Bean
    ApplicationRunner initializeChartOfAccounts() {
        return args -> {
            for (AccountingParentEnum parentEnum : AccountingParentEnum.values()) {
                if (!accountRepository.existsById(parentEnum.getCode())) {
                    ChartOfAccounts parentAccount = new ChartOfAccounts(
                            parentEnum.getCode(),
                            parentEnum.getDescription(),
                            "P",
                            null,
                            null
                    );

                    accountRepository.save(parentAccount);
                    System.out.println("Đã lưu parent account"  + parentEnum.getCode());
                }
            }

            for (AccountEnum accountEnum : AccountEnum.values()) {
                if (!accountRepository.existsById(accountEnum.getCode())) {
                    ChartOfAccounts parentAccount = accountRepository.findByAccountId(accountEnum.getParentCode());
                    if (parentAccount == null) {
                        System.out.println("Không tồn tại tài khoản " + accountEnum.getParentCode());
                        throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
                    }
                    ChartOfAccounts childAccount = new ChartOfAccounts(
                            accountEnum.getCode(),
                            accountEnum.getDescription(),
                            "C",
                            parentAccount,
                            null
                    );
                    accountRepository.save(childAccount);
                }
            }
        };
    }
}

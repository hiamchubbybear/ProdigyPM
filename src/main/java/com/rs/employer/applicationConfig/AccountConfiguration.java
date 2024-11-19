package com.rs.employer.applicationConfig;

import com.rs.employer.dao.customer.AccountRepository;
import com.rs.employer.enums.AccountEnum;
import com.rs.employer.enums.AccountingParrentEnum;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.model.account.ChartOfAccounts;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Configuration
public class AccountConfiguration {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountConfiguration(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @Transactional
    @Bean
    @Qualifier
    ApplicationRunner createAccount() {
        return args -> {
            for (AccountEnum accountEnum : AccountEnum.values()) {
                if (!accountRepository.existsById(accountEnum.getCode())) {
                    switch (accountEnum.getCode().charAt(0)) {
                        case '1':
                            var crAccount = accountRepository.findByAccountId("Current Assets");
                            var cbAccount = new ChartOfAccounts(accountEnum.getCode(),
                                    accountEnum.getDescription(), "C"
                                    , crAccount, null, BigDecimal.ZERO);
                            crAccount.setChildrenAccounts(List.of(cbAccount));
                            accountRepository.save(cbAccount);
                            accountRepository.save(crAccount);
                            break;
                        case '2':
                            var ncaAccount = accountRepository.findByAccountId("Non-Current Assets");
                            ChartOfAccounts chartOfAccounts = new ChartOfAccounts(accountEnum.getCode(),
                                    accountEnum.getDescription(), "C"
                                    , ncaAccount, null, BigDecimal.ZERO);
                            ncaAccount.setChildrenAccounts(List.of(chartOfAccounts));
                            accountRepository.save(chartOfAccounts);
                            accountRepository.save(ncaAccount);
                            break;
                        case '3':
                            var clAccount = accountRepository.findByAccountId("Current Liabilities");
                            ChartOfAccounts c3 = new ChartOfAccounts(accountEnum.getCode(),
                                    accountEnum.getDescription(), "C"
                                    , clAccount, null, BigDecimal.ZERO);
                            clAccount.setChildrenAccounts(List.of(c3));
                            accountRepository.save(c3);
                            accountRepository.save(clAccount);
                            break;
                        case '4':
                            var nclAccount = accountRepository.findByAccountId("Non-Current Liabilities");
                            ChartOfAccounts c4 = new ChartOfAccounts(accountEnum.getCode(),
                                    accountEnum.getDescription(), "C"
                                    , nclAccount, null, BigDecimal.ZERO);
                            nclAccount.setChildrenAccounts(List.of(c4));

                            accountRepository.save(c4);
                            accountRepository.save(nclAccount);
                            break;
                        case '5':
                            var oeAccount = accountRepository.findByAccountId("Owner's Equity");
                            ChartOfAccounts c5 = new ChartOfAccounts(accountEnum.getCode(),
                                    accountEnum.getDescription(), "C"
                                    , oeAccount, null, BigDecimal.ZERO);
                            oeAccount.setChildrenAccounts(List.of(c5));
                            accountRepository.save(c5);
                            accountRepository.save(oeAccount);
                            break;
                        case '6':
                            var rAccount = accountRepository.findByAccountId("Revenue");
                            ChartOfAccounts c6 = new ChartOfAccounts(accountEnum.getCode(),
                                    accountEnum.getDescription(), "C"
                                    , rAccount, null, BigDecimal.ZERO);
                            rAccount.setChildrenAccounts(List.of(c6));
                            accountRepository.save(c6);
                            accountRepository.save(rAccount);
                            break;
                        case '7':
                            var eAccount = accountRepository.findByAccountId("Expenses");
                            ChartOfAccounts c7 = new ChartOfAccounts(accountEnum.getCode(),
                                    accountEnum.getDescription(), "C"
                                    , eAccount, null, BigDecimal.ZERO);
                            eAccount.setChildrenAccounts(List.of(c7));
                            accountRepository.save(c7);
                            accountRepository.save(eAccount);
                            break;
                    }
                }
            }
            for (AccountingParrentEnum accountEnum : AccountingParrentEnum.values()) {
                var parentAccount = accountRepository.findByAccountId(accountEnum.getDescription());
                if (accountRepository.existsById(accountEnum.getDescription()) && parentAccount != null) {
                    List<String> ids = Collections.singletonList(accountEnum.getDescription());
                    parentAccount.setChildrenAccounts(accountRepository.findAllById(ids));
                    accountRepository.save(parentAccount);

                } else {
                    throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
                }
            }
        };
    }

    @Primary
    @Transactional
    @Bean
    ApplicationRunner createParrent() {
        return args -> {
            for (AccountingParrentEnum parrentEnum : AccountingParrentEnum.values()) {
                if (!accountRepository.existsById(parrentEnum.getDescription())) {
                ChartOfAccounts chartOfAccounts = new ChartOfAccounts(parrentEnum.getDescription()
                            , parrentEnum.getGroup(), "P", null
                            , null, BigDecimal.ZERO
                    );
                    accountRepository.save(chartOfAccounts);
                    System.out.println("Đã lưu "  +chartOfAccounts.getAccountId());
                }
            }
        };
    }
}

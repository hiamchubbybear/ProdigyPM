package com.rs.employer.applicationConfig;

import com.rs.employer.dao.AccountRepository;
import com.rs.employer.enums.AccountEnum;
import com.rs.employer.enums.AccountingParrentEnum;
import com.rs.employer.model.account.ChartOfAccounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AccountConfiguration {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountConfiguration(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

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
                                    accountEnum.getDescription(), "Child"
                                    , crAccount, null, null);
                            accountRepository.save(cbAccount);
                            break;
                        case '2':
                            var ncaAccount = accountRepository.findByAccountId("Non-Current Assets");
                            ChartOfAccounts chartOfAccounts = new ChartOfAccounts(accountEnum.getCode(),
                                    accountEnum.getDescription(), "Child"
                                    , ncaAccount, null, null);
                            accountRepository.save(chartOfAccounts);
                            break;
                        case '3':
                            var clAccount = accountRepository.findByAccountId("Current Liabilities");
                            ChartOfAccounts c3 = new ChartOfAccounts(accountEnum.getCode(),
                                    accountEnum.getDescription(), "Child"
                                    , clAccount, null, null);
                            accountRepository.save(c3);
                            break;
                        case '4':
                            var nclAccount = accountRepository.findByAccountId("Current Liabilities");
                            ChartOfAccounts c4 = new ChartOfAccounts(accountEnum.getCode(),
                                    accountEnum.getDescription(), "Child"
                                    , nclAccount, null, null);
                            accountRepository.save(c4);
                            break;
                        case '5':
                            var oeAccount = accountRepository.findByAccountId("Current Liabilities");
                            ChartOfAccounts c5 = new ChartOfAccounts(accountEnum.getCode(),
                                    accountEnum.getDescription(), "Child"
                                    , oeAccount, null, null);
                            accountRepository.save(c5);
                            break;
                        case '6':
                            var rAccount = accountRepository.findByAccountId("Current Liabilities");
                            ChartOfAccounts c6 = new ChartOfAccounts(accountEnum.getCode(),
                                    accountEnum.getDescription(), "Child"
                                    , rAccount, null, null);
                            accountRepository.save(c6);
                            break;
                        case '7':
                            var eAccount = accountRepository.findByAccountId("Current Liabilities");
                            ChartOfAccounts c7 = new ChartOfAccounts(accountEnum.getCode(),
                                    accountEnum.getDescription(), "Child"
                                    , eAccount, null, null);
                            accountRepository.save(c7);
                            break;
                    }

                }
            }
        };
    }

    @Primary
    @Bean
    ApplicationRunner createParrent() {
        return args -> {
            for (AccountingParrentEnum parrentEnum : AccountingParrentEnum.values()) {
                if (!accountRepository.existsById(parrentEnum.getDescription())) {
                    ChartOfAccounts chartOfAccounts = new ChartOfAccounts(parrentEnum.getDescription()
                            , parrentEnum.getGroup(), "Parent", null
                            , null, null
                    );
                    accountRepository.save(chartOfAccounts);
                }
            }
        };
    }
}

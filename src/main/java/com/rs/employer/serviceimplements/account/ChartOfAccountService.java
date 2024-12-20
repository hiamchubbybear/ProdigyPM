package com.rs.employer.serviceimplements.account;

import com.rs.employer.dao.account.ChartOfAccountRepository;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.model.account.ChartOfAccounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChartOfAccountService {
    @Autowired
    private ChartOfAccountRepository repository;

    public Optional<ChartOfAccounts> getChartOfAccountById(String id) {
        var chartAccount = repository.findById(id);
        chartAccount.ifPresentOrElse(
                value -> {
                },
                () -> {
                    throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
                }
        );
        return chartAccount;
    }

    public List<ChartOfAccounts> getAllChartOfAccounts() {
        return repository.findAll();
    }

    public ChartOfAccounts addChartOfAccount(ChartOfAccounts chartOfAccount) {
        return repository.save(chartOfAccount);
    }

    public ChartOfAccounts updateChartOfAccount(String id, ChartOfAccounts chartOfAccount) {
        if (repository.existsById(id)) {
            chartOfAccount.setAccountId(id);
            return repository.save(chartOfAccount);
        }
        return null;
    }

    public boolean deleteChartOfAccount(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}


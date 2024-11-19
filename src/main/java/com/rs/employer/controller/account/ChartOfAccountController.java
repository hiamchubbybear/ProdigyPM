package com.rs.employer.controller.account;

import com.rs.employer.apirespone.ApiRespone;
import com.rs.employer.model.account.ChartOfAccounts;
import com.rs.employer.service.account.ChartOfAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/account")
public class ChartOfAccountController {
    @Autowired
    private ChartOfAccountService chartOfAccountService;
    @GetMapping("/all")
    public ApiRespone<List<ChartOfAccounts>> all() {
            return new ApiRespone<>(chartOfAccountService.getAllChartOfAccounts());
    }
}

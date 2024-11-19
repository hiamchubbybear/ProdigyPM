package com.rs.employer.controller.account;

import com.rs.employer.apirespone.ApiRespone;
import com.rs.employer.model.account.ChartOfAccounts;
import com.rs.employer.service.account.ChartOfAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping(value = "/api/account")
public class ChartOfAccountController {

    private ChartOfAccountService chartOfAccountService;
    @Autowired
    public ChartOfAccountController(ChartOfAccountService chartOfAccountService) {
        this.chartOfAccountService = chartOfAccountService;
    }

    @GetMapping("/all")
    public ApiRespone<List<ChartOfAccounts>> getAll() {
            return new ApiRespone<>(chartOfAccountService.getAllChartOfAccounts());
    }
}

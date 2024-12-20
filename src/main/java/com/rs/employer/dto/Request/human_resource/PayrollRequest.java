package com.rs.employer.dto.Request.human_resource;

import com.rs.employer.model.customer.Customer;
import com.rs.employer.model.human_resource.Employee;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.util.Date;

public class PayrollRequest {
    private String employeeName;
    private String period;
    private BigDecimal basicSalary;
    private BigDecimal allowance;
    private BigDecimal deductions;
    private BigDecimal netsalary;
    private String status;
    private String customerName;
    private Date date;

    public PayrollRequest() {
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(BigDecimal basicSalary) {
        this.basicSalary = basicSalary;
    }

    public BigDecimal getAllowance() {
        return allowance;
    }

    public void setAllowance(BigDecimal allowance) {
        this.allowance = allowance;
    }

    public BigDecimal getDeductions() {
        return deductions;
    }

    public void setDeductions(BigDecimal deductions) {
        this.deductions = deductions;
    }

    public BigDecimal getNetsalary() {
        return netsalary;
    }

    public void setNetsalary(BigDecimal netsalary) {
        this.netsalary = netsalary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public PayrollRequest(String employeeName, String period, BigDecimal basicSalary, BigDecimal allowance, BigDecimal deductions, BigDecimal netsalary, String status, String customerName, Date date) {
        this.employeeName = employeeName;
        this.period = period;
        this.basicSalary = basicSalary;
        this.allowance = allowance;
        this.deductions = deductions;
        this.netsalary = netsalary;
        this.status = status;
        this.customerName = customerName;
        this.date = date;
    }
}

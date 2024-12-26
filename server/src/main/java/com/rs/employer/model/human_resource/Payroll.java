package com.rs.employer.model.human_resource;

import com.rs.employer.model.customer.Customer;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Payroll {
    @Id
    @GeneratedValue
    private Long payrollId;
    @ManyToOne
    @JoinColumn(name = "employee_payroll")
    private Employee employee;
    private String period;
    private BigDecimal basicSalary;
    private BigDecimal allowance;
    private BigDecimal deductions;
    private BigDecimal netsalary;
    private String status;
    @ManyToOne
    @JoinColumn(name = "manage")
    private Customer customer;
    private Date createDate;

    public Payroll() {
    }

    public Long getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(Long payrollId) {
        this.payrollId = payrollId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Payroll(Long payrollId, Employee employee, String period, BigDecimal basicSalary, BigDecimal allowance, BigDecimal deductions, BigDecimal netsalary, String status, Customer customer, Date createDate) {
        this.payrollId = payrollId;
        this.employee = employee;
        this.period = period;
        this.basicSalary = basicSalary;
        this.allowance = allowance;
        this.deductions = deductions;
        this.netsalary = netsalary;
        this.status = status;
        this.customer = customer;
        this.createDate = createDate;
    }
}


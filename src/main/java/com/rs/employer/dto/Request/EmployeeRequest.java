package com.rs.employer.dto.Request;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;
//    private Department department;
//    private Set<Payroll> payrolls;
public class EmployeeRequest {
    private Integer employeeId;
    private String employeeCode;
    private String employeeName;
    private Date dob;
    private Integer gender;
    private String address;
    private String phoneNumber;
    private String email;
    String departmentName;
    private String position;
    private Date startDate;
    private Integer status;
    private Set<String> payrolls;

    public EmployeeRequest() {
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<String> getPayrolls() {
        return payrolls;
    }

    public void setPayrolls(Set<String> payrolls) {
        this.payrolls = payrolls;
    }

    public EmployeeRequest(Integer employeeId, String employeeCode, String employeeName, Date dob, Integer gender, String address, String phoneNumber, String email, String departmentName, String position, Date startDate, Integer status, Set<String> payrolls) {
        this.employeeId = employeeId;
        this.employeeCode = employeeCode;
        this.employeeName = employeeName;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.departmentName = departmentName;
        this.position = position;
        this.startDate = startDate;
        this.status = status;
        this.payrolls = payrolls;
    }
}


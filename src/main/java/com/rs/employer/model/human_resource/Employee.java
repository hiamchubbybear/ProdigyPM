package com.rs.employer.model.human_resource;

import com.rs.employer.model.others.Department;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;
    private String employeeCode;
    private String employeeName;
    private Date dob;
    private Integer gender;
    private String address;
    private String phoneNumber;
    private String email;
    @ManyToOne
    @JoinColumn(name = "employee_department")
    private Department department;
    private String position;
    private Date startDate;
    private Integer status;
    @OneToMany(mappedBy = "employee")
    private Set<Payroll> payrolls;

    public Employee() {

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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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

    public Set<Payroll> getPayrolls() {
        return payrolls;
    }

    public void setPayrolls(Set<Payroll> payrolls) {
        this.payrolls = payrolls;
    }

    public Employee(Integer employeeId, String employeeCode, String employeeName, Date dob, Integer gender, String address, String phoneNumber, String email, Department department, String position, Date startDate, Integer status, Set<Payroll> payrolls) {
        this.employeeId = employeeId;
        this.employeeCode = employeeCode;
        this.employeeName = employeeName;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.department = department;
        this.position = position;
        this.startDate = startDate;
        this.status = status;
        this.payrolls = payrolls;
    }
}

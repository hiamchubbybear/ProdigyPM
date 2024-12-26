package com.rs.employer.model.others;

import com.rs.employer.model.human_resource.Employee;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departmentId;
    private String departmentCode;
    private String departmentName;
    @OneToMany(mappedBy = "parentDepartment")
    private Set<Department> subDepartment;
    @ManyToOne
    @JoinColumn(name = "department")
    private Department parentDepartment;
    private Integer status;
    @OneToMany(mappedBy = "department")
    private Set<Employee> employees;

    public Department() {
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Set<Department> getSubDepartment() {
        return subDepartment;
    }

    public void setSubDepartment(Set<Department> subDepartment) {
        this.subDepartment = subDepartment;
    }

    public Department getParentDepartment() {
        return parentDepartment;
    }

    public void setParentDepartment(Department parentDepartment) {
        this.parentDepartment = parentDepartment;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Department(Long departmentId, String departmentCode, String departmentName, Set<Department> subDepartment, Department parentDepartment, Integer status, Set<Employee> employees) {
        this.departmentId = departmentId;
        this.departmentCode = departmentCode;
        this.departmentName = departmentName;
        this.subDepartment = subDepartment;
        this.parentDepartment = parentDepartment;
        this.status = status;
        this.employees = employees;
    }
}

package com.rs.employer.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.rs.employer.model.Employee;
@Service
public interface EmployeeService {
  public ArrayList<Employee>  getAllEmployee();
    public Employee addEmployee(Employee eployee);

    public Employee updateEmployee(Long id, Employee eployee);

    public boolean deleteEmployee(Long id);

    public Employee getEmployee(Long id);

    public Employee registerUser(String username, String password, String login);
}

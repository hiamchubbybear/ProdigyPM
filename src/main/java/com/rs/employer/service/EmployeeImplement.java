package com.rs.employer.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.employer.model.Employee;
import com.rs.employer.repository.EmployeeRepository;


@Service
public class EmployeeImplement implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public Employee addEmployee(Employee employee) {
        if (employee != null) {
            return employeeRepository.save(employee);
        }
        throw new IllegalStateException(
                " Can not add employee with username: " + employee.getUsername() + " cause username is existed ");
    }

    @Override
    public Employee updateEmployee(Long UserID, Employee employee) {
        Employee employee1 = employeeRepository.getReferenceById(UserID);
        if (employee1 != null) {
            employee1.setId(UserID);
            employee1.setName(employee.getName());
            employee1.setAddress(employee.getAddress());
            employee1.setUsername(employee.getUsername());
            employee1.setPassword(employee.getPassword());
            employee1.setRole(employee.getRole());
            employee1.setStatus(employee.getStatus());
            employee1.setBirthDay(employee.getBirthDay());
            return employeeRepository.save(employee1);
        }
        throw new IllegalStateException("User data can not found");
    }

    @Override
    public boolean deleteEmployee(Long UserID) {
        if (employeeRepository.existsById(UserID)) {
            Optional<Employee> eOptional = employeeRepository.findById(UserID);
            Employee employee1 = eOptional.get();
            if (employee1.getId() != null) {
                employeeRepository.deleteById(UserID);
                return true;
            }
        }
        return false;
    }

    @Override
    public Employee getEmployee(Long UserID) {
        Optional<Employee> eOptional = employeeRepository.findById(UserID);
        Employee employee1 = eOptional.get();
        if (employee1.getId() != null) {
            return employee1;
        } else
            throw new IllegalStateException("User have id:  " + UserID + " does not exist ");
    }

    public Employee registerUser(String username, String password, String login) {
        if (username != null && password != null && login != null) {
            Employee employee = new Employee();
            employee.setUsername(username);
            employee.setPassword(password);
            return employeeRepository.save(employee);
        } else
            return null;
    }

    @Override
    public ArrayList<Employee> getAllEmployee() {
      return (ArrayList<Employee>)  employeeRepository.findAll();
    }

}

package com.rs.employer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rs.employer.model.Employee;
import com.rs.employer.service.EmployeeService;

@RequestMapping(path = "/v1")
@RestController
public class controller {
    @Autowired
    private EmployeeService employeeService; 
    @GetMapping(path="/getallus")
   public List<Employee> getAllUser() {
         List<Employee> list = employeeService.getAllEmployee();
    return list;

   }
    @GetMapping(path = "/get")
    public Employee getPaticipateUser(@RequestParam(name = "userid") Long UserID) {
        return employeeService.getEmployee(UserID);
    }

    @DeleteMapping(path = "/delete")
    public boolean deleteEmployee(@RequestParam(name = "userid") Long UserID) {
        return employeeService.deleteEmployee(UserID);
    }

    @GetMapping(path = "/update")
    public Employee updatEmployee(@RequestParam(name = "userid") Long UserID,
            @RequestBody Employee employee) {
        return employeeService.updateEmployee(UserID, employee);
    }

    @GetMapping(path = "/add")
    public void addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
    }

    @GetMapping(path = "/register")
    public Employee registerEmployee(String username, String password, String login) {
        return employeeService.registerUser(username, password, login);
    }
    @GetMapping(path= "/hello") 
    public String hello() {
          return "HelloWorld";
    }  
}

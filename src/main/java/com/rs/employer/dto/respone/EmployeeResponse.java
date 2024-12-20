package com.rs.employer.dto.respone;

import java.util.Date;
import java.util.Set;

public class EmployeeResponse {
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
}

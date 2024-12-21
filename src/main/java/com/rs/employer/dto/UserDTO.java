package com.rs.employer.dto;

import lombok.Data;

@Data
public class UserDTO {

    private int id;
    private String usercode;
    private String address;
    private String email;
    private String phone;
    private String taxcode;
    private byte status;

}
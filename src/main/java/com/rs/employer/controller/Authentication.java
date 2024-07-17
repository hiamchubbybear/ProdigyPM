package com.rs.employer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rs.employer.apirespone.ApiRespone;
import com.rs.employer.dto.AuthenticationDto;
import com.rs.employer.dto.AuthenticationRespone.AuthenticationRespone;
import com.rs.employer.serviceimplements.AuthenticationServiceImp;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Authentication {
    @Autowired
    private AuthenticationServiceImp authen;

    @PostMapping("/login")
    public ApiRespone<AuthenticationRespone> postMethodName(@RequestBody AuthenticationDto authenticated) {
        boolean result = authen.authenticate(authenticated);

        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setData(result);
        return apiRespone;
        // return ApiRespone.<AuthenticationDto>builder().setData(result).build();
    }

}

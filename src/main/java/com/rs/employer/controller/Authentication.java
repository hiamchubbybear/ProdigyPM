package com.rs.employer.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.JOSEException;
import com.rs.employer.apirespone.ApiRespone;
import com.rs.employer.dto.AuthenticationRespone.IntrospectRequest;
import com.rs.employer.dto.IntrospectResponse;
import com.rs.employer.serviceimplements.AuthenticationServiceImp;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Authentication {
//    @Autowired
//    private AuthenticationServiceImp authen;

    // @PostMapping("/login")
    // public ApiRespone<AuthenticationRespone> postMethodName(@RequestBody
    // AuthenticationDto authenticated) {
    // boolean result = authen.authenticate(authenticated);
    // AuthenticationRespone respone = new AuthenticationRespone();
    // ApiRespone apiRespone = new ApiRespone<>();
    // respone.setAuthenticated(result);
    // apiRespone.setData(respone);
    // return apiRespone;
    // // return ApiRespone.<AuthenticationDto>builder().setData(result).build();
    // }
//    @PostMapping("/introspect")
//    ApiRespone<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request)
//            throws ParseException {
        // var result = AuthenticationServiceImp.introspect(request);
        // return ApiResponse.<IntrospectResponse>builder()
        // .result(result)
        // .build();
//        return null;
//    }
}

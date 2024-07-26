package com.rs.employer.controller;

import java.text.ParseException;

import com.rs.employer.dto.AuthenticationDto;
import com.rs.employer.dto.AuthenticationRespone.AuthenticationRespone;
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
    @Autowired
    private AuthenticationServiceImp authen;

     @PostMapping("/login")
     public ApiRespone<AuthenticationRespone> postMethodName(@RequestBody
        AuthenticationDto authenticated) {
     var result = authen.authentication(authenticated);
     ApiRespone apiRespone = new ApiRespone<>();
     apiRespone.setData(result);
     return apiRespone;
//      return ApiRespone.<AuthenticationDto>builder().setData(result).build();
     }
     @PostMapping("/token")
    public ApiRespone<IntrospectRequest> introspect (@RequestBody IntrospectRequest authenticated)
             throws ParseException, JOSEException {
             var result = authen.introspectRequest(authenticated);
             ApiRespone apiRespone = new ApiRespone<>();
             apiRespone.setData(result);
             return apiRespone;
     }

}

package com.rs.employer.controller.auth;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.JOSEException;
import com.rs.employer.apirespone.ApiRespone;
import com.rs.employer.dto.Request.AuthenticationRequest;
import com.rs.employer.dto.Request.IntrospectRequest;
import com.rs.employer.dto.Request.LogoutRequest;
import com.rs.employer.dto.Respone.AuthenticationRespone;
import com.rs.employer.serviceimplements.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class Authentication {
     @Autowired
     private AuthenticationService authen;

     @PostMapping("/login")
     public ApiRespone<AuthenticationRespone> postMethodName(@RequestBody AuthenticationRequest authenticated) {
          var result = authen.authentication(authenticated);
          ApiRespone apiRespone = new ApiRespone<>();
          apiRespone.setData(result);
          return apiRespone;
          // return ApiRespone.<AuthenticationDto>builder().setData(result).build();
     }

     @PostMapping("/token")
     public ApiRespone<IntrospectRequest> introspect(@RequestBody IntrospectRequest authenticated)
               throws ParseException, JOSEException {
          var result = authen.introspectRequest(authenticated);
          ApiRespone apiRespone = new ApiRespone<>();
          apiRespone.setData(result);
          return apiRespone;
     }

     @PostMapping("/logout")
     public ApiRespone<Void> logout(@RequestBody LogoutRequest request) throws JOSEException, ParseException {
          authen.Logout(request);
          ApiRespone apiRespone = new ApiRespone<>();
          apiRespone.setCode(1000);
          return apiRespone;
     }
}

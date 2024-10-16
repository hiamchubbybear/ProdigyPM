package com.rs.employer.controller.auth;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nimbusds.jose.JOSEException;
import com.rs.employer.apirespone.ApiRespone;
import com.rs.employer.dto.Request.Auth.AuthenticationRequest;
import com.rs.employer.dto.Request.Auth.IntrospectRequest;
import com.rs.employer.dto.Request.Auth.LogoutRequest;
import com.rs.employer.dto.Respone.AuthenticationRespone;
import com.rs.employer.serviceimplements.AuthenticationService;
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
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

package com.rs.employer.controller.auth;

import java.text.ParseException;

import com.rs.employer.dao.CustomerRepo;
import com.rs.employer.dto.Request.ActivateRequestAccount;
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
public class AuthenticationController {
     @Autowired
     private AuthenticationService authen;
     @Autowired
     private CustomerRepo customerRepo;
     @PostMapping("/login")
     public ApiRespone<?> postMethodName(@RequestBody AuthenticationRequest authenticated) throws JOSEException {
          var  username = authenticated.getUsername();
          var  password = authenticated.getPassword();
          if(customerRepo.existsByUsernameAndEmail(username, password) && (customerRepo.findStatusByUsernameAndEmail(username,password) ==true)) {
               return new ApiRespone<>(authen.authentication(authenticated));
          } else {
               return new ApiRespone<>(authen.EmailVerification(authenticated));
          }
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

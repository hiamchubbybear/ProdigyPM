package com.rs.employer.controller.auth;

import java.text.ParseException;

import com.rs.employer.dao.customer.CustomerRepo;
import com.rs.employer.dto.Respone.LoginInactiveAccountRespone;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nimbusds.jose.JOSEException;
import com.rs.employer.apirespone.ApiRespone;
import com.rs.employer.dto.Request.Auth.AuthenticationRequest;
import com.rs.employer.dto.Request.Auth.IntrospectRequest;
import com.rs.employer.dto.Request.Auth.LogoutRequest;
import com.rs.employer.serviceimplements.customer.AuthenticationService;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authen;
    private final CustomerRepo customerRepo;

    @Autowired
    public AuthenticationController(AuthenticationService authen, CustomerRepo customerRepo) {
        this.authen = authen;
        this.customerRepo = customerRepo;
    }

    @PostMapping("/login")
    public ApiRespone<?> postMethodName(@RequestBody AuthenticationRequest authenticated) throws JOSEException {
        var username = authenticated.getUsername();
        var status = customerRepo.findStatusByUsernameAndEmail(username);
        var email = customerRepo.findEmailByUsername(username);
        if (customerRepo.existsByUsernameAndEmail(username, email) || username.equals("admin")) {
            if (status) {
                return new ApiRespone<>(authen.authentication(authenticated));
            } else {
                String token = authen.EmailVerification(authenticated);
                ApiRespone apiRespone = new ApiRespone();
                apiRespone.setCode(101);
                apiRespone.setData(new LoginInactiveAccountRespone(token, true));
                return apiRespone;
            }
        }throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
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

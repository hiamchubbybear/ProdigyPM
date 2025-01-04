package com.rs.employer.controller.auth;

import java.text.ParseException;

import com.rs.employer.dao.customer.CustomerRepository;
import com.rs.employer.dao.customer.TokenService;
import com.rs.employer.dto.Response.IntrospectRespone;
import com.rs.employer.dto.Response.LoginInactiveAccountRespone;
import com.rs.employer.service.EmailService;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nimbusds.jose.JOSEException;
import com.rs.employer.apirespone.ApiRespone;
import com.rs.employer.dto.Request.Auth.AuthenticationRequest;
import com.rs.employer.dto.Request.Auth.IntrospectRequest;
import com.rs.employer.dto.Request.Auth.LogoutRequest;
import com.rs.employer.service.customer.customer.AuthenticationService;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authen;
    private final CustomerRepository customerRepo;
    private final TokenService tokenService;
    private final AuthenticationService authenticationService;
    private final EmailService emailService;

    @Autowired
    public AuthenticationController(AuthenticationService authen, CustomerRepository customerRepo, TokenService tokenService, AuthenticationService authenticationService, EmailService emailService) {
        this.authen = authen;
        this.customerRepo = customerRepo;
        this.tokenService = tokenService;
        this.authenticationService = authenticationService;
        this.emailService = emailService;
    }

    @PostMapping("/login")
    public ApiRespone<?> postMethodName(@RequestBody AuthenticationRequest authenticated) throws JOSEException {
        var username = authenticated.getUsername();
        var status = customerRepo.findStatusByUsernameAndEmail(username);
        var email = customerRepo.findEmailByUsername(username);
        System.out.println(status );
        System.out.println(email);
        if (customerRepo.existsByUsernameAndEmail(username, email)) {
            if (status) {
                System.out.println("Xong");
                return new ApiRespone<>(authen.authentication(authenticated));
            } else {

                String token = (authenticationService.authentication(authenticated)).getToken();
                emailService.sendActivateToken(email,"Activate token "  , tokenService.checkTokenAndRegenerateToken(email));
                return new ApiRespone<>(new LoginInactiveAccountRespone(token, true));
            }
        }
        else throw new AppException(ErrorCode.NOT_MATCH);
    }

    @PostMapping("/token")
    public ApiRespone<IntrospectRespone> introspect(@RequestBody IntrospectRequest authenticated)
            throws ParseException, JOSEException {
        return new ApiRespone<>(authen.introspectRequest(authenticated));
    }

    @PostMapping("/logout")
    public ApiRespone<Void> logout(@RequestBody LogoutRequest request) throws JOSEException, ParseException {
        authen.Logout(request);
        return new ApiRespone<>(null);
    }
}

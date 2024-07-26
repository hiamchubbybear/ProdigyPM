package com.rs.employer.serviceimplements;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import com.rs.employer.dto.AuthenticationRespone.IntrospectRequest;
import com.rs.employer.dto.AuthenticationRespone.IntrospectRespone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.rs.employer.dto.AuthenticationDto;
import com.rs.employer.dto.AuthenticationRespone.AuthenticationRespone;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.repository.CustomerRepo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

@Service
@RequiredArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@NonNull
public class AuthenticationServiceImp {
    @Autowired
    private CustomerRepo repo;
    @NonFinal
    public static String SIGNER_KEY = "UgCfRRF43z88eCjjLQyzLZBp5hw1WyG15tR2VWg13F5yAPBP4oxKhpy3KViWnwSP";

    public AuthenticationRespone authentication(AuthenticationDto authenticationDto) {
        var user = repo.findByUsername(authenticationDto.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean authenticated = passwordEncoder.matches(authenticationDto.getPassword()
                ,passwordEncoder.encode(user.getPassword()
                        ));
        if (!authenticated)
            throw new AppException(ErrorCode.USER_UNAUTHENTICATED);
        else {
            var token = generateToken(authenticationDto.getUsername(), authenticationDto.getRole());
            AuthenticationRespone aps = new AuthenticationRespone();
            aps.setToken(token);
            aps.setAuthenticated(true);
            return aps;
            // return
            // AuthenticationRespone.builder().token(token).authenticated(true).build();
        }
    }
    public IntrospectRespone introspectRequest(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT =  SignedJWT.parse(token);
        var verified = signedJWT.verify(verifier);
        Date expireDate = signedJWT.getJWTClaimsSet().getExpirationTime();
        IntrospectRespone respone =  new IntrospectRespone();
        respone.setValid(verified && expireDate.after(new Date()));
        return respone;
    }
    private String generateToken(String username, String role) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder().subject(username).subject(role)
                .issuer("Chessy")
                .issueTime(new Date()).expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }
}
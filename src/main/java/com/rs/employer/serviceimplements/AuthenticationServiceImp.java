package com.rs.employer.serviceimplements;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.rs.employer.dto.Request.AuthenticationRequest;
import com.rs.employer.dto.Request.IntrospectRequest;
import com.rs.employer.dto.Request.LogoutRequest;
import com.rs.employer.dto.Respone.AuthenticationRespone;
import com.rs.employer.dto.Respone.IntrospectRespone;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.model.Customer;
import com.rs.employer.model.Invalid;
import com.rs.employer.repository.CustomerRepo;
import com.rs.employer.repository.InvalidRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationServiceImp {
    @Autowired
    private CustomerRepo repo;
    @Autowired
    private InvalidRepository invalidRepository;
    // @NonFinal
    public static String SIGNER_KEY = "UgCfRRF43z88eCjjLQyzLZBp5hw1WyG15tR2VWg13F5yAPBP4oxKhpy3KViWnwSP";

    public AuthenticationRespone authentication(AuthenticationRequest authenticationDto) {
        var user = repo.findByUsername(authenticationDto.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean authenticated = passwordEncoder.matches(authenticationDto.getPassword(),
                user.getPassword());
        if (!authenticated)
            throw new AppException(ErrorCode.USER_UNAUTHENTICATED);
        else {
            var token = generateToken(user);
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
        IntrospectRespone respone = new IntrospectRespone();
        verifiedToken(request.getToken());
        try {
            verifiedToken(token);
        } catch (AppException e) {
            respone.setValid(false);
            return respone;
        }
        respone.setValid(true);
        return respone;
    }

    private String generateToken(Customer customer) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder().subject(customer.getUsername())
                .issuer("Chessy")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now()
                        .plus(1, ChronoUnit.HOURS)
                        .toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(customer))
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

    public void Logout(LogoutRequest request) throws JOSEException, ParseException {
        var signedToken = verifiedToken(request.getToken());
        String jit = signedToken.getJWTClaimsSet().getJWTID();
        Date expireDate = signedToken.getJWTClaimsSet().getExpirationTime();
        Invalid token = new Invalid();
        token.setId(jit);
        token.set(expireDate);
        invalidRepository.save(token);
    }

    private SignedJWT verifiedToken(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        var verified = signedJWT.verify(verifier);
        Date expireDate = signedJWT.getJWTClaimsSet().getExpirationTime();
        if (!(verified && expireDate.after(new Date())))
            throw new AppException(ErrorCode.USER_UNAUTHENTICATED);
        if (!(invalidRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())))
            throw new AppException(ErrorCode.USER_UNAUTHENTICATED);
        return signedJWT;
    }

    private String buildScope(Customer customer) {
        StringJoiner joiner = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(customer.getRoles())) {
            customer.getRoles().forEach(role -> {
                joiner.add("ROLE_" + role.getName());
                if (!CollectionUtils.isEmpty(role.getPermissions()))
                    role.getPermissions().forEach(permission -> joiner.add(permission.getName()));

            });
        }
        return joiner.toString();
    }
}
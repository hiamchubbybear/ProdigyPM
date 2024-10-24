package com.rs.employer.serviceimplements;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;
import java.util.StringJoiner;
import java.util.UUID;

import com.rs.employer.dto.Request.ActivateRequestAccount;
import com.rs.employer.dto.Request.ActivateRequestToken;
import com.rs.employer.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
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
import com.rs.employer.dao.CustomerRepo;
import com.rs.employer.dao.InvalidRepository;
import com.rs.employer.dto.Request.Auth.AuthenticationRequest;
import com.rs.employer.dto.Request.Auth.IntrospectRequest;
import com.rs.employer.dto.Request.Auth.LogoutRequest;
import com.rs.employer.dto.Respone.AuthenticationRespone;
import com.rs.employer.dto.Respone.IntrospectRespone;
import com.rs.employer.globalexception.AppException;
import com.rs.employer.globalexception.ErrorCode;
import com.rs.employer.model.Customer;
import com.rs.employer.model.Invalid;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationService {
    @Autowired
    private CustomerRepo repo;
    @Autowired
    private InvalidRepository invalidRepository;
    private String signer_key = "UgCfRRF43z88eCjjLQyzLZBp5hw1WyG15tR2VWg13F5yAPBP4oxKhpy3KViWnwSP";
    @Autowired
    private EmailService emailService;

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
        }
    }

    public ActivateRequestToken ActivateAccount(ActivateRequestToken treq) throws JOSEException, ParseException {
        try {
            verifiedToken(treq.getToken());
        } catch (AppException e) {
            throw new AppException(ErrorCode.ACTIVATED_FAILED);
        }
        return treq;
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
            jwsObject.sign(new MACSigner(signer_key.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean EmailVerification(ActivateRequestAccount customer) throws JOSEException {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        String token = new Random().nextInt(1000000) + "";
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(customer.getUsername()).issuer("Chessy")
                .jwtID(token).expirationTime(new Date(Instant.now().plus(15, ChronoUnit.MINUTES).toEpochMilli()))
                .claim("email", customer.getEmail()).build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(signer_key.getBytes()));
            emailService.sendActivateToken(customer.getEmail(), customer.getUsername(), jwsObject.serialize());
            return true;
        } catch (JOSEException e) {
            throw new RuntimeException();
        }
    }

//    public boolean EmailVerification(ActivateRequestAccount customer) {
//        // Khởi tạo header
//        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
//
//        // Sinh mã ID duy nhất
//        String token = UUID.randomUUID().toString();
//
//        // Tạo claims
//        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
//                .subject(customer.getUsername())
//                .issuer("Chessy")
//                .jwtID(token)
//                .expirationTime(Date.from(Instant.now().plusSeconds(15 * 60))) // 15 phút
//                .claim("email", customer.getEmail())
//                .build();
//
//        // Tạo đối tượng JWS
//        JWSObject jwsObject = new JWSObject(header, new Payload(jwtClaimsSet.toJSONObject()));
//
//        try {
//            // Ký token
//            jwsObject.sign(new MACSigner(signer_key.getBytes()));
//
//            // Gửi email
//            emailService.sendActivateToken(customer.getEmail(), customer.getUsername(), jwsObject.serialize());
//            return true;
//        } catch (JOSEException e) {
//            // Ghi log hoặc xử lý ngoại lệ
//            e.printStackTrace();
//            throw new RuntimeException("Error signing JWT: " + e.getMessage());
//        }
//    }


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
        JWSVerifier verifier = new MACVerifier(signer_key.getBytes());
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
                joiner.add(role.getName());
                if (!CollectionUtils.isEmpty(role.getPermissions()))
                    role.getPermissions().forEach(permission -> joiner.add(permission.getName()));

            });
        }
        return joiner.toString();
    }
}

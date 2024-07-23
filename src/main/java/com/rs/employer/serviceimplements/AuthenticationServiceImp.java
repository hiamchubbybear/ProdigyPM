package com.rs.employer.serviceimplements;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.employer.dto.AuthenticationDto;
import com.rs.employer.repository.CustomerRepo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
@Service
@RequiredArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@NonNull
public class AuthenticationServiceImp {
    @Autowired
    private CustomerRepo repo;
    public static String SIGNER_KEY = "UgCfRRF43z88eCjjLQyzLZBp5hw1WyG15tR2VWg13F5yAPBP4oxKhpy3KViWnwSP";

    // public IntrospectResponse introspect(IntrospectRequest request)
    // throws JOSEException, ParseException {
    // var token = request.getToken();

    // JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

    // SignedJWT signedJWT = SignedJWT.parse(token);

    // Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

    // var verified = signedJWT.verify(verifier);

    // // return IntrospectResponse.builder()
    // // .valid(verified && expiryTime.after(new Date()))
    // // .build();
    // return null;
    // }

    // // @Autowired
    // // JWEObject jweObject = new JWEObject(header, payload);
    // public AuthenticationRespone authenticate(AuthenticationDto authentication) {
    // var user = repo.findByUsername(authentication.getUsername())
    // .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));
    // PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(5);
    // boolean authenticated =
    // (passwordEncoder.matches(authentication.getPassword(), user.getPassword()));
    // if (!authenticated) {
    // throw new AppException(ErrorCode.UNCATEGORIZE_EXCEPTION);
    // } else {
    // // JWTClaimsSet claimkey = new JWTClaimsSet.Builder().claim("username",
    // // "maven").claim("password", "maven")
    // // .build();
    // // Payload payload = new Payload(claims.toJSONObject());
    // var token = generateToken(authentication);
    // // AuthenticationRespone authen1 = new AuthenticationRespone();
    // // return
    // // AuthenticationRespone.builder().token(token).authenticated(true).build();
    // // return
    // // AuthenticationRespone.builder().token(token).authenticated(true).build();
    // return null;
    // }
    // }

    // private String generateToken(AuthenticationDto authenticationDto) {
    // JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
    // JWTClaimsSet claimsSet = new
    // JWTClaimsSet.Builder().subject(authenticationDto.getUsername())
    // .issuer("https://employer-ksml.onrender.com/")
    // .expirationTime(new Date(new Date().getTime() + 60 * 60 * 1000))
    // // .claim("scope", builscope(user))
    // .build();
    // Payload payload = new Payload(claimsSet.toJSONObject());

    // JWSObject jwsObject = new JWSObject(header, payload);

    // try {
    // jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
    // return jwsObject.serialize();
    // } catch (JOSEException e) {
    // // log.error("Cannot create token", e);
    // System.out.println("Cannot create token");
    // throw new RuntimeException(e);
    // }
    // }

    // private String builscope(AuthenticationDto user) {
    // StringJoiner stringJoiner = new StringJoiner(" ");
    // if (!CollectionUtils.isEmpty(user.getRole()))
    // user.getRoles().forEach(stringJoiner::add);

    // return stringJoiner.toString();

    // }
    public String generateToken(AuthenticationDto authen) {
        String username = authen.getUsername();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + 604800000);
        String token = Jwts.builder().subject(username).issuedAt(expireDate).signWith(Keys.hmacShaKeyFor(SIGNER_KEY.getBytes())).compact();
        return token;
//        signWith(Keys.hmacShaKeyFor(SIGNER_KEY.getBytes()), SignatureAlgorithm.HS256)
    }
    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SIGNER_KEY));
    }

}
package com.rs.employer.security;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
        public final String[] PUBLIC_ENDPOINT = {
                        "/api/customer/add",
                        "/auth/login",
                        "/auth/token" };
        public final String SIGNER_KEY = "UgCfRRF43z88eCjjLQyzLZBp5hw1WyG15tR2VWg13F5yAPBP4oxKhpy3KViWnwSP";

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
                httpSecurity
                                .authorizeHttpRequests(
                                                request -> request.requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINT)
                                                                .permitAll()
                                                                .anyRequest().authenticated());
                httpSecurity.csrf(CsrfConfigurer -> CsrfConfigurer.disable());
                httpSecurity.oauth2ResourceServer(
                                oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())));
                return httpSecurity.build();

        }

        @Bean
        JwtDecoder jwtDecoder() {
                SecretKeySpec key = new SecretKeySpec(SIGNER_KEY.getBytes(), "HS256");
                return NimbusJwtDecoder.withSecretKey(key).macAlgorithm(MacAlgorithm.HS256).build();
        }
}

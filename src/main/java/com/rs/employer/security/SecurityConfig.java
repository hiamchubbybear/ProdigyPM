package com.rs.employer.security;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
        private final String[] PUBLIC_ENDPOINT = {
                        "/api/customer/add",
                        "/api/customer/hello",
                        "/api/product/all",
                        "/api/product/getbyid/**",
                        "/api/resources/all",
                        "/api/resources/getbyid/**",
                        "/auth/login",
                        "/auth/token"
        };
        private final String[] VENDOR_ENDPOINT = {
                        "/api/product/add",
                        "/api/product/delete/**",
                        "/api/product/update/**",
        };
        private final String[] USER_ENDPOINT = {
                        "/api/customer/update/**",
                        "/api/customer/delete/**",
                        "/api/customer/getbyid/**"
        };
        private final String[] SUPPLIER_ENDPOINT = {
                        "/api/resources/add",
                        "/api/resources/update/**",
                        "/api/resources/delete/**"
        };
        private final String[] ADMIN_ENDPOINT = {
                        "/api/customer/all",
                        "/api/customer/getMyInfo"
        };
        private final String SIGNER_KEY = "UgCfRRF43z88eCjjLQyzLZBp5hw1WyG15tR2VWg13F5yAPBP4oxKhpy3KViWnwSP";

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
                httpSecurity.authorizeHttpRequests(request -> request
                                .requestMatchers(PUBLIC_ENDPOINT)
                                .permitAll()
                                .requestMatchers(USER_ENDPOINT)
                                .hasAnyAuthority("SCOPE_USER", "SCOPE_ADMIN")
                                .requestMatchers(VENDOR_ENDPOINT)
                                .hasAnyAuthority("SCOPE_VENDOR", "SCOPE_ADMIN")
                                .requestMatchers(SUPPLIER_ENDPOINT)
                                .hasAuthority("SCOPE_SUPPLIER")
                                .requestMatchers(ADMIN_ENDPOINT)
                                .hasAuthority("SCOPE_ADMIN")
                                .anyRequest().authenticated());
                httpSecurity.csrf(CsrfConfigurer -> CsrfConfigurer.disable());
                httpSecurity.oauth2ResourceServer(
                                oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer
                                                .decoder(jwtDecoder())));
                return httpSecurity.build();
        }

        @Bean
        JwtDecoder jwtDecoder() {
                SecretKeySpec key = new SecretKeySpec(SIGNER_KEY.getBytes(), "HS256");
                return NimbusJwtDecoder.withSecretKey(key).macAlgorithm(MacAlgorithm.HS256).build();
        }
}

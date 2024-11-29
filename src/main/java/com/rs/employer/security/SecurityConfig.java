package com.rs.employer.security;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.AbstractConfiguredSecurityBuilder;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig  {
    private final String[] PUBLIC_ENDPOINT = {
            "/api/customer/add",
            "/api/customer/hello",
            "/api/product/all",
            "/api/product/getbyid/**",
            "/api/resources/all",
            "/api/resources/getbyid/**",
            "/auth/login",
            "/auth/logout",
            "/auth/token",
            "/api/customer/getMyInfo",
            "/api/permission/all",
            "/api/permission/add",
            "/api/permission/delete/**",
            "/api/permission/update",
            "/api/role/all",
            "/api/role/add",
            "/api/role/delete/**",
            "/api/role/update",
            "/api/category",
            "/api/cart/**",
            "/api/category/",
            "/api/images",
            "/api/images/upload/**",
            "/api/images/image/download",
            "/api/product/",
            "/api/customer/register/**",
            "/api/customer/updateUser",
            "/api/customer/activate",
            "auth/sendActivateToken",
            "api/customer/hello",
            "api/customer/resetpwd",
            "api/payment/vnpay",
            "api/customer/upload",
            "api/customer/image",
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
            "/api/customer/getMyInfo",

    };
    private final String SIGNER_KEY = "UgCfRRF43z88eCjjLQyzLZBp5hw1WyG15tR2VWg13F5yAPBP4oxKhpy3KViWnwSP";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request -> request
//                .requestMatchers(PUBLIC_ENDPOINT)
//                .permitAll()
//                .requestMatchers(USER_ENDPOINT)
//                .permitAll()
//
//                .requestMatchers(VENDOR_ENDPOINT)
//                .permitAll()
//
//                .requestMatchers(SUPPLIER_ENDPOINT)
//                .permitAll()
//
//                .requestMatchers(ADMIN_ENDPOINT)
//                .requestMatchers("/*/**")
                .anyRequest()
                .permitAll());
//                 .hasAuthority("ADMIN")
//                .anyRequest()
//                .authenticated());
        httpSecurity.cors(AbstractHttpConfigurer::disable);
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
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedHeader("*");
        return null;
    }

}

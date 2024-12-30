package com.rs.employer.service;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class ProcessSecurityContextHolder {
    public static String getUUID(SecurityContext securityContext) {
        Authentication authentication = securityContext.getAuthentication();
        if (authentication.getPrincipal() instanceof Jwt) {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            return String.valueOf(jwt.getClaimAsString("uuid"));
        }
        throw new SecurityException("Unauthorized");
    }
}

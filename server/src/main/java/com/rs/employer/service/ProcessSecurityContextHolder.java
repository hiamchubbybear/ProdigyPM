package com.rs.employer.service;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProcessSecurityContextHolder {
    public static UUID getUUID(SecurityContext securityContext) {
        Authentication authentication = securityContext.getAuthentication();
        if (authentication.getPrincipal() instanceof Jwt) {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            String uuidString = jwt.getClaimAsString("uuid");
            if (uuidString != null) {
                System.out.println("UUID String: " + uuidString);
                UUID uuid = UUID.fromString(uuidString);
                System.out.println("UUID Parse after parsed: " + uuid);
                return uuid;
            }
            throw new SecurityException("UUID claim is missing");
        }
        throw new SecurityException("Unauthorized");
    }

    public static String getUsername(SecurityContext securityContext) {
        return securityContext.getAuthentication().getName();
    }
}

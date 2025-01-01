package com.rs.employer.service;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProcessSecurityContextHolder {
    /**
     * Extracts the UUID of the authenticated user from the SecurityContext.
     * This method assumes that the authentication is based on a JWT token containing an "uuid" claim.
     *
     * @param securityContext The security context containing the authentication information.
     * @return The UUID of the authenticated user, or throws an exception if the user is not authenticated or the JWT token is missing the "uuid" claim.
     * @throws SecurityException If the user is not authenticated or the JWT token is missing the "uuid" claim.
     */
    public static UUID getUUID(SecurityContext securityContext) {
        Authentication authentication = securityContext.getAuthentication();
        if (authentication == null) {
            throw new SecurityException("Authentication object is null.");
        }
        if (!(authentication.getPrincipal() instanceof Jwt)) {
            throw new SecurityException("Principal is not an instance of Jwt.");
        }
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String uuidString = jwt.getClaimAsString("uuid");
        if (uuidString == null) {
            throw new SecurityException("UUID claim is missing in JWT.");
        }
        UUID uuid = UUID.fromString(uuidString);
        System.out.println("UUID String: " + uuidString);
        System.out.println("UUID Parsed after parsed: " + uuid);
        return uuid;
    }

    /**
     * Retrieves the username of the authenticated user from the SecurityContext.
     * This method assumes that the username is stored in the getName() method of the Authentication object.
     *
     * @param securityContext The security context containing the authentication information.
     * @return The username of the authenticated user, or throws an exception if the user is not authenticated.
     * @throws SecurityException If the user is not authenticated.
     */
    public static String getUsername(SecurityContext securityContext) {
        Authentication authentication = securityContext.getAuthentication();
        if (authentication == null) {
            throw new SecurityException("Authentication object is null.");
        }
        return authentication.getName();
    }
}

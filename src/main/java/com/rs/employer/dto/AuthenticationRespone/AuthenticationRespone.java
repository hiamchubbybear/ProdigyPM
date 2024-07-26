package com.rs.employer.dto.AuthenticationRespone;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
@Data
@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = false )
public class AuthenticationRespone {
    private String token;
    private boolean authenticated;  
    
    public AuthenticationRespone() {
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AuthenticationRespone(String token, boolean authenticated) {
        this.token = token;
        this.authenticated = authenticated;
    }
}
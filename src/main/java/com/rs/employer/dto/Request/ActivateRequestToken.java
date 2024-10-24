package com.rs.employer.dto.Request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ActivateRequestToken {


    String token;

    public ActivateRequestToken() {
    }
    @JsonCreator
    public ActivateRequestToken(@JsonProperty("token") String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

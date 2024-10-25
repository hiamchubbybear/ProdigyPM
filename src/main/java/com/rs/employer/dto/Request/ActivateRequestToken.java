package com.rs.employer.dto.Request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;
public class ActivateRequestToken {

    public ActivateRequestToken(String token) {
        this.token = token;
    }

    public ActivateRequestToken() {
    }

    public String getToken() {
        return token;
    }
    @JsonProperty("token")
    public String token;

}

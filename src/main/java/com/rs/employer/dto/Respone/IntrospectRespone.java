package com.rs.employer.dto.Respone;

public class IntrospectRespone {
    boolean valid;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public IntrospectRespone(boolean valid) {
        this.valid = valid;
    }

    public IntrospectRespone() {
    }

}

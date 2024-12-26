package com.rs.employer.dto.Response;

public class ActivateAccountResponse {
    boolean isActivated = false;

    public ActivateAccountResponse(boolean isActivated) {
        this.isActivated = isActivated;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public ActivateAccountResponse() {
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }
}

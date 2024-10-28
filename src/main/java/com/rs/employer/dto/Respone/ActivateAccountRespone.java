package com.rs.employer.dto.Respone;

public class ActivateAccountRespone {
    boolean isActivated = false;

    public ActivateAccountRespone(boolean isActivated) {
        this.isActivated = isActivated;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public ActivateAccountRespone() {
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }
}

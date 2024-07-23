package com.rs.employer.globalexception;

import com.rs.employer.dto.AuthenticationRespone.AuthenticationRespone;

public class AppException extends IllegalStateException {
    public AppException(ErrorCode errorCode) {
        super(errorCode.getStatus());
        this.errorCode = errorCode;
    }

    private ErrorCode errorCode;

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    AuthenticationRespone respone = new AuthenticationRespone();
}

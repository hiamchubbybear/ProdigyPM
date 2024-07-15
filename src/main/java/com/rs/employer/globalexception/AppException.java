package com.rs.employer.globalexception;

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

}

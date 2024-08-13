package com.rs.employer.globalexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public enum ErrorCode {
    USER_NOTFOUND(1002, "User not found", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be at least 4 characters and less than 20 characters",
            HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1004, "Password must be at least 8 characters", HttpStatus.BAD_REQUEST),
    UNCATEGORIZE_EXCEPTION(999, "Uncategorize Exception", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_UNAUTHENTICATED(1000, "User authentication failed", HttpStatus.UNAUTHORIZED),
    USERNAME_EXISTED(1001, "Username existed", HttpStatus.NOT_FOUND),
    UNAUTHORIZED(401, "You don't have permission to access", HttpStatus.FORBIDDEN),
    PRODUCT_NOTFOUND(1006, "Product not found", HttpStatus.NOT_FOUND),
    PRODUCT_EXISTED(1007, "Product existed", HttpStatus.BAD_REQUEST),
    RUNTIME_ERROR(1005, "Run time error", HttpStatus.TOO_MANY_REQUESTS),
    USEREXISTED_OR_USERIDEXISTED(1008, "Userid  existed or Username existed", HttpStatus.BAD_REQUEST);

    private int code;
    private String status;
    private HttpStatusCode statuscode;

    public HttpStatusCode getStatusCode() {
        return this.statuscode;
    }

    public void setStatusCode(HttpStatusCode statuscode) {
        this.statuscode = statuscode;
    }

    private ErrorCode(int code, String status, HttpStatusCode statuscode) {
        this.code = code;
        this.statuscode = statuscode;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

}

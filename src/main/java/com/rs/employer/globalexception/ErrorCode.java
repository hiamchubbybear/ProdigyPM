package com.rs.employer.globalexception;

public enum ErrorCode {
    USER_NOTFOUND(1002, "User not found"),
    USERNAME_INVALID(1003, "Username must be at least 4 characters and less than 20 characters"),
    PASSWORD_INVALID(1004, "Password must be at least 8 characters"),
    UNCATEGORIZE_EXCEPTION(999, "Uncategorize Exception"),
    USER_UNAUTHENTICATED(1000, "User authentication failed"),
    USERNAME_EXISTED(1001, "Username existed"),
    PRODUCT_NOTFOUND(1006, "Product not found"),
    PRODUCT_EXISTED(1007, "Product existed"),
    RUNTIME_ERROR(1005 , "Run time error"),
    USEREXISTED_OR_USERIDEXISTED(1008,"Userid  existed or Username existed");
    private int code;
    private String status;

    private ErrorCode(int code, String status) {
        this.code = code;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

}

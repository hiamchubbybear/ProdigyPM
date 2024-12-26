package com.rs.employer.globalexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public enum ErrorCode {
    USER_NOTFOUND(1002, "User not found", HttpStatus.INTERNAL_SERVER_ERROR),
    USERNAME_INVALID(1003, "Username must be at least 4 characters and less than 20 characters", HttpStatus.BAD_REQUEST),
    USERNAME_EXISTS_OR_EMAIL_EXISTS(1004, "Username or email already exists", HttpStatus.CONFLICT),
    PASSWORD_INVALID(1004, "Password must be at least 8 characters", HttpStatus.BAD_REQUEST),
    UNCATEGORIZE_EXCEPTION(999, "Uncategorize Exception", HttpStatus.INTERNAL_SERVER_ERROR),
    SERVER_INTERNAL_ERROR(998, "Server Internal Error", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_UNAUTHENTICATED(1000, "User authentication failed", HttpStatus.UNAUTHORIZED),
    USERNAME_EXISTED(1001, "Username existed", HttpStatus.NOT_FOUND),
    UNAUTHORIZED(401, "You don't have permission to access", HttpStatus.FORBIDDEN),
    PRODUCT_NOTFOUND(1006, "Product not found", HttpStatus.NOT_FOUND),
    PRODUCT_EXISTED(1007, "Product existed", HttpStatus.BAD_REQUEST),
    RUNTIME_ERROR(1005, "Run time error", HttpStatus.TOO_MANY_REQUESTS),
    PRODUCT_EXISTED_CART(1009, "Product added into cart", HttpStatus.NOT_ACCEPTABLE),
    CATEGORY_NOTFOUND(1011, "Category not found", HttpStatus.NOT_FOUND),
    BRAND_NOTFOUND(1013, "Brand not found", HttpStatus.NOT_FOUND),
    IMAGES_NOTFOUND(1012, "Images not found", HttpStatus.NOT_FOUND),
    CART_NOT_FOUNT(1010, "Cart not exists or can't be find ", HttpStatus.NOT_ACCEPTABLE),
    USEREXISTED_OR_USERIDEXISTED(1008, "Userid  existed or Username existed", HttpStatus.BAD_REQUEST),
    NOT_FOUND_OR_EXISTED(1014, "Can not found or  existed", HttpStatus.CONFLICT),
    ACTIVATED_FAILED(1003, "Activated failed", HttpStatus.CONFLICT),
    TOKEN_NOTFOUND(1015, "Token not found or expired", HttpStatus.NOT_FOUND),
    TOKEN_INVALID(1015, "Token is used or has expired", HttpStatus.NOT_ACCEPTABLE),
    NOT_MATCH(1016, "Account doesn't match with system", HttpStatus.NOT_ACCEPTABLE),
    RESOURCE_NOT_FOUND(1017, "Resource not found", HttpStatus.NOT_FOUND),
    EMPLOYEE_NOT_FOUND(1017, "Employee not found", HttpStatus.NOT_FOUND),
    DEPARTMENT_NOT_FOUND(1018, "Department not found", HttpStatus.NOT_FOUND),
    BOM_NOTFOUND(1019, "BOM not found", HttpStatus.NOT_FOUND),
    SUPPLIER_NOTFOUND(1019, "Supplier not found", HttpStatus.NOT_FOUND),
    PURCHASE_ORDER_NOTFOUND(1020,"Purchase not found" , HttpStatus.NOT_FOUND );

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

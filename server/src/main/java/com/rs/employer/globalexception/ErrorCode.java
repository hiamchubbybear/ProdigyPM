package com.rs.employer.globalexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public enum ErrorCode {

    // NOT FOUND _ RESPONSE
    USER_NOTFOUND(001, "User not found", HttpStatus.INTERNAL_SERVER_ERROR),
    PRODUCT_NOTFOUND(001, "Product not found", HttpStatus.NOT_FOUND),
    CATEGORY_NOTFOUND(001, "Category not found", HttpStatus.NOT_FOUND),
    BRAND_NOTFOUND(001, "Brand not found", HttpStatus.NOT_FOUND),
    IMAGES_NOTFOUND(001, "Images not found", HttpStatus.NOT_FOUND),
    TOKEN_NOTFOUND(001, "Token not found or expired", HttpStatus.NOT_FOUND),
    RESOURCE_NOT_FOUND(001, "Resource not found", HttpStatus.NOT_FOUND),
    EMPLOYEE_NOT_FOUND(001, "Employee not found", HttpStatus.NOT_FOUND),
    DEPARTMENT_NOT_FOUND(001, "Department not found", HttpStatus.NOT_FOUND),
    BOM_NOT_FOUND(001, "BOM not found", HttpStatus.NOT_FOUND),
    SUPPLIER_NOT_FOUND(001, "Supplier not found", HttpStatus.NOT_FOUND),
    PURCHASE_ORDER_NOT_FOUND(001, "Purchase not found", HttpStatus.NOT_FOUND),
    CART_NOT_FOUND(001, "Cart not exists or can't be find ", HttpStatus.NOT_FOUND),
    JOURNAL_ENTRY_NOT_FOUND(001, "Journal entry not exists or can't be find ", HttpStatus.NOT_FOUND )
    , ROLE_NOT_FOUND(001, "Role not exists or can't be find ", HttpStatus.NOT_FOUND  ),


    //EXISTED
    PRODUCT_ALREADY_EXISTS(002, "Product existed", HttpStatus.CONFLICT),
    EMAIL_ALREADY_EXISTS(002, "Email existed", HttpStatus.CONFLICT),
    USERNAME_ALREADY_EXISTS(002, "Username existed", HttpStatus.CONFLICT),


    // INVALID RESPONSE
    USERNAME_INVALID(003, "Username must be at least 4 characters and less than 20 characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(003, "Password must be at least 8 characters", HttpStatus.BAD_REQUEST),
    TOKEN_INVALID(003, "Token is used or has expired", HttpStatus.NOT_ACCEPTABLE),

    // NOT MATCH
    UUID_NOT_MATCH(004, "UUID you provided do not match with any user" , HttpStatus.NOT_EXTENDED ),

    //AUTHENTICATED FAILED
    USER_UNAUTHENTICATED(005, "User authentication failed", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(005, "You don't have permission to access", HttpStatus.FORBIDDEN),
    ACTIVATED_FAILED(005, "Activated failed", HttpStatus.NOT_ACCEPTABLE),

    //SYSTEM ERROR
    RUNTIME_ERROR(006, "Run time error", HttpStatus.TOO_MANY_REQUESTS),
    PRODUCT_EXISTED_CART(006, "Product added into cart", HttpStatus.NOT_ACCEPTABLE),
    NOT_MATCH(006, "Account doesn't match with system", HttpStatus.NOT_ACCEPTABLE),


    //SERVER
    UNCATEGORIZE_EXCEPTION(007,"Uncategorize Exception",HttpStatus.INTERNAL_SERVER_ERROR),
    SERVER_INTERNAL_ERROR(007,"Server Internal Error",HttpStatus.INTERNAL_SERVER_ERROR)

    // NULL
    , NULL_EXCEPTION(010, "Your input data is null" , HttpStatus.GONE );



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

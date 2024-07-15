package com.rs.employer.globalexception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rs.employer.apirespone.ApiRespone;

@ControllerAdvice
public class GlobleExceptionHandler {
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiRespone> handlingResponseEntity(AppException exception) {
        ErrorCode ec = exception.getErrorCode();
        ApiRespone apiRespone = new ApiRespone();
        apiRespone.setCode(ec.getCode());
        apiRespone.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(apiRespone);
    }

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiRespone> handlingUncategorizeException(AppException exception) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setCode(ErrorCode.UNCATEGORIZE_EXCEPTION.getCode());
        apiRespone.setMessage(ErrorCode.UNCATEGORIZE_EXCEPTION.getStatus());
        return ResponseEntity.badRequest().body(apiRespone);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiRespone> handlingArgumentException(MethodArgumentNotValidException exception) {
        String enumKey = exception.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.valueOf(enumKey);
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setCode(errorCode.getCode());
        apiRespone.setMessage(errorCode.getStatus());
        return ResponseEntity.badRequest().body(apiRespone);
    }

    // @ExceptionHandler(value = RuntimeErrorException.class)
    // ResponseEntity<ApiRespone> runtimeErrorException(RuntimeErrorException
    // exception) {
    // ApiRespone apiRespone = new ApiRespone<>();
    // apiRespone.setCode(00);
    // apiRespone.setMessage(exception.getMessage());
    // apiRespone.setResult(false);
    // return ResponseEntity.badRequest().body(apiRespone);
    // }
    // RuntimException --
    @ExceptionHandler(value = NullPointerException.class)
    ResponseEntity<ApiRespone> nullRespone(NullPointerException exception) {

        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setCode(1002);
        apiRespone.setMessage(exception.getMessage());
        apiRespone.setData(false);
        return ResponseEntity.badRequest().body(apiRespone);
    }
}

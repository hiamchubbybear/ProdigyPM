package com.rs.employer.globalexception;

import javax.management.RuntimeErrorException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rs.employer.apirespone.ApiRespone;

@ControllerAdvice
public class GlobleExceptionHandler {
    @ExceptionHandler(value = IllegalStateException.class)
    ResponseEntity<ApiRespone> handlingResponseEntity(IllegalStateException exception) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setCode(400);
        apiRespone.setMessage(exception.getMessage());
        apiRespone.setResult(false);
        return ResponseEntity.badRequest().body(apiRespone);
    }

    // @SuppressWarnings("rawtypes")
    @ExceptionHandler(value = RuntimeErrorException.class)
    ResponseEntity<ApiRespone> runtimeErrorException(RuntimeErrorException exception) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setCode(00);
        apiRespone.setMessage(exception.getMessage());
        apiRespone.setResult(false);
        return ResponseEntity.badRequest().body(apiRespone);
    }
    //RuntimException -- 
    @ExceptionHandler(value = NullPointerException.class)
    ResponseEntity<ApiRespone> nullRespone(NullPointerException exception) {
        ApiRespone apiRespone = new ApiRespone<>();
        apiRespone.setCode(504);
        apiRespone.setMessage(exception.getMessage());
        apiRespone.setResult(false);
        return ResponseEntity.badRequest().body(apiRespone);
    }
}

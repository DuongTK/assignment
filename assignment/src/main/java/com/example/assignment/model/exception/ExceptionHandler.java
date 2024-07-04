package com.example.assignment.model.exception;

import lombok.val;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

//    @org.springframework.web.bind.annotation.ExceptionHandler(value = {MethodArgumentNotValidException.class})
//    protected ResponseEntity<ApiError> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException ex,
//            HttpHeaders headers,
//            HttpStatus status,
//            WebRequest request) {
//        List<String> errors = new ArrayList<String>();
//        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//            errors.add(error.getField() + ": " + error.getDefaultMessage());
//        }
//        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
//            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
//        }
//
//        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
//        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
//    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiError> handleBaseException(BaseException ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> globalExceptionHandler(Exception ex, WebRequest request) {
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());

        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

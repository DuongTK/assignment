package com.example.assignment.model.exception;


public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BaseException(String msg) {
        super(msg);
    }
}
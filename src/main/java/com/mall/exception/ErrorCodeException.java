package com.mall.exception;

import org.springframework.http.HttpStatus;

public class ErrorCodeException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final Object[] errorArguments;
    private String errorMessage;
    private HttpStatus httpStatus;


    public ErrorCodeException(String errorCode, Object errorArguments) {
        super(errorCode);
        this.errorMessage = errorArguments.toString();
        this.errorArguments = new Object[]{errorArguments};
    }


    public HttpStatus getStatus() {
        return this.httpStatus;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
}
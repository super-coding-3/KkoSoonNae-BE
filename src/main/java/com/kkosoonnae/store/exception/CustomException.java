package com.kkosoonnae.store.exception;

public class CustomException extends RuntimeException{
    protected ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;


    }
}

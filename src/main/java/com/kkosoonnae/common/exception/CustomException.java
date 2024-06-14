package com.kkosoonnae.common.exception;

public class CustomException extends RuntimeException{
    protected ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;

    }
}


package com.kkosoonnae.store.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    //Store
    STORE_NOT_FOUND("해당 매장이 없습니다.", HttpStatus.NOT_FOUND),
    // Store Image
    STORE_IMAGE_NOT_FOUND("해당 이미지가 없습니다.", HttpStatus.NOT_FOUND),
    //Style
    STYLE_NOT_FOUND("해당 스타일이 없습니다.", HttpStatus.NOT_FOUND),

    CUSTOMER_NOT_FOUND("유저를 찾을 수 없습니다.",HttpStatus.NOT_FOUND),

    LIKE_STORE_NOT_FOUND("관심매장을 찾을 수 없습니다",HttpStatus.NOT_FOUND),

    DUPLICATE_LIKE_STORE("관심매장이 중복입니다.",HttpStatus.BAD_REQUEST),
    //
    DEFAULT("처리 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;

    private final HttpStatus status;

    ErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
}




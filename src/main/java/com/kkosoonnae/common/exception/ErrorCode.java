package com.kkosoonnae.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    //Store
    STORE_NOT_FOUND("해당 매장이 없습니다.", HttpStatus.NOT_FOUND),
    // Store Image
    STORE_IMAGE_NOT_FOUND("해당 이미지가 없습니다.", HttpStatus.NOT_FOUND),
    //Style
    STYLE_NOT_FOUND("해당 스타일이 없습니다.", HttpStatus.NOT_FOUND),
    STORE_SAME_NAME("해당 매장이름은 존재합니다.",HttpStatus.CONFLICT),

    CUSTOMER_NOT_FOUND("유저를 찾을 수 없습니다.",HttpStatus.NOT_FOUND),

    LIKE_STORE_NOT_FOUND("관심매장을 찾을 수 없습니다",HttpStatus.NOT_FOUND),

    DUPLICATE_LIKE_STORE("관심매장이 중복입니다.",HttpStatus.BAD_REQUEST),

    DUPLICATE_LOGIN("중복된 아이디 입니다.",HttpStatus.CONFLICT),

    DUPLICATE_NICKNAME("중복된 닉네임 입니다.",HttpStatus.CONFLICT),
    REVIEW_NOT_FOUND("리뷰를 찾을 수 없습니다." ,HttpStatus.NOT_FOUND),
    DEFAULT("처리 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),

    STORE_IMG_MISMATCH("해당 매장 이미지가 없습니다.",HttpStatus.NOT_FOUND),
    STORE_IMG_NOT_FOUND("해당 매장에 id값에 해당하는 이미지가 없습니다.",HttpStatus.NOT_FOUND),
    USER_NOT_LOGIN("로그인 하지 않았습니다.",HttpStatus.UNAUTHORIZED),
    INVALID_SEARCH_KEYWORD("키워드를 제대로 입력하세요",HttpStatus.NOT_FOUND),
    DATABASE_ERROR("데이터베이스 접근 중 오류",HttpStatus.INTERNAL_SERVER_ERROR),
    FILE_UPLOAD_ERROR("S3 파일업로드 오류",HttpStatus.INTERNAL_SERVER_ERROR),

    INTERNAL_SERVER_ERROR("서버에서 요청을 처리하는중에 오류가 발생했습니다. 개발자에게 문의해주세요",HttpStatus.INTERNAL_SERVER_ERROR),

    NOT_MATCH_PASSWORD("비밀번호가 일치하지 않습니다.",HttpStatus.UNAUTHORIZED);
    private final String message;

    private final HttpStatus status;

    ErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

}





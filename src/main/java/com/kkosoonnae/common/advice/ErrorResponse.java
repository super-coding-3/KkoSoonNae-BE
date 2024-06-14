package com.kkosoonnae.common.advice;
import com.kkosoonnae.common.exception.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;


@Getter
public class ErrorResponse {

    private final String message;

    @Builder
    public ErrorResponse(String message) {
        this.message = message;

    }
    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode) {
        return  ResponseEntity.status(errorCode.getStatus())
                .body(ErrorResponse.builder()
                        .message(errorCode.getMessage())
                        .build());
    }
}

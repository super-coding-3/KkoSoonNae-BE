package com.kkosoonnae.user.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * packageName    : com.kkosoonnae.review.dto
 * fileName       : ReviewRqDto
 * author         : hagjoon
 * date           : 2024-05-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-28        hagjoon       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRqDto {

    @Schema(defaultValue = "총점")
    private Integer scope;

    @Schema(defaultValue = "내용")
    private String content;

    @Schema(defaultValue = "등록일자")
    private LocalDateTime createDt;
}

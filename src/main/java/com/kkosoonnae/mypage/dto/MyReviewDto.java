package com.kkosoonnae.mypage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

/**
 * packageName    : com.kkosoonnae.mypage.dto
 * fileName       : MyReviewDto
 * author         : hagjoon
 * date           : 2024-05-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-24        hagjoon       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyReviewDto {

    @Schema(description = "리뷰 일련번호")
    private Integer reviewNo;

    @Schema(description = "매장 일련번호")
    private Integer storeNo;

    @Schema(description = "매장 명")
    private String storeName;

    @Schema(description = "별점")
    private Integer scope;

    @Schema(description = "이미지")
    private String reviewImg;

    @Schema(description = "내용")
    private String content;

    @Schema(description = "생성 시간")
    private LocalTime createTime;
}

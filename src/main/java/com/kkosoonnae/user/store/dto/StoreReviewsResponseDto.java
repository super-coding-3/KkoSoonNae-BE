package com.kkosoonnae.user.store.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreReviewsResponseDto {
    @Schema(description = "매장 번호")
    private Integer storeNo;

    @Schema(description = "매장 이름")
    private String storeName;

    @Schema(description = "관심수")
    private Long totalLikeStore;

    @Schema(description = "리뷰 번호")
    private Integer reviewNo;

    @Schema(description = " 고객 번호")
    private Integer cstmrNo;

    @Schema(description = "리뷰 본문")
    private String content;

    @Schema(description = "리뷰 날짜")
    private LocalDateTime reviewDt;

    @Schema(description = "별점")
    private Integer scope;

    @Schema(description = "총점")
    private Double averageScope;

    @Schema(description = "유저 닉네임")
    private String nickName;

    @Schema(description = "펫 이미지")
    private String img;

    @Schema(description = "메인펫이미지")
    private String mainPet;
}


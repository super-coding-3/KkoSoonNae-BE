package com.kkosoonnae.user.store.dto;


import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreReviewsResponseDto {
    @Schema(description = "매장 번호")
    private Integer storeNo;

    @Schema(description = "매장 이름")
    private String storeName;

    @Schema(description = "총점")
    private Double averageScope;

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

    @Schema(description = "유저 닉네임")
    private String nickName;

    @Schema(description = "펫 이미지")
    private String img;

    @Schema(description = "메인펫이미지")
    private String mainPet;

    public StoreReviewsResponseDto ReviewToDto(Double averageScope,Long totalLikeStore) {
        return StoreReviewsResponseDto.builder()
                .storeNo(storeNo)
                .storeName(storeName)
                .totalLikeStore(totalLikeStore)
                .averageScope(averageScope)
                .reviewNo(reviewNo)
                .cstmrNo(cstmrNo)
                .content(content)
                .reviewDt(reviewDt)
                .scope(scope)
                .nickName(nickName)
                .img(img)
                .mainPet(mainPet)
                .build();
    }
    @QueryProjection
    public StoreReviewsResponseDto(Integer storeNo, String storeName, Integer reviewNo, Integer cstmrNo, String content, LocalDateTime reviewDt, Integer scope, String nickName, String img, String mainPet) {
        this.storeNo = storeNo;
        this.storeName = storeName;
        this.reviewNo = reviewNo;
        this.cstmrNo = cstmrNo;
        this.content = content;
        this.reviewDt = reviewDt;
        this.scope = scope;
        this.nickName = nickName;
        this.img = img;
        this.mainPet = mainPet;
    }
}






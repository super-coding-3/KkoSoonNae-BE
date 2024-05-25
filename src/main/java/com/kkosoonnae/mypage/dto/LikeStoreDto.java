package com.kkosoonnae.mypage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

/**
 * packageName    : com.kkosoonnae.mypage.dto
 * fileName       : LikeStoreDto
 * author         : hagjoon
 * date           : 2024-05-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-23        hagjoon       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeStoreDto {

    @Schema(description = "관심매장 일련번호")
    private Integer likeNo;

    @Schema(description = "매장 일련번호")
    private Integer storeNo;

    @Schema(description = "매장 이미지")
    private String storeImg;

    @Schema(description = "매장 명")
    private String storeName;

    @Schema(description = "별점")
    private Double scope;

    @Schema(description = "관심 수")
    private Integer totalLikeCount;

    @Schema(description = "도로명 주소")
    private String roadAddress;

    @Schema(description = "오픈시간")
    private LocalTime openTime;

    @Schema(description = "종료시간")
    private LocalTime closeTime;
}

package com.kkosoonnae.mypage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * packageName    : com.kkosoonnae.mypage.dto
 * fileName       : AvailDto
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
public class AvailDto {

    @Schema(description = "예약 일련번호")
    private Integer reservationNo;

    @Schema(description = "예약 날짜")
    private LocalDate reservationDate;

    @Schema(description = "예약 시간")
    private LocalTime reservationTime;

    @Schema(description = "예약상태")
    private String reservationStatus;

    @Schema(description = "매장 이미지")
    private String storeImg;

    @Schema(description = "매장 명")
    private String storeName;

    @Schema(description = "스타일 명")
    private String styleName;

    @Schema(description = "가격")
    private Integer price;
}

package com.kkosoonnae.reservation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ReservationRequest {
    @Schema(description = "매장 일련번호")
    private Integer storeNo;

    @Schema(description = "예약 날짜 ( ex. 2024-05-22 )")
    private String reservationDate;

    @Schema(description = "예약 시간 ( ex. 16:00 )")
    private String reservationTime;

    @Schema(description = "스타일 이름")
    private String styleName;

    @Schema(description = "펫 일련번호")
    private Integer petNo;

    @Schema(description = "특징")
    private String feature;
}

package com.kkosoonnae.reservation.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
    private Integer storeNumber;

    @Schema(description = "매장 이름")
    private String storeName;

    @Schema(description = "예약 날짜 ( ex. 2024-05-22 )")
    private String reservationDate;

    @Schema(description = "예약 시간 ( ex. 16:00 )")
    private String reservationTime;

    @Schema(description = "스타일 이름")
    private String cutStyle;

    @Schema(description = "펫 이름")
    private String petName;

    @Schema(description = "펫 종")
    private String breed;

    @Schema(description = "펫 몸무게")
    private String weight;

    @Schema(description = "특징")
    private String characteristics;
}

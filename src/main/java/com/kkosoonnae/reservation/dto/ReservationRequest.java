package com.kkosoonnae.reservation.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReservationRequest {

    @Schema(description = "매장 일련번호")
    private Integer storeNo;

    @Schema(description = "예약 날짜")
    private LocalDate reservationDate;

    @Schema(description = "예약 시간")
    private LocalTime reservationTime;

    @Schema(description = "스타일 이름")
    private String styleName;

//    @Schema(description = "반려동물 종류")
//    private String type;
//
//    @Schema(description = "반려동물 몸무게")
//    private String weight;

    @Schema(description = "펫 일련번호")
    private Integer petNo;

    @Schema(description = "특징")
    private String feature;

}

package com.kkosoonnae.president.reservationmanage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class DateAndStatusRequest {
//    @Schema(description = "매장 일련번호")
//    private Integer storeNumber;

    @Schema(description = "예약 날짜 ( ex. 2024-05-22 )")
    private String startDate;

    @Schema(description = "예약 날짜 ( ex. 2024-05-23 )")
    private String endDate;

    @Schema(description = "예약 상태")
    private String reservationStatus;
}

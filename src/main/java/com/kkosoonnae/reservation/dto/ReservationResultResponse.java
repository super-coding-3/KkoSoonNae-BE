package com.kkosoonnae.reservation.dto;

import com.kkosoonnae.jpa.entity.Style;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReservationResultResponse {
    @Schema(description = "매장 이름")
    private String storeName;

    @Schema(description = "예약 날짜")
    private String reservationDate;

    @Schema(description = "예약 시간")
    private String reservationTime;

    @Schema(description = "스타일 이름")
    private String styleName;

    @Schema(description = "반려동물 종류")
    private String type;

    @Schema(description = "반려동물 몸무게")
    private String weight;

    @Schema(description = "특징")
    private String feature;
}

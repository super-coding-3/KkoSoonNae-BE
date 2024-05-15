package com.kkosoonnae.reservation.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kkosoonnae.jpa.entity.Pet;
import com.kkosoonnae.jpa.entity.Reservation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationResponse {

    @Schema(description = "매장 이름")
    private String storeName;

    @Schema(description = "예약 날짜")
    private LocalDate reservationDate;

    @Schema(description = "예약 시간")
    private LocalTime reservationTime;

    @Schema(description = "스타일 이름")
    private String styleName;

    @Schema(description = "반려동물 종류")
    private String type;

    @Schema(description = "반려동물 몸무게")
    private String weight;

    @Schema(description = "특징")
    private String feature;

    public ReservationResponse(String storeName, Reservation reservation, String styleName, Pet pet) {
        this.storeName = storeName;
        this.reservationDate = reservation.getReservationDate();
        this.reservationTime = reservation.getReservationTime();
        this.styleName = styleName;
        this.type = pet.getType();
        this.weight = pet.getWeight();
        this.feature = reservation.getFeature();
    }

}
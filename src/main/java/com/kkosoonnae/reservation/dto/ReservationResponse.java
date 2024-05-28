package com.kkosoonnae.reservation.dto;

import com.kkosoonnae.jpa.entity.Pet;
import com.kkosoonnae.jpa.entity.Reservation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationResponse {
    @Schema(description = "매장 이름")
    private String storeName;

    @Schema(description = "예약 날짜")
    private String reservationDate;

    @Schema(description = "예약 시간")
    private String reservationTime;

    @Schema(description = "스타일 이름")
    private String cutStyle;

    @Schema(description = "가격")
    private String price;

    @Schema(description = "펫 이름")
    private String petName;

    @Schema(description = "펫 이미지")
    private String petImg;

    @Schema(description = "반려동물 종류")
    private String breed;

    @Schema(description = "반려동물 몸무게")
    private String weight;

    @Schema(description = "특징")
    private String characteristics;

    @Schema(description = "예약 일련번호")
    private Integer reservationNumber;

    public ReservationResponse(String storeName, String responseDate,Reservation reservation, String styleName, String price, Pet pet, Integer reservationNumber) {
        this.storeName = storeName;
        this.reservationDate = responseDate;
        this.reservationTime = reservation.getReservationTime().toString();
        this.cutStyle = styleName;
        this.price = price;
        this.petName = pet.getName();
        this.petImg = getPetImg();
        this.breed = pet.getType();
        this.weight = pet.getWeight();
        this.characteristics = reservation.getFeature();
        this.reservationNumber = reservationNumber;
    }
}

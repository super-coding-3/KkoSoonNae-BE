package com.kkosoonnae.user.reservation.dto;

import com.kkosoonnae.jpa.entity.Pet;
import com.kkosoonnae.jpa.entity.Reservation;
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

    public ReservationResultResponse(Reservation reservation, String storeName, String stringPrice, Pet pet, String responseDate) {
        this.storeName = storeName;
        this.reservationDate = responseDate;
        this.reservationTime = String.valueOf(reservation.getReservationTime());
        this.cutStyle = reservation.getStyleName();
        this.price = stringPrice;
        this.petName = pet.getName();
        this.petImg = pet.getImg();
        this.breed = pet.getType();
        this.weight = pet.getWeight();
        this.characteristics = reservation.getFeature();
    }
}

package com.kkosoonnae.jpa.entity;

import com.kkosoonnae.jpa.entity.Pet;
import com.kkosoonnae.jpa.entity.Reservation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationListResponse {

    @Schema(description = "예약 번호")
    private Integer reservationNo;

    @Schema(description = "고객 이름")
    private String cstmrName;

    @Schema(description = "예약 날짜")
    private String reservationDate;

    @Schema(description = "예약 상태")
    private String reservationStatus;

    public ReservationListResponse(Integer reservationNo, String nickName, LocalDate reservationDate, String reservationStatus) {
        this.reservationNo = reservationNo;
        this.cstmrName = nickName;
        this.reservationDate = reservationDate.toString();
        this.reservationStatus = reservationStatus;
    }

}

package com.kkosoonnae.jpa.entity;

import com.kkosoonnae.jpa.entity.Pet;
import com.kkosoonnae.jpa.entity.Reservation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReservationListResponse {

    @Schema(description = "예약 번호")
    private Integer reservationNumber;

    @Schema(description = "고객 이름")
    private String cstmrName;

    @Schema(description = "예약 날짜 및 시간")
    private String reservationDateAndTime;

    @Schema(description = "예약 상태")
    private String reservationStatus;

    public ReservationListResponse(Integer reservationNo, String nickName, LocalDate reservationDate, LocalTime reservationTime, String reservationStatus) {
        this.reservationNumber = reservationNo;
        this.cstmrName = nickName;
        this.reservationDateAndTime = formatReservationDateTime(reservationDate, reservationTime);
        this.reservationStatus = reservationStatus;
    }

    private String formatReservationDateTime(LocalDate reservationDate, LocalTime reservationTime) {
        // 요일을 구하고 한국어로 표시하기 위해 Locale 설정
        String dayOfWeek = reservationDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.KOREAN);

        // 시간 포맷 설정
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("a h:mm").withLocale(Locale.KOREAN);

        // 날짜 포맷 설정
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

        // 날짜와 시간 합치기
        return reservationDate.format(dateFormatter) + "(" + dayOfWeek + ") " + reservationTime.format(timeFormatter);
    }

}

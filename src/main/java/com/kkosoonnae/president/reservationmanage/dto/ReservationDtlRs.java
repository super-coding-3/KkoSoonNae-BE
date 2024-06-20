package com.kkosoonnae.president.reservationmanage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

@NoArgsConstructor
@Getter
@Setter
public class ReservationDtlRs {

    @Schema(description = "예약 번호")
    private Integer reservationNumber;

    @Schema(description = "고객 이름")
    private String cstmrName;

    @Schema(description = "예약 날짜")
    private String reservationDate;

    @Schema(description = "예약 시간")
    private String reservationTime;

    @Schema(description = "스타일")
    private String cutStyle;

    @Schema(description = "견종/묘종")
    private String breed;

    @Schema(description = "펫 이름")
    private String petName;

    @Schema(description = "반려동물 몸무게")
    private String weight;

    @Schema(description = "특징")
    private String characteristics;

    public ReservationDtlRs(Integer reservationNo, String nickName, LocalDate reservationDate, LocalTime reservationTime,
                            String styleName, String type, String petName, String weight, String feature)
    {
        this.reservationNumber = reservationNo;
        this.cstmrName = nickName;
        this.reservationDate = formatReservationDate(reservationDate);
        this.reservationTime = reservationTime.toString();
        this.cutStyle = styleName;
        this.breed = type;
        this.petName = petName;
        this.weight = weight;
        this.characteristics = feature;
    }

    private String formatReservationDate(LocalDate date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일", Locale.KOREAN);
        String dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.KOREAN);
        return date.format(dateFormatter) + " (" + dayOfWeek + ")";
    }
}

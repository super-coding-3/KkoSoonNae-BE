package com.kkosoonnae.jpa.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kkosoonnae.reservation.dto.ReservationRequest;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "RESERVATION")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVATION_NO")
    private Integer reservationNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AVAIL_NO")
    private AvailTime avail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_NO")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CSTMR_NO")
    private CustomerBas cstmrBas;

    @Column(name = "RESERVATION_STATUS")
    private String reservationStatus;

    @Column(name = "RESERVATION_DATE")
    private LocalDate reservationDate;

    @Column(name = "RESERVATION_TIME")
    private LocalTime reservationTime;

    @Column(name = "FEATURE")
    private String feature;

    @Column(name = "STYLE_NAME")
    private String styleName;

    public Reservation(Store store, AvailTime avail, CustomerBas cstmrBas, ReservationRequest reservationRequest) {
        this.avail = avail;
        this.cstmrBas = cstmrBas;
        this.store = store;
        this.reservationStatus = "예약 완료";
        this.reservationDate = LocalDate.parse(reservationRequest.getReservationDate());
        this.reservationTime = LocalTime.parse(reservationRequest.getReservationTime());
        this.feature = reservationRequest.getCharacteristics();
        this.styleName = reservationRequest.getCutStyle();
    }
}

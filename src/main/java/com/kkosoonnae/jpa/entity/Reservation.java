package com.kkosoonnae.jpa.entity;

import com.kkosoonnae.reservation.dto.ReservationRequest;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

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

    @Column(name = "AVAIL_NO")
    private Integer availNo;

    @Column(name = "STORE_NO")
    private Integer storeNo;

    @Column(name = "CSTMER_NO")
    private Integer cstmerNo;

    @Column(name = "RESERVATION_STATUS")
    private String reservationStatus;

    @Column(name = "RESERVATION_DATE")
    private LocalDate reservationDate;

    @Column(name = "RESERVATION_TIME")
    private LocalTime reservationTime;

    @Column(name = "FEATURE")
    private String feature;

    public Reservation(Integer availNo, Integer cstmerNo, ReservationRequest reservationRequest) {
        this.availNo = availNo;
        this.cstmerNo = cstmerNo;
        this.storeNo = reservationRequest.getStoreNo();
        this.reservationStatus = "확정";
        this.reservationDate = reservationRequest.getReservationDate();
        this.reservationTime = reservationRequest.getReservationTime();
        this.feature = reservationRequest.getFeature();
    }
}

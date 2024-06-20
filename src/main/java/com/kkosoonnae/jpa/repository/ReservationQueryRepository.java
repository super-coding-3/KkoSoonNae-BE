package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.QCustomerBas;
import com.kkosoonnae.jpa.entity.QCustomerDtl;
import com.kkosoonnae.jpa.entity.QReservation;
import com.kkosoonnae.jpa.entity.ReservationListResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@RequiredArgsConstructor
@Repository
@Slf4j
public class ReservationQueryRepository {

    private final JPAQueryFactory query;
    private final EntityManager entityManager;

    public List<ReservationListResponse> findReservation(String name, LocalDate reservationStartDate, LocalDate reservationEndDate, String status) {

        QReservation reservation = QReservation.reservation;
        QCustomerBas customerBas = QCustomerBas.customerBas;
        QCustomerDtl customerDtl = QCustomerDtl.customerDtl;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(reservation.reservationDate.between(reservationStartDate, reservationEndDate));

        if (status != null && !status.isEmpty()) {
            builder.and(reservation.reservationStatus.eq(status));
        }

        if (name != null && !name.isEmpty()) {
            builder.and(customerDtl.nickName.eq(name));
        }

        return query.select(Projections.constructor(
                ReservationListResponse.class,
                reservation.reservationNo,
                customerBas.customerDtl.nickName,
                reservation.reservationDate,
                reservation.reservationStatus
        ))
        .from(reservation)
        .leftJoin(customerBas)
        .on(reservation.cstmrBas.cstmrNo.eq(customerBas.cstmrNo))
        .leftJoin(customerDtl)
        .on(customerBas.cstmrNo.eq(customerDtl.cstmrNo))
        .where(builder)
        .fetch();

    }

}

package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.*;
import com.kkosoonnae.mypage.dto.AvailDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName    : com.kkosoonnae.jpa.repository
 * fileName       : CustomerQueryRepository
 * author         : hagjoon
 * date           : 2024-05-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-23        hagjoon       최초 생성
 */
@RequiredArgsConstructor
@Repository
@Slf4j
public class CustomerQueryRepository {

    private final JPAQueryFactory query;

    public List<AvailDto> availList(Integer cstmrNo){
        QCustomerAvail customerAvail = QCustomerAvail.customerAvail;
        QReservation reservation = QReservation.reservation;
        QStyle style = QStyle.style;
        QStore store = QStore.store;


        return query.select(Projections.bean(AvailDto.class,
                reservation.reservationNo,
                reservation.reservationDate,
                reservation.reservationTime,
                reservation.reservationStatus,
                store.storeName,
                reservation.styleName,
                style.price
                ))
                .from(customerAvail)
                .leftJoin(reservation)
                .on(customerAvail.reservationNo.reservationNo.eq(reservation.reservationNo))
                .leftJoin(store)
                .on(reservation.store.storeNo.eq(store.storeNo))
                .leftJoin(style)
                .on(store.storeNo.eq(style.store.storeNo))
                .where(customerAvail.cstmrNo.cstmrNo.eq(cstmrNo)
                                .and(style.styleName.eq(reservation.styleName))
                        )
                .fetch();

    }

    public void deleteAvail(Integer reservationNo){
        QCustomerAvail customerAvail = QCustomerAvail.customerAvail;
        QReservation reservation = QReservation.reservation;
        QReservedPets reservedPets = QReservedPets.reservedPets;

        long customerAvailDelete = query.delete(customerAvail)
                .where(customerAvail.reservationNo.reservationNo.eq(reservationNo))
                .execute();

        long reservedPetsDelete = query.delete(reservedPets)
                .where(reservedPets.reservation.reservationNo.eq(reservationNo))
                .execute();

        long reservationDelete = query.delete(reservation)
                .where(reservation.reservationNo.eq(reservationNo))
                .execute();

    }

    // 회원 번호와 예약 번호로 예약이 존재하는지 확인
    public boolean existsByCstmrNoAndReservationNo(Integer cstmrNo, Integer reservationNo) {
        QCustomerAvail customerAvail = QCustomerAvail.customerAvail;
        return query.selectFrom(customerAvail)
                .where(customerAvail.cstmrNo.cstmrNo.eq(cstmrNo)
                        .and(customerAvail.reservationNo.reservationNo.eq(reservationNo)))
                .fetch().size() > 0;
    }
}

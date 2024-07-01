package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.*;
import com.kkosoonnae.president.reservationmanage.dto.ReservationDtlRs;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
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
                reservation.reservationTime,
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

    public ReservationDtlRs findReservationDtl(Integer reservationNumber) {
        QReservation reservation = QReservation.reservation;
        QCustomerBas customerBas = QCustomerBas.customerBas;
        QCustomerDtl customerDtl = QCustomerDtl.customerDtl;
        QReservedPets reservedPets = QReservedPets.reservedPets;
        QPet pet = QPet.pet;

        return query.select(Projections.constructor(ReservationDtlRs.class,
                reservation.reservationNo,
                customerBas.customerDtl.nickName,
                reservation.reservationDate,
                reservation.reservationTime,
                reservation.styleName,
                reservedPets.pet.type,
                reservedPets.pet.name,
                reservedPets.pet.weight,
                reservation.feature
        ))
                .from(reservation)
                .leftJoin(customerBas)
                .on(reservation.cstmrBas.cstmrNo.eq(customerBas.cstmrNo))
                .leftJoin(customerDtl)
                .on(customerBas.cstmrNo.eq(customerDtl.cstmrNo))
                .leftJoin(reservedPets)
                .on(reservation.reservationNo.eq(reservedPets.reservation.reservationNo))
                .leftJoin(pet)
                .on(reservedPets.pet.petNo.eq(pet.petNo))
                .where(reservation.reservationNo.eq(reservationNumber))
                .fetchOne();
    }

    public CustomerBas getCustomerBas(String loginId) {
        QCustomerBas customerBas = QCustomerBas.customerBas;
        return query.selectFrom(customerBas).where(customerBas.loginId.eq(loginId)).fetchOne();
    }

    public AvailTime getAvailTime(Integer storeNo) {
//        QAvailTime availTime = QAvailTime.availTime;
        QAvailTime availTime = QAvailTime.availTime1;
        return query.selectFrom(availTime)
                .where(availTime.store.storeNo.eq(storeNo))
                .fetchOne();
    }

    public Style findByStoreNoAndStyleName(Integer storeNo, String cutStyle) {
        QStyle style = QStyle.style;
        return query.selectFrom(style).where(style.store.storeNo.eq(storeNo).and(style.styleName.eq(cutStyle))).fetchOne();
    }

    public Store findStoreById(Integer storeNo) {
        QStore store = QStore.store;
        return query.selectFrom(store)
                .where(store.storeNo.eq(storeNo))
                .fetchOne();
    }

    public Pet findByCstmrNoAndPetNo(Integer cstmrNo, String petName) {
        QPet pet = QPet.pet;
        return query.selectFrom(pet)
                .where(pet.customerBas.cstmrNo.eq(cstmrNo)
                        .and(pet.name.eq(petName)))
                .fetchOne();
    }

    public Reservation findByStoreNoAndReservationDateAndReservationTime(Integer storeNo, LocalDate reservationDate, LocalTime reservationTime) {
        QReservation reservation = QReservation.reservation;
        return query.selectFrom(reservation)
                .where(reservation.store.storeNo.eq(storeNo)
                        .and(reservation.reservationDate.eq(reservationDate))
                        .and(reservation.reservationTime.eq(reservationTime)))
                .fetchOne();
    }

    @Transactional
    public void saveReservation(Reservation reservation) {
        entityManager.persist(reservation);
    }

    @Transactional
    public void saveCustomerAvail(CustomerAvail customerAvail) {
        entityManager.persist(customerAvail);
    }

    @Transactional
    public void saveReservedPets(ReservedPets reservedPets) {
        entityManager.persist(reservedPets);
    }

    public List<Style> findStylNameByStoreNo(Integer storeNumber) {
        QStyle style = QStyle.style;

        return query.selectFrom(style)
                .where(style.store.storeNo.eq(storeNumber))
                .fetch();
    }

    public Store findStoreNameByStoreNo(Integer storeNumber) {
        QStore store = QStore.store;

        return query.selectFrom(store)
                .where(store.storeNo.eq(storeNumber))
                .fetchOne();
    }

    public Integer findCstmrNoByLoginId(String loginId) {
        QCustomerBas customerBas = QCustomerBas.customerBas;

        return query.select(customerBas.cstmrNo)
                .from(customerBas)
                .where(customerBas.loginId.eq(loginId))
                .fetchOne();
    }

    public List<Pet> findByCstmrNo(Integer cstmrNo) {
        QPet pet = QPet.pet;

        return query.selectFrom(pet)
                .where(pet.customerBas.cstmrNo.eq(cstmrNo))
                .fetch();
    }

    public Reservation findReservationById(Integer reservationNumber) {
        QReservation reservation = QReservation.reservation;

        return query.selectFrom(reservation)
                .where(reservation.reservationNo.eq(reservationNumber))
                .fetchOne();
    }

    public ReservedPets findByReservationNo(Integer reservationNumber) {
        QReservedPets reservedPets = QReservedPets.reservedPets;

        return query.selectFrom(reservedPets)
                .where(reservedPets.reservation.reservationNo.eq(reservationNumber))
                .fetchOne();
    }

    public Pet findByPetNo(Integer petNo) {
        QPet pet = QPet.pet;

        return query.selectFrom(pet)
                .where(pet.petNo.eq(petNo))
                .fetchOne();
    }

}

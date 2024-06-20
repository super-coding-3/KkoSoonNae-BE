package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.Reservation;
import com.kkosoonnae.jpa.entity.ReservationListResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query("SELECT r FROM Reservation r WHERE r.store.storeNo = :storeNo AND r.reservationDate = :reservationDate AND r.reservationTime = :reservationTime")
    Reservation findByStoreNoAndReservationDateAndReservationTime(Integer storeNo, LocalDate reservationDate, LocalTime reservationTime);


//    @Query("SELECT new com.kkosoonnae.jpa.entity.ReservationListResponse(r.reservationNo, cb.customerDtl.nickName, r.reservationDate, r.reservationStatus) " +
//    "FROM Reservation r " +
//    "JOIN r.cstmrBas cb ON r.cstmrBas.cstmrNo = cb.cstmrNo " +
//    "WHERE r.reservationDate BETWEEN :reservationStartDate AND :reservationEndDate AND r.reservationStatus = :status")
//    List<ReservationListResponse> findByReservationDateAndReservationStatus(LocalDate reservationStartDate, LocalDate reservationEndDate, String status);
}

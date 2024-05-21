package com.kkosoonnae.jpa.repository;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kkosoonnae.jpa.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query("SELECT r FROM Reservation r WHERE r.cstmrBas.cstmrNo = :cstmrNo ORDER BY r.reservationNo DESC")
    List<Reservation> findTopByCstmrNoOrderByReservationNoDesc(Integer cstmrNo);

    @Query("SELECT r FROM Reservation r WHERE r.store.storeNo = :storeNo AND r.reservationDate = :reservationDate AND r.reservationTime = :reservationTime")
    boolean existsByStoreNoAndDateAndTime(Integer storeNo, LocalDate reservationDate, LocalTime reservationTime);

    @Query("SELECT r FROM Reservation r WHERE r.store.storeNo = :storeNo AND r.reservationDate = :reservationDate AND r.reservationTime = :reservationTime")
    Reservation findByStoreNoAndReservationDateAndReservationTime(Integer storeNo, LocalDate reservationDate, LocalTime reservationTime);
}

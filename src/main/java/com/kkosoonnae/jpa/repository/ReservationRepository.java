package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query("SELECT r.reservationNo FROM Reservation r WHERE r.cstmerNo = :cstmrNo")
    Reservation findByCstmrNo(Integer cstmrNo);

    @Query("SELECT r.reservationNo FROM Reservation r WHERE r.storeNo = :storeNo AND r.reservationDate = :reservationDate AND r.reservationTime = :reservationTime")
    boolean existsByStoreNoAndDateAndTime(Integer storeNo, LocalDate reservationDate, LocalTime reservationTime);
}

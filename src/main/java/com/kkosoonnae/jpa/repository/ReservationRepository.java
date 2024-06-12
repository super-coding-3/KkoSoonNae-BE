package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    @Query("SELECT r FROM Reservation r WHERE r.store.storeNo = :storeNo AND r.reservationDate = :reservationDate AND r.reservationTime = :reservationTime")
    Reservation findByStoreNoAndReservationDateAndReservationTime(Integer storeNo, LocalDate reservationDate, LocalTime reservationTime);
}

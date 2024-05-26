package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.ReservedPets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservedPetsRepository extends JpaRepository<ReservedPets, Integer> {
    @Query("SELECT rp FROM ReservedPets rp WHERE rp.reservation.reservationNo = :reservationNumber")
    ReservedPets findByReservationNo(Integer reservationNumber);
}

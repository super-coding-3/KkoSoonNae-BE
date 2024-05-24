package com.kkosoonnae.reservation.service.scheduler;

import com.kkosoonnae.jpa.entity.Reservation;
import com.kkosoonnae.jpa.entity.ReservedPets;
import com.kkosoonnae.jpa.repository.ReservationRepository;
import com.kkosoonnae.jpa.repository.ReservedPetsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReservationStatusScheduler {

    private final ReservationRepository reservationRepository;
    private final ReservedPetsRepository reservedPetsRepository;

    @Scheduled(fixedRate = 60000)  // 1분마다 실행
    @Transactional
    public void updateReservationStatus() {

        LocalTime nowTime = LocalTime.now();

        List<Reservation> reservations = reservationRepository.findAll();

        for (Reservation reservation: reservations) {
            LocalTime reservationTime = reservation.getReservationTime();
            Integer reservationNo = reservation.getReservationNo();
            ReservedPets reservedPets = reservedPetsRepository.findByReservationNo(reservationNo);
            if ((reservationTime.plusHours(3)).isBefore(nowTime)) {
                reservedPets.markAsNotAvailable();
                reservedPetsRepository.save(reservedPets);
            }
        }


    }

}

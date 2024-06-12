package com.kkosoonnae.common.scheduler;

import com.kkosoonnae.jpa.entity.Reservation;
import com.kkosoonnae.jpa.entity.ReservedPets;
import com.kkosoonnae.jpa.repository.ReservationRepository;
import com.kkosoonnae.jpa.repository.ReservedPetsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();

        List<Reservation> reservations = reservationRepository.findAll();

        for (Reservation reservation: reservations) {

            LocalDate reservationDate = reservation.getReservationDate();
            LocalTime reservationTime = reservation.getReservationTime();

            Integer reservationNo = reservation.getReservationNo();

            ReservedPets reservedPets = reservedPetsRepository.findByReservationNo(reservationNo);

            if (reservedPets == null) {
                continue;
            }

            if (((reservationTime.plusHours(3)).isBefore(nowTime) && reservationDate.isEqual(nowDate)) || reservationDate.isBefore(nowDate)) {
                reservedPets.markAsNotAvailable();
                reservedPetsRepository.save(reservedPets);
            }
        }

    }

}

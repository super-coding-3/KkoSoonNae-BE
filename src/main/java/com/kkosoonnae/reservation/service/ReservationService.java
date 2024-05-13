package com.kkosoonnae.reservation.service;

import com.kkosoonnae.jpa.repository.CustomerBasRepository;
import com.kkosoonnae.reservation.dto.ReservationRequest;
import com.kkosoonnae.reservation.dto.ReservationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.kkosoonnae.reservation.service
 * fileName       : ReservationService
 * author         : hagjoon
 * date           : 2024-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10        hagjoon       최초 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationService {

    private final CustomerBasRepository customerBasRepository;

    public ReservationResponse makeReservation(ReservationRequest reservationRequest) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String currentEmail = authentication.getName();
        Integer cstmrNo = customerBasRepository.findCstmrNoByEmail(currentEmail);

        if ( cstmrNo == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        } else  {
            return null;
        }
    }
}

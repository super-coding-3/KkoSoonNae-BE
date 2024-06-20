package com.kkosoonnae.president.reservationmanage.service;

import com.kkosoonnae.jpa.repository.ReservationQueryRepository;
import com.kkosoonnae.jpa.repository.ReservationRepository;
import com.kkosoonnae.jpa.entity.ReservationListResponse;
import com.kkosoonnae.president.reservationmanage.dto.ReservationDtlRs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName    : com.kkosoonnae.president.reservationmanage.service
 * fileName       : ReservationManageService
 * author         : hagjoon
 * date           : 2024-06-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-13        hagjoon       최초 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationManageService {

    private final ReservationRepository reservationRepository;

    private final ReservationQueryRepository query;

    public List<ReservationListResponse> getReservation(String name, String startDate, String endDate, String status) {

        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate now = LocalDate.now();

        LocalDate reservationStartDate = null;
        LocalDate reservationEndDate = null;

        if (startDate == null || startDate.isEmpty()) {
            reservationStartDate = now.withDayOfMonth(1); // 현재 달의 첫 일

        } else {
            reservationStartDate = LocalDate.parse(startDate, formatDate);
        }

        if (endDate == null || endDate.isEmpty()) {
            YearMonth yearMonth = YearMonth.from(now);
            reservationEndDate = yearMonth.atEndOfMonth(); // 현재 달의 마지막 일
        } else {
            reservationEndDate = LocalDate.parse(endDate, formatDate);
        }

        return query.findReservation(name, reservationStartDate, reservationEndDate, status);

    }


    public ReservationDtlRs getReservationDtl(Integer reservationNumber) {
        return query.findReservationDtl(reservationNumber);
    }
}

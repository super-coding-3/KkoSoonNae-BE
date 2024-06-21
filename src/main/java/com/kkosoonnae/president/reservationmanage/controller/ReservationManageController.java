package com.kkosoonnae.president.reservationmanage.controller;

import com.kkosoonnae.jpa.entity.ReservationListResponse;
import com.kkosoonnae.president.reservationmanage.dto.ReservationDtlRs;
import com.kkosoonnae.president.reservationmanage.service.ReservationManageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName    : com.kkosoonnae.president.reservationmanage.controller
 * fileName       : ReservationManageController
 * author         : hagjoon
 * date           : 2024-06-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-13        hagjoon       최초 생성
 */
@RestController
@RequestMapping("/api/president/reservation-manage")
@Tag(name = "ReservationManageController",description = "예약 관리 API 컨트롤러")
@Slf4j
@RequiredArgsConstructor
public class ReservationManageController {

    private final ReservationManageService reservationManageService;

    @Operation(summary = "예약 목록 조회")
    @GetMapping("/list")
    public List<ReservationListResponse> getResultReservation(@RequestParam(value = "name", required = false) String name, @RequestParam String startDate, @RequestParam String endDate, @RequestParam(value = "status", required = false) String status) {
        return reservationManageService.getReservation(name, startDate, endDate, status);
    }

    @Operation(summary = "예약 상세 보기")
    @GetMapping("/{reservationNumber}")
    public ReservationDtlRs getReservationDtl(@PathVariable Integer reservationNumber) {
        return reservationManageService.getReservationDtl(reservationNumber);
    }

}

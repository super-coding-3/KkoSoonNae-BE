package com.kkosoonnae.reservation.controller;

import com.kkosoonnae.reservation.dto.ReservationRequest;
import com.kkosoonnae.reservation.dto.ReservationResponse;
import com.kkosoonnae.reservation.dto.StyleResponse;
import com.kkosoonnae.reservation.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName    : com.kkosoonnae.reservation.controller
 * fileName       : ReservationController
 * author         : hagjoon
 * date           : 2024-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10        hagjoon       최초 생성
 */
@RestController
@RequestMapping("/KkoSoonNae/reservation")
@Tag(name = "ReservationController",description = "예약 하기 API 및 예약 확인 API 컨트롤러")
@Slf4j
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @Operation(summary = "매장 일련번호로 스타일 이름 가져오기")
    @GetMapping("/style-list/{storeNo}")
    public List<StyleResponse> findStyleName(@PathVariable Integer storeNo) {
        return reservationService.findStyleNameByStoreNo(storeNo);
    }

    @Operation(summary = "에약 하기")
    @PostMapping("/make-reservation")
    public ReservationResponse makeReservation(@RequestBody ReservationRequest reservationRequest) {
        return reservationService.makeReservation(reservationRequest);
    }
    
}

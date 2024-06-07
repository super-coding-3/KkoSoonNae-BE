package com.kkosoonnae.reservation.controller;

import com.kkosoonnae.reservation.dto.*;
import com.kkosoonnae.reservation.service.ReservationService;
import com.kkosoonnae.reservation.service.exceptions.InvalidValueException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
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
@RequestMapping("/api/reservation")
@Tag(name = "ReservationController",description = "예약페이지 API 컨트롤러")
@Slf4j
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @Operation(summary = "매장 일련번호로 매장 이름 가져오기")
    @GetMapping("/store-name/{storeNumber}")
    public StoreNameResponse findStoreName(@PathVariable Integer storeNumber) {
        return reservationService.findStoreNameByStoreNo(storeNumber);
    }

    @Operation(summary = "매장 일련번호로 스타일 정보 가져오기")
    @GetMapping("/style-list/{storeNumber}")
    public List<StyleResponse> findStyleName(@PathVariable Integer storeNumber) {
        return reservationService.findStyleNameByStoreNo(storeNumber);
    }

    @Operation(summary = "로그인한 정보로 펫 정보 가져오기")
    @GetMapping("/my-pet")
    public List<PetResponse> findMyPet() {
        return reservationService.findMyPet();
    }

    @Operation(summary = "예약 하기")
    @PostMapping("/make-reservation")
    public ReservationResponse makeReservation(@RequestBody ReservationRequest reservationRequest) {
        return reservationService.makeReservation(reservationRequest);
    }

    @Operation(summary = "예약 확인")
    @GetMapping("/result-reservation/{reservationNumber}")
    public ReservationResultResponse resultReservation(@PathVariable Integer reservationNumber) {
        return reservationService.resultReservation(reservationNumber);
    }
    
}

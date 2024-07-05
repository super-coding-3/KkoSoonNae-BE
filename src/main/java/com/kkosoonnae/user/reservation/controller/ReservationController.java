package com.kkosoonnae.user.reservation.controller;

import com.kkosoonnae.user.reservation.dto.*;
import com.kkosoonnae.user.reservation.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/user/reservation")
@Tag(name = "ReservationController",description = "예약페이지 API 컨트롤러")
@Slf4j
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @Operation(summary = "매장 일련번호로 매장 이름 가져오기")
    @GetMapping("/store-name/{storeNumber}")
    public ResponseEntity<StoreNameResponse> findStoreName(@PathVariable Integer storeNumber) {
        StoreNameResponse storeNameResponse = reservationService.findStoreNameByStoreNo(storeNumber);
        return ResponseEntity.ok().body(storeNameResponse);
    }

    @Operation(summary = "매장 일련번호로 스타일 정보 가져오기")
    @GetMapping("/style-list/{storeNumber}")
    public ResponseEntity<List<StyleResponse>> findStyleName(@PathVariable Integer storeNumber) {
        List<StyleResponse> styleResponses = reservationService.findStyleNameByStoreNo(storeNumber);
        return ResponseEntity.ok().body(styleResponses);
    }

    @Operation(summary = "로그인한 정보로 펫 정보 가져오기")
    @GetMapping("/my-pet")
    public ResponseEntity<List<PetResponse>> findMyPet() {
        List<PetResponse> petResponses = reservationService.findMyPet();
        return ResponseEntity.ok().body(petResponses);
    }

    @Operation(summary = "예약 하기")
    @PostMapping("/make-reservation")
    public ResponseEntity<ReservationResponse> makeReservation(@RequestBody ReservationRequest reservationRequest) {
        ReservationResponse reservationResponse = reservationService.makeReservation(reservationRequest);
        return ResponseEntity.ok().body(reservationResponse);
    }

    @Operation(summary = "예약 확인")
    @GetMapping("/result-reservation/{reservationNumber}")
    public ResponseEntity<ReservationResultResponse> resultReservation(@PathVariable Integer reservationNumber) {
        ReservationResultResponse reservationResultResponse = reservationService.resultReservation(reservationNumber);
        return ResponseEntity.ok().body(reservationResultResponse);
    }
    
}

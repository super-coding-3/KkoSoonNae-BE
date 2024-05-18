package com.kkosoonnae.reservation.controller;

import com.kkosoonnae.reservation.dto.*;
import com.kkosoonnae.reservation.service.ReservationService;
import com.kkosoonnae.reservation.service.exceptions.NotAcceptException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
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
@RequestMapping("/KkoSoonNae/reservation")
@Tag(name = "ReservationController",description = "예약페이지 API 컨트롤러")
@Slf4j
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @Operation(summary = "매장 일련번호로 매장 이름 가져오기")
    @GetMapping("/store-name/{storeNo}")
    public ResponseEntity<?> findStoreName(@PathVariable Integer storeNo) {
        try {
            StoreNameResponse storeNameResponse = reservationService.findStoreNameByStoreNo(storeNo);
            return new ResponseEntity<>(storeNameResponse, HttpStatus.OK);
        } catch (NotFoundException nfe) {
            return new ResponseEntity<>(nfe.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity<>(iae.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }


    @Operation(summary = "매장 일련번호로 스타일 이름 가져오기")
    @GetMapping("/style-list/{storeNo}")
    public ResponseEntity<?> findStyleName(@PathVariable Integer storeNo) {
        try {
            List<StyleResponse> styleResponses = reservationService.findStyleNameByStoreNo(storeNo);
            return new ResponseEntity<>(styleResponses, HttpStatus.OK);
        } catch (NotFoundException nfe) {
            return new ResponseEntity<>(nfe.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity<>(iae.getMessage(), HttpStatus.UNAUTHORIZED);
        }

    }

    @Operation(summary = "로그인한 정보로 펫 정보 가져오기")
    @GetMapping("/my-pet")
    public ResponseEntity<?> findMyPet() {
        try {
            List<PetResponse> petResponses = reservationService.findMyPet();
            return new ResponseEntity<>(petResponses, HttpStatus.OK);
        } catch (NotFoundException nfe) {
            return new ResponseEntity<>(nfe.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity<>(iae.getMessage(), HttpStatus.UNAUTHORIZED);
        }

    }


    @Operation(summary = "에약 하기")
    @PostMapping("/make-reservation")
    public ResponseEntity<?> makeReservation(@RequestBody ReservationRequest reservationRequest) {
        try {
            ReservationResponse reservationResponse = reservationService.makeReservation(reservationRequest);
            return new ResponseEntity<>(reservationResponse, HttpStatus.CREATED);
        } catch (NotFoundException nfe) {
            return new ResponseEntity<>(nfe.getMessage(), HttpStatus.NOT_FOUND);
        } catch (NotAcceptException nae) {
            return new ResponseEntity<>(nae.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity<>(iae.getMessage(), HttpStatus.UNAUTHORIZED);
        }

    }
    
}

package com.kkosoonnae.reservation.service;

import com.kkosoonnae.config.auth.PrincipalDetails;
import com.kkosoonnae.jpa.entity.*;
import com.kkosoonnae.jpa.repository.*;
import com.kkosoonnae.reservation.dto.*;
import com.kkosoonnae.reservation.service.exceptions.InvalidValueException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private final StoreRepository storeRepository;
    private final ReservationRepository reservationRepository;
    private final AvailTimeRepository availTimeRepository;
    private final PetRepository petRepository;
    private final StyleRepository styleRepository;
    private final ReservedPetsRepository reservedPetsRepository;

    public ReservationResponse makeReservation(ReservationRequest reservationRequest) {

        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomerBas customerBas = principalDetails.getCustomerBas();
        String loginId = customerBas.getLoginId();

        if (loginId == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        Integer cstmrNo = customerBasRepository.findCstmrNoByLoginId(loginId);

        CustomerBas cstmrBas = customerBasRepository.findByCstmrNo(cstmrNo);

        Integer storeNo = reservationRequest.getStoreNo();

        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss");

        LocalDate reservationDate = LocalDate.parse(reservationRequest.getReservationDate(), formatDate);
        LocalTime reservationTime = LocalTime.parse(reservationRequest.getReservationTime(),formatTime);


        Reservation isAvailable = reservationRepository.findByStoreNoAndReservationDateAndReservationTime(storeNo, reservationDate, reservationTime);

        if (isAvailable == null) {
            Integer availNo = availTimeRepository.findByStoreNo(storeNo);
            AvailTime availTime = availTimeRepository.findAvailTimeByStoreNo(storeNo);
            Reservation reservationByCstmrNo = reservationRepository.findByCstmrNo(cstmrNo);
            Store store = storeRepository.findById(reservationRequest.getStoreNo()).orElseThrow(() -> new NotFoundException("선택하신 매장을 찾을 수 없습니다."));


            Pet pet = petRepository.findByCstmrNoAndPetNo(cstmrNo, reservationRequest.getPetNo());

            if (pet == null) {
                throw new NotFoundException("해당 펫을 찾을 수 없습니다.");
            }

            Reservation reservation = new Reservation(store, availTime, cstmrBas, reservationRequest);
            reservationRepository.save(reservation);

            ReservationResponse reservationResponse = new ReservationResponse(store.getStoreName(), reservation, reservationRequest.getStyleName(), pet);

            return reservationResponse;

            } else {
                throw new InvalidValueException("해당 날짜와 시간에는 이미 예약이 있습니다.");
            }

    }


    public List<StyleResponse> findStyleNameByStoreNo(Integer storeNo) {

        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomerBas customerBas = principalDetails.getCustomerBas();
        String loginId = customerBas.getLoginId();

        if (loginId == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        List<Style> styles = styleRepository.findStylNameByStoreNo(storeNo);

        if (styles == null || styles.isEmpty()) {
            throw new NotFoundException("스타일을 찾을 수 없습니다.");
        } else {
            return StyleResponse.stylesToStyleResponse(styles);
        }

    }


    public StoreNameResponse findStoreNameByStoreNo(Integer storeNo) {

        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomerBas customerBas = principalDetails.getCustomerBas();
        String loginId = customerBas.getLoginId();

        if (loginId == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        Store store = storeRepository.findStoreNameByStoreNo(storeNo);

        if (store == null) {
            throw new NotFoundException("매장 이름을 찾을 수 없습니다.");
        } else {
            StoreNameResponse storeNameResponse = new StoreNameResponse(store.getStoreName());
            return storeNameResponse;
        }

    }

    public List<PetResponse> findMyPet() {
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomerBas customerBas = principalDetails.getCustomerBas();
        String loginId = customerBas.getLoginId();

        if (loginId == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        Integer cstmrNo = customerBasRepository.findCstmrNoByLoginId(loginId);

        List<Pet> pets = petRepository.findByCstmrNo(cstmrNo);

        if (pets == null || pets.isEmpty()) {
            throw new NotFoundException("등록된 펫 정보가 없습니다.");
        } else {
            return PetResponse.petsToPetResponse(pets);
        }

    }
}

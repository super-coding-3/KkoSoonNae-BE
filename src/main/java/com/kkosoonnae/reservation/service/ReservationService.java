package com.kkosoonnae.reservation.service;

import com.kkosoonnae.config.auth.PrincipalDetails;
import com.kkosoonnae.jpa.entity.*;
import com.kkosoonnae.jpa.repository.*;
import com.kkosoonnae.reservation.dto.*;
import com.kkosoonnae.reservation.service.exceptions.InvalidValueException;
import com.kkosoonnae.reservation.service.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;

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
    private final ReservedPetsRepository reservedPetsRepository;
    private final StyleRepository styleRepository;
    private final CustomerAvailRepository customerAvailRepository;

    @Transactional
    public ReservationResponse makeReservation(ReservationRequest reservationRequest) {
        String loginId = null;

        try {
            PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            CustomerBas customerBas = principalDetails.getCustomerBas();
            loginId = customerBas.getLoginId();
        } catch (Exception e) {
            throw new AccessDeniedException("로그인이 필요합니다.");
        }

        Integer cstmrNo = customerBasRepository.findCstmrNoByLoginId(loginId);
        CustomerBas cstmrBas = customerBasRepository.findByCstmrNo(cstmrNo);
        Integer storeNo = reservationRequest.getStoreNumber();

        AvailTime availTime = availTimeRepository.findAvailTimeByStoreNo(storeNo);
        Store store = storeRepository.findById(storeNo).orElseThrow(() -> new NotFoundException("선택하신 매장을 찾을 수 없습니다."));

        if (!Objects.equals(store.getStoreName(), reservationRequest.getStoreName())) {
            throw new NotFoundException("해당 매장 일련번호의 매장 이름이 아닙니다.");
        }

        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm");

        LocalDate nowD = LocalDate.now();
        LocalTime nowT = LocalTime.now();

        String nowDateString = nowD.format(formatDate);
        String nowTimeString = nowT.format(formatTime);

        //
        LocalDate nowDate = LocalDate.parse(nowDateString, formatDate);
        LocalTime nowTime = LocalTime.parse(nowTimeString, formatTime);

        //
        LocalDate reservationDate;
        LocalTime reservationTime;

        try {
            reservationDate = LocalDate.parse(reservationRequest.getReservationDate(), formatDate);
            reservationTime = LocalTime.parse(reservationRequest.getReservationTime(), formatTime);
        } catch (Exception e) {
            throw new InvalidValueException("요청하신 날짜 또는 시간 형식이 올바르지 않습니다.");
        }

        if ((reservationDate.isBefore(nowDate)) || (reservationDate.isEqual(nowDate)) && !reservationTime.isAfter(nowTime.plusHours(2))) {
            throw new InvalidValueException("해당 시간은 예약이 불가합니다. 2시간 전에는 미리 예약해야 합니다.");
        }

        Reservation isAvailable = reservationRepository.findByStoreNoAndReservationDateAndReservationTime(storeNo, reservationDate, reservationTime);

        if (isAvailable != null) {
            throw new InvalidValueException("해당 날짜와 시간에는 이미 예약이 있습니다.");
        }

        Style style = styleRepository.findByStoreNoAndStyleName(storeNo, reservationRequest.getCutStyle());

        if (style == null) {
            throw new NotFoundException("해당 스타일을 찾을 수 없습니다.");
        }

        Pet pet = petRepository.findByCstmrNoAndPetNo(cstmrNo, reservationRequest.getPetName());

        if (pet == null) {
            throw new NotFoundException("해당 펫을 찾을 수 없습니다.");
        }

        Reservation reservation = new Reservation(store, availTime, cstmrBas, reservationRequest);
        reservationRepository.save(reservation);

        CustomerAvail customerAvail = new CustomerAvail(cstmrBas, reservation, reservation.getAvail().getAvailNo());
        customerAvailRepository.save(customerAvail);

        ReservedPets reservedPets = new ReservedPets(reservation, pet, availTime);
        reservedPetsRepository.save(reservedPets);

        return new ReservationResponse(store.getStoreName(), reservation, reservationRequest.getCutStyle(), pet, reservation.getReservationNo());
    }

    public List<StyleResponse> findStyleNameByStoreNo(Integer storeNo) {
        String loginId = null;

        try {
            PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            CustomerBas customerBas = principalDetails.getCustomerBas();
            loginId = customerBas.getLoginId();
        } catch (Exception e) {
            throw new AccessDeniedException("로그인이 필요합니다.");
        }

        List<Style> styles = styleRepository.findStylNameByStoreNo(storeNo);

        if (styles == null || styles.isEmpty()) {
            throw new NotFoundException("해당 매장의 스타일을 찾을 수 없습니다.");
        }

        return StyleResponse.stylesToStyleResponse(styles);
    }

    public StoreNameResponse findStoreNameByStoreNo(Integer storeNo) {
        String loginId = null;

        try {
            PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            CustomerBas customerBas = principalDetails.getCustomerBas();
            loginId = customerBas.getLoginId();
        } catch (Exception e) {
            throw new AccessDeniedException("로그인이 필요합니다.");
        }

        Store store = storeRepository.findStoreNameByStoreNo(storeNo);

        if (store == null) {
            throw new NotFoundException("매장 이름을 찾을 수 없습니다.");
        }

        return new StoreNameResponse(store.getStoreName());
    }

    public List<PetResponse> findMyPet() {
        String loginId = null;

        try {
            PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            CustomerBas customerBas = principalDetails.getCustomerBas();
            loginId = customerBas.getLoginId();
        } catch (Exception e) {
            throw new AccessDeniedException("로그인이 필요합니다.");
        }

        Integer cstmrNo = customerBasRepository.findCstmrNoByLoginId(loginId);
        List<Pet> pets = petRepository.findByCstmrNo(cstmrNo);

        if (pets == null || pets.isEmpty()) {
            throw new NotFoundException("등록된 펫 정보가 없습니다.");
        }

        return PetResponse.petsToPetResponse(pets);
    }

    public ReservationResultResponse resultReservation(Integer reservationNumber) {
//        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        CustomerBas customerBas = principalDetails.getCustomerBas();
//        String loginId = customerBas.getLoginId();
//
//        if (loginId == null) {
//            throw new IllegalArgumentException("로그인이 필요합니다.");
//        }
        String loginId = null;

        try {
            PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            CustomerBas customerBas = principalDetails.getCustomerBas();
            loginId = customerBas.getLoginId();
        } catch (Exception e) {
            throw new AccessDeniedException("로그인이 필요합니다.");
        }

        Integer cstmrNo = customerBasRepository.findCstmrNoByLoginId(loginId);
        Reservation reservation = reservationRepository.findById(reservationNumber).orElseThrow(() -> new NotFoundException("해당 예약을 찾을 수 없습니다."));

        String storeName = reservation.getStore().getStoreName();
        String styleName = reservation.getStyleName();
        String feature = reservation.getFeature();

        ReservedPets reservedPets = reservedPetsRepository.findByReservationNo(reservationNumber);
        if (reservedPets == null) {
            throw new NotFoundException("해당 펫은 예약에 등록되지 않았습니다.");
        }
        Pet pet = petRepository.findByPetNo(reservedPets.getPet().getPetNo());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        LocalDate date = reservation.getReservationDate();

        String dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN);
        String fromatterDate = date.format(formatter);
        String responseDate = fromatterDate + "(" + dayOfWeek + ")";

        return new ReservationResultResponse(reservation, storeName, pet, responseDate);
    }

}

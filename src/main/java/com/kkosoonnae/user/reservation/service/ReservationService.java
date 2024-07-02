package com.kkosoonnae.user.reservation.service;

import com.kkosoonnae.common.exception.CustomException;
import com.kkosoonnae.common.exception.ErrorCode;
import com.kkosoonnae.common.exception.InvalidValueException;
import com.kkosoonnae.common.exception.NotFoundException;
import com.kkosoonnae.config.auth.PrincipalDetails;
import com.kkosoonnae.jpa.entity.*;
import com.kkosoonnae.jpa.repository.*;
import com.kkosoonnae.user.reservation.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.text.NumberFormat;
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

    private final ReservationQueryRepository query;

    private static final long LOCK_EXPIRE_TIME = 10000L;  // 10초.

    @Transactional
    public ReservationResponse makeReservation(ReservationRequest reservationRequest) {
        String loginId = getLoginId();
        CustomerBas cstmrBas = getCustomerBas(loginId);
        Integer storeNo = reservationRequest.getStoreNumber();

        String lockKey = "reservation:" + storeNo + ":" + reservationRequest.getReservationDate() + ":" + reservationRequest.getReservationTime();

//        if (redisLockService.lock(lockKey, LOCK_EXPIRE_TIME)) {
//            try {
                AvailTime availTime = getAvailTime(storeNo);
                Store store = getStore(storeNo, reservationRequest.getStoreName());

                LocalDate reservationDate = LocalDate.parse(reservationRequest.getReservationDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalTime reservationTime = LocalTime.parse(reservationRequest.getReservationTime(), DateTimeFormatter.ofPattern("HH:mm"));

                validateDateTime(reservationDate, reservationTime);
                checkReservationDuplicate(storeNo, reservationDate, reservationTime);

                Style style = validateStyle(storeNo, reservationRequest.getCutStyle());
                String stringPrice = formatPrice(style.getPrice());

                Pet pet = validatePet(cstmrBas.getCstmrNo(), reservationRequest.getPetName());

                Reservation reservation = new Reservation(store, availTime, cstmrBas, reservationRequest);
                query.saveReservation(reservation);

                String responseDate = formatReservationDate(reservation.getReservationDate());

                CustomerAvail customerAvail = new CustomerAvail(cstmrBas, reservation, availTime);
                query.saveCustomerAvail(customerAvail);

                ReservedPets reservedPets = new ReservedPets(reservation, pet, availTime);
                query.saveReservedPets(reservedPets);

                return new ReservationResponse(store.getStoreName(), responseDate, reservation, reservationRequest.getCutStyle(), stringPrice, pet, reservation.getReservationNo());
//            } finally {
//                redisLockService.unlock(lockKey);
//            }
//        } else {
//            throw new InvalidValueException("해당 날짜와 시간에는 이미 예약이 있습니다.");
//        }
    }

    private String getLoginId() {
        try {
            PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            CustomerBas customerBas = principalDetails.getCustomerBas();
            return customerBas.getLoginId();
        } catch (Exception e) {
            throw new CustomException(ErrorCode.USER_NOT_LOGIN);
        }
    }

    private CustomerBas getCustomerBas(String loginId) {
        return query.getCustomerBas(loginId);
    }

    private AvailTime getAvailTime(Integer storeNo) {
        return query.getAvailTime(storeNo);
    }

    private Store getStore(Integer storeNo, String storeName) {
        Store store = query.findStoreById(storeNo);
        if (store == null) {
            throw new CustomException(ErrorCode.STORE_NOT_FOUND);
        }
        if (!Objects.equals(store.getStoreName(), storeName)) {
            throw new CustomException(ErrorCode.NOT_MATCH_STORE_INFO);
        }
        return store;
    }

    private void validateDateTime(LocalDate reservationDate, LocalTime reservationTime) {
        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();

        if (reservationDate.isBefore(nowDate)) {
            throw new CustomException(ErrorCode.UNABLE_DATE_RESERVATION);
        }

        if (reservationDate.isEqual(nowDate) && reservationTime.isBefore(nowTime)) {
            throw new CustomException(ErrorCode.UNABLE_TIME_RESERVATION);
        }

        if (reservationDate.isEqual(nowDate) && nowTime.plusHours(2).isAfter(reservationTime)) {
            throw new CustomException(ErrorCode.UNABLE_NOW_TIME_RESERVATION);
        }
    }

    private Style validateStyle(Integer storeNo, String cutStyle) {
        Style style = query.findByStoreNoAndStyleName(storeNo, cutStyle);
        if (style == null) {
            throw new CustomException(ErrorCode.STYLE_NOT_FOUND);
        }
        return style;
    }

    private Pet validatePet(Integer cstmrNo, String petName) {
        Pet pet = query.findByCstmrNoAndPetNo(cstmrNo, petName);
        if (pet == null) {
            throw new CustomException(ErrorCode.PET_NOT_FOUND);
        }
        return pet;
    }

    private void checkReservationDuplicate(Integer storeNo, LocalDate reservationDate, LocalTime reservationTime) {
        Reservation isAvailable = query.findByStoreNoAndReservationDateAndReservationTime(storeNo, reservationDate, reservationTime);
        if (isAvailable != null) {
            throw new CustomException(ErrorCode.UNABLE_RESERVATION);
        }
    }

    private String formatPrice(Integer price) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.KOREA);
        String formattedPrice = numberFormat.format(price);
        return formattedPrice + "원";
    }

    private String formatReservationDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN);
        String formattedDate = date.format(formatter);
        return formattedDate + "(" + dayOfWeek.charAt(0) + ")";
    }

    public List<StyleResponse> findStyleNameByStoreNo(Integer storeNumber) {
        String loginId = getLoginId();

        List<Style> styles = query.findStylNameByStoreNo(storeNumber);

        if (styles == null || styles.isEmpty()) {
            throw new CustomException(ErrorCode.STORE_STYLE_NOT_FOUND);
        }

        return StyleResponse.stylesToStyleResponse(styles);
    }

    public StoreNameResponse findStoreNameByStoreNo(Integer storeNumber) {
        String loginId = getLoginId();

        Store store = query.findStoreNameByStoreNo(storeNumber);

        if (store == null) {
            throw new CustomException(ErrorCode.STORE_NOT_FOUND);
        }

        return new StoreNameResponse(store.getStoreName());
    }

    public List<PetResponse> findMyPet() {
        String loginId = getLoginId();

        Integer cstmrNo = query.findCstmrNoByLoginId(loginId);
        List<Pet> pets =  query.findByCstmrNo(cstmrNo);

        if (pets == null || pets.isEmpty()) {
            throw new CustomException(ErrorCode.USER_PET_NOT_FOUND);
        }

        return PetResponse.petsToPetResponse(pets);
    }

    public ReservationResultResponse resultReservation(Integer reservationNumber) {
        String loginId = getLoginId();

        Reservation reservation = query.findReservationById(reservationNumber);
        if (reservation == null) {
            throw new CustomException(ErrorCode.RESERVATION_NOT_FOUND);
        }

        Integer storeNo = reservation.getStore().getStoreNo();
        String storeName = reservation.getStore().getStoreName();
        String styleName = reservation.getStyleName();

        Style style = query.findByStoreNoAndStyleName(storeNo, styleName);
        Integer price = style.getPrice();

        NumberFormat numberFormat = NumberFormat.getInstance(Locale.KOREA);
        String formattedPrice = numberFormat.format(price);
        String stringPrice = formattedPrice + "원";

        ReservedPets reservedPets = query.findByReservationNo(reservationNumber);
        if (reservedPets == null) {
            throw new CustomException(ErrorCode.PET_NOT_REGIST_RESERVATION);
        }

        Pet pet = query.findByPetNo(reservedPets.getPet().getPetNo());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        LocalDate date = reservation.getReservationDate();

        String dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN);
        String fromatterDate = date.format(formatter);
        String responseDate = fromatterDate + "(" + dayOfWeek.charAt(0) + ")";

        return new ReservationResultResponse(reservation, storeName, stringPrice, pet, responseDate);
    }

}

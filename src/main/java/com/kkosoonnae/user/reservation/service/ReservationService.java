package com.kkosoonnae.user.reservation.service;

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

    private final CustomerBasRepository customerBasRepository;
    private final StoreRepository storeRepository;
    private final ReservationRepository reservationRepository;
    private final AvailTimeRepository availTimeRepository;
    private final PetRepository petRepository;
    private final ReservedPetsRepository reservedPetsRepository;
    private final StyleRepository styleRepository;
    private final CustomerAvailRepository customerAvailRepository;
    private final RedisLockService redisLockService;

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

        LocalDate nowDate = LocalDate.parse(nowDateString, formatDate);
        LocalTime nowTime = LocalTime.parse(nowTimeString, formatTime);

        LocalDate reservationDate;
        LocalTime reservationTime;

        try {
            reservationDate = LocalDate.parse(reservationRequest.getReservationDate(), formatDate);
            reservationTime = LocalTime.parse(reservationRequest.getReservationTime(), formatTime);
        } catch (Exception e) {
            throw new InvalidValueException("요청하신 날짜 또는 시간 형식이 올바르지 않습니다.");
        }

        if (reservationDate.isBefore(nowDate)) {
            throw new InvalidValueException("해당 날짜는 예약이 불가합니다.");
        }

        if ( reservationDate.isEqual(nowDate) && reservationTime.isBefore(nowTime) ) {
            throw new InvalidValueException("해당 시간은 예약이 불가합니다.");
        }

        int compareOne = nowTime.plusHours(2).compareTo(reservationTime);
        int compareTwo = nowTime.compareTo(reservationTime);

        if ( reservationDate.isEqual(nowDate) && ((compareOne > 0) && (compareTwo < 0)) ) {
            throw new InvalidValueException("2시간 전에는 미리 예약해야 합니다.");
        }

        Reservation isAvailable = reservationRepository.findByStoreNoAndReservationDateAndReservationTime(storeNo, reservationDate, reservationTime);

        if (isAvailable != null) {
            throw new InvalidValueException("해당 날짜와 시간에는 이미 예약이 있습니다.");
        }

        Style style = styleRepository.findByStoreNoAndStyleName(storeNo, reservationRequest.getCutStyle());

        if (style == null) {
            throw new NotFoundException("해당 스타일을 찾을 수 없습니다.");
        }

        Integer price = style.getPrice();

        NumberFormat numberFormat = NumberFormat.getInstance(Locale.KOREA);
        String formattedPrice = numberFormat.format(price);
        String stringPrice = formattedPrice + "원";

        Pet pet = petRepository.findByCstmrNoAndPetNo(cstmrNo, reservationRequest.getPetName());

        if (pet == null) {
            throw new NotFoundException("해당 펫을 찾을 수 없습니다.");
        }

        Reservation reservation = new Reservation(store, availTime, cstmrBas, reservationRequest);
        reservationRepository.save(reservation);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        LocalDate date = reservation.getReservationDate();

        String dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN);
        String fromatterDate = date.format(formatter);
        String responseDate = fromatterDate + "(" + dayOfWeek.charAt(0) + ")";

        CustomerAvail customerAvail = new CustomerAvail(cstmrBas, reservation, availTime);
        customerAvailRepository.save(customerAvail);

        ReservedPets reservedPets = new ReservedPets(reservation, pet, availTime);
        reservedPetsRepository.save(reservedPets);

        return new ReservationResponse(store.getStoreName(), responseDate, reservation, reservationRequest.getCutStyle(), stringPrice, pet, reservation.getReservationNo());
    }

    private static final long LOCK_EXPIRE_TIME = 10000L;  // 10 seconds

//    @Transactional
//    public ReservationResponse makeReservation(ReservationRequest reservationRequest) {
//        String loginId = getLoginId();
//        CustomerBas cstmrBas = getCustomerBas(loginId);
//        Integer storeNo = reservationRequest.getStoreNumber();
//
//        String lockKey = "reservation:" + storeNo + ":" + reservationRequest.getReservationDate() + ":" + reservationRequest.getReservationTime();
//
//        if (redisLockService.lock(lockKey, LOCK_EXPIRE_TIME)) {
//            try {
//                AvailTime availTime = getAvailTime(storeNo);
//                Store store = getStore(storeNo, reservationRequest.getStoreName());
//
//                LocalDate reservationDate = LocalDate.parse(reservationRequest.getReservationDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//                LocalTime reservationTime = LocalTime.parse(reservationRequest.getReservationTime(), DateTimeFormatter.ofPattern("HH:mm"));
//
//                validateDateTime(reservationDate, reservationTime);
//                checkReservationDuplicate(storeNo, reservationDate, reservationTime);
//
//                Style style = validateStyle(storeNo, reservationRequest.getCutStyle());
//                String stringPrice = formatPrice(style.getPrice());
//
//                Pet pet = validatePet(cstmrBas.getCstmrNo(), reservationRequest.getPetName());
//
//                Reservation reservation = new Reservation(store, availTime, cstmrBas, reservationRequest);
//                reservationRepository.save(reservation);
//
//                String responseDate = formatReservationDate(reservation.getReservationDate());
//
//                CustomerAvail customerAvail = new CustomerAvail(cstmrBas, reservation, availTime);
//                customerAvailRepository.save(customerAvail);
//
//                ReservedPets reservedPets = new ReservedPets(reservation, pet, availTime);
//                reservedPetsRepository.save(reservedPets);
//
//                return new ReservationResponse(store.getStoreName(), responseDate, reservation, reservationRequest.getCutStyle(), stringPrice, pet, reservation.getReservationNo());
//            } finally {
//                redisLockService.unlock(lockKey);
//            }
//        } else {
//            throw new InvalidValueException("해당 날짜와 시간에는 이미 예약이 있습니다.");
//        }
//    }
//
//    private String getLoginId() {
//        try {
//            PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            CustomerBas customerBas = principalDetails.getCustomerBas();
//            return customerBas.getLoginId();
//        } catch (Exception e) {
//            throw new AccessDeniedException("로그인이 필요합니다.");
//        }
//    }
//
//    private CustomerBas getCustomerBas(String loginId) {
//        Integer cstmrNo = customerBasRepository.findCstmrNoByLoginId(loginId);
//        return customerBasRepository.findByCstmrNo(cstmrNo);
//    }
//
//    private AvailTime getAvailTime(Integer storeNo) {
//        return availTimeRepository.findAvailTimeByStoreNo(storeNo);
//    }
//
//    private Store getStore(Integer storeNo, String storeName) {
//        Store store = storeRepository.findById(storeNo).orElseThrow(() -> new NotFoundException("선택하신 매장을 찾을 수 없습니다."));
//        if (!Objects.equals(store.getStoreName(), storeName)) {
//            throw new NotFoundException("해당 매장 일련번호의 매장 이름이 아닙니다.");
//        }
//        return store;
//    }
//
//    private void validateDateTime(LocalDate reservationDate, LocalTime reservationTime) {
//        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm");
//
//        LocalDate nowDate = LocalDate.now();
//        LocalTime nowTime = LocalTime.now();
//
//        if (reservationDate.isBefore(nowDate)) {
//            throw new InvalidValueException("해당 날짜는 예약이 불가합니다.");
//        }
//
//        if (reservationDate.isEqual(nowDate) && reservationTime.isBefore(nowTime)) {
//            throw new InvalidValueException("해당 시간은 예약이 불가합니다.");
//        }
//
//        if (reservationDate.isEqual(nowDate) && nowTime.plusHours(2).compareTo(reservationTime) > 0) {
//            throw new InvalidValueException("2시간 전에는 미리 예약해야 합니다.");
//        }
//    }
//
//    private Style validateStyle(Integer storeNo, String cutStyle) {
//        Style style = styleRepository.findByStoreNoAndStyleName(storeNo, cutStyle);
//        if (style == null) {
//            throw new NotFoundException("해당 스타일을 찾을 수 없습니다.");
//        }
//        return style;
//    }
//
//    private Pet validatePet(Integer cstmrNo, String petName) {
//        Pet pet = petRepository.findByCstmrNoAndPetNo(cstmrNo, petName);
//        if (pet == null) {
//            throw new NotFoundException("해당 펫을 찾을 수 없습니다.");
//        }
//        return pet;
//    }
//
//    private void checkReservationDuplicate(Integer storeNo, LocalDate reservationDate, LocalTime reservationTime) {
//        Reservation isAvailable = reservationRepository.findByStoreNoAndReservationDateAndReservationTime(storeNo, reservationDate, reservationTime);
//        if (isAvailable != null) {
//            throw new InvalidValueException("해당 날짜와 시간에는 이미 예약이 있습니다.");
//        }
//    }
//
//    private String formatPrice(Integer price) {
//        NumberFormat numberFormat = NumberFormat.getInstance(Locale.KOREA);
//        String formattedPrice = numberFormat.format(price);
//        return formattedPrice + "원";
//    }
//
//    private String formatReservationDate(LocalDate date) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//        String dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN);
//        String formattedDate = date.format(formatter);
//        return formattedDate + "(" + dayOfWeek.charAt(0) + ")";
//    }
//
    public List<StyleResponse> findStyleNameByStoreNo(Integer storeNumber) {
        String loginId = null;

        try {
            PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            CustomerBas customerBas = principalDetails.getCustomerBas();
            loginId = customerBas.getLoginId();
        } catch (Exception e) {
            throw new AccessDeniedException("로그인이 필요합니다.");
        }

        List<Style> styles = styleRepository.findStylNameByStoreNo(storeNumber);

        if (styles == null || styles.isEmpty()) {
            throw new NotFoundException("해당 매장의 스타일을 찾을 수 없습니다.");
        }

        return StyleResponse.stylesToStyleResponse(styles);
    }

    public StoreNameResponse findStoreNameByStoreNo(Integer storeNumber) {
        String loginId = null;

        try {
            PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            CustomerBas customerBas = principalDetails.getCustomerBas();
            loginId = customerBas.getLoginId();
        } catch (Exception e) {
            throw new AccessDeniedException("로그인이 필요합니다.");
        }

        Store store = storeRepository.findStoreNameByStoreNo(storeNumber);

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

        Integer storeNo = reservation.getStore().getStoreNo();
        String storeName = reservation.getStore().getStoreName();
        String styleName = reservation.getStyleName();
        String feature = reservation.getFeature();

        Style style = styleRepository.findByStoreNoAndStyleName(storeNo, styleName);
        Integer price = style.getPrice();

        NumberFormat numberFormat = NumberFormat.getInstance(Locale.KOREA);
        String formattedPrice = numberFormat.format(price);
        String stringPrice = formattedPrice + "원";

        ReservedPets reservedPets = reservedPetsRepository.findByReservationNo(reservationNumber);
        if (reservedPets == null) {
            throw new NotFoundException("해당 펫은 예약에 등록되지 않았습니다.");
        }
        Pet pet = petRepository.findByPetNo(reservedPets.getPet().getPetNo());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        LocalDate date = reservation.getReservationDate();

        String dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN);
        String fromatterDate = date.format(formatter);
        String responseDate = fromatterDate + "(" + dayOfWeek.charAt(0) + ")";

        return new ReservationResultResponse(reservation, storeName, stringPrice, pet, responseDate);
    }

}

package com.kkosoonnae.reservation.service;

import com.kkosoonnae.config.auth.PrincipalDetails;
import com.kkosoonnae.jpa.entity.*;
import com.kkosoonnae.jpa.repository.*;
import com.kkosoonnae.reservation.dto.ReservationRequest;
import com.kkosoonnae.reservation.dto.ReservationResponse;
import com.kkosoonnae.reservation.dto.StyleResponse;
import com.kkosoonnae.reservation.service.exceptions.InvalidValueException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
//        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
//        SecurityContextHolder.getContext().setAuthentication(authentication);

        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomerBas customerBas = principalDetails.getCustomerBas();

        String loginId = customerBas.getLoginId();
        Integer cstmrNo = customerBasRepository.findCstmrNoByLoginId(loginId);

        CustomerBas cstmrBas = customerBasRepository.findCstmrBasByLoginId(loginId);

        if ( cstmrNo == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        } else  {
            Integer storeNo = reservationRequest.getStoreNo();
            LocalDate reservationDate = LocalDate.parse(reservationRequest.getReservationDate());
            LocalTime reservationTime = LocalTime.parse(reservationRequest.getReservationTime());
            boolean isAvailable = reservationRepository.existsByStoreNoAndDateAndTime(storeNo, reservationDate, reservationTime);

            if (!isAvailable) {
                Integer availNo = availTimeRepository.findByStoreNo(storeNo);
                AvailTime availTime = availTimeRepository.findAvailTimeByStoreNoAndReservationDateReservationTime(storeNo, reservationDate, reservationTime);
//                AvailTime availTime = availTimeRepository.find
                Reservation reservationByCstmrNo = reservationRepository.findByCstmrNo(cstmrNo);
                Store store = storeRepository.findById(reservationRequest.getStoreNo()).orElseThrow(() -> new NotFoundException("선택하신 매장을 찾을 수 없습니다."));
//                Style style = styleRepository.findById(reservationRequest.getStyleNo()).orElseThrow(() -> new NotFoundException("요청하신 스타일이 없습니다."));
//                Style style = styleRepository.findStylNameByStoreNo(storeNo, reservationRequest.getStyleNo());
                Pet pet = petRepository.findByCustomerNoAndPetNo(cstmrNo, reservationRequest.getPetNo());

                Reservation reservation = new Reservation(store, availTime, cstmrBas, reservationRequest);
                reservationRepository.save(reservation);

                ReservedPets reservedPets = new ReservedPets(reservationByCstmrNo.getReservationNo(), pet, availTime);
                reservedPetsRepository.save(reservedPets);

                ReservationResponse reservationResponse = new ReservationResponse(store.getStoreName(), reservation, reservationRequest.getStyleName(), pet);

                return reservationResponse;

            } else {
                throw new InvalidValueException("해당 날짜와 시간에는 이미 예약이 있습니다.");
            }
        }
    }


    public List<StyleResponse> findStyleNameByStoreNo(Integer storeNo) {
        List<Style> styles = styleRepository.findStylNameByStoreNo(storeNo);
        return StyleResponse.stylesToStyleResponse(styles);
    }


}

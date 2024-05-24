package com.kkosoonnae.mypage.service;

import com.kkosoonnae.config.auth.PrincipalDetails;
import com.kkosoonnae.jpa.repository.CustomerAvailRepository;
import com.kkosoonnae.jpa.repository.CustomerBasRepository;
import com.kkosoonnae.jpa.repository.CustomerQueryRepository;
import com.kkosoonnae.jpa.repository.ReservationRepository;
import com.kkosoonnae.mypage.dto.AvailDto;
import com.kkosoonnae.mypage.dto.LikeStoreDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * packageName    : com.kkosoonnae.mypage.service
 * fileName       : MyPageService
 * author         : hagjoon
 * date           : 2024-05-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-23        hagjoon       최초 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MyPageService {

    private final CustomerQueryRepository query;


    public List<AvailDto> getMyAvailList(PrincipalDetails principalDetails){
        Integer cstmrNo = principalDetails.getCustomerBas().getCstmrNo();

        List<AvailDto> dto = query.availList(cstmrNo);

        if(dto == null || dto.isEmpty()){
            return Collections.emptyList();
        }
        return dto;
    }

    @Transactional
    public void deleteAvail(Integer cstmrNo,Integer reservationNo){
        // 예약을 조회하고 해당 회원의 예약인지 확인
        boolean exists = query.existsByCstmrNoAndReservationNo(cstmrNo, reservationNo);
        if (!exists) {
            throw new IllegalArgumentException("해당 회원의 예약이 존재하지 않습니다.");
        }
        query.deleteAvail(reservationNo);
    }

    public List<LikeStoreDto> getMyLikeStore(PrincipalDetails principalDetails){
        Integer cstmrNo = principalDetails.getCustomerBas().getCstmrNo();

        List<LikeStoreDto> dto = query.likeList(cstmrNo);

        if(dto == null || dto.isEmpty()){
            return Collections.emptyList();
        }
        return dto;
    }

    @Transactional
    public void deleteLike(Integer cstmrNo, Integer likeNo){
        boolean exists = query.existsByCstmrNoAndLikeNo(cstmrNo, likeNo);

        if(!exists){
            throw new IllegalArgumentException("해당 회원의 관심 매장 정보가 없습니다.");
        }
        query.deleteLike(likeNo);

    }
}

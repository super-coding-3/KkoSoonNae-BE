package com.kkosoonnae.point.service;

import com.kkosoonnae.config.auth.PrincipalDetails;
import com.kkosoonnae.jpa.entity.CustomerBas;
import com.kkosoonnae.jpa.repository.PointQueryRepository;
import com.kkosoonnae.jpa.repository.PointRepository;
import com.kkosoonnae.point.dto.PointDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.kkosoonnae.point.service
 * fileName       : PointService
 * author         : hagjoon
 * date           : 2024-05-20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-20        hagjoon       최초 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PointService {

    private final PointRepository pointRepository;

    private final PointQueryRepository query;

    public PointDto getMyPoint(PrincipalDetails principalDetails){
        Integer customerBas = principalDetails.getCustomerBas().getCstmrNo();

           PointDto dto = query.getMyPoint(customerBas);

       return dto;
    }
}

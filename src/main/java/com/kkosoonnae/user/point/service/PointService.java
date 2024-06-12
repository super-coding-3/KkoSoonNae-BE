package com.kkosoonnae.user.point.service;

import com.kkosoonnae.jpa.repository.PointQueryRepository;
import com.kkosoonnae.jpa.repository.PointRepository;
import com.kkosoonnae.user.point.dto.PointDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public PointDto getMyPoint(Integer cstmrNo){

           PointDto dto = query.getMyPoint(cstmrNo);

           if(dto == null){
               dto = new PointDto();
               dto.setTitle("잔여 포인트");
               dto.setPointRm(0);
           }

       return dto;
    }
}

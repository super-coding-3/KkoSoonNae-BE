package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.QPoint;
import com.kkosoonnae.point.dto.PointDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * packageName    : com.kkosoonnae.jpa.repository
 * fileName       : PointQueryRepository
 * author         : hagjoon
 * date           : 2024-05-20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-20        hagjoon       최초 생성
 */
@RequiredArgsConstructor
@Repository
@Slf4j
public class PointQueryRepository {

    private final JPAQueryFactory query;

    public PointDto getMyPoint(Integer cstmrNo){
        QPoint point = QPoint.point;

        return query.select(Projections.bean(PointDto.class,
                point.title,
                point.pointRm
                ))
                .from(point)
                .where(point.customerBas.cstmrNo.eq(cstmrNo))
                .fetchOne();
    }
}

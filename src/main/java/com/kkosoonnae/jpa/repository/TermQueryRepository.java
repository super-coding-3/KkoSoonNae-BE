package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.QTerm;
import com.kkosoonnae.user.term.dto.TermRsDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName    : com.kkosoonnae.jpa.repository
 * fileName       : TermQueryRepository
 * author         : hagjoon
 * date           : 2024-06-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-25        hagjoon       최초 생성
 */
@RequiredArgsConstructor
@Repository
@Slf4j
public class TermQueryRepository {

    private final JPAQueryFactory query;

    public List<TermRsDto> list(){
        QTerm term = QTerm.term;

        return query
                .select(Projections.bean(TermRsDto.class,
                        term.termName,
                        term.termContent,
                        term.termFlag
                        ))
                .from(term)
                .fetch();
    }
}

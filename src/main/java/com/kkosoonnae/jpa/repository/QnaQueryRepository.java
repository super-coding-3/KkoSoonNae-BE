package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.QCustomerBas;
import com.kkosoonnae.jpa.entity.QPet;
import com.kkosoonnae.jpa.entity.QQna;
import com.kkosoonnae.pet.dto.PetInfoDto;
import com.kkosoonnae.qna.dto.QnaListDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName    : com.kkosoonnae.jpa.repository
 * fileName       : PetQueryRepositroy
 * author         : hagjoon
 * date           : 2024-05-16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-16        hagjoon       최초 생성
 */
@RequiredArgsConstructor
@Repository
@Slf4j
public class QnaQueryRepository {

    private final JPAQueryFactory query;

    public List<QnaListDto> listQna(Integer cstmrNo){
        QQna qna = QQna.qna;

        return query
                .select(Projections.bean(QnaListDto.class,
                        qna.qnaNo,
                        qna.title,
                        qna.content,
                        qna.createDt
                ))
                .from(qna)
                .where(qna.cstmrNo.cstmrNo.eq(cstmrNo))
                .fetch();
    }

    public QnaListDto findQnaInfoById(Integer qnaNo){
        QQna qna = QQna.qna;

        return query
                .select(Projections.bean(QnaListDto.class,
                    qna.title,
                    qna.content,
                    qna.createDt
                ))
                .from(qna)
                .where(qna.qnaNo.eq(qnaNo))
                .fetchOne();
    }
}

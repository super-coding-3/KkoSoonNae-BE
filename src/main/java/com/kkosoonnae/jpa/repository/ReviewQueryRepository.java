package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.QReview;
import com.kkosoonnae.president.reviewmanage.dto.ReviewManageRs;
import com.kkosoonnae.user.review.dto.ReviewResponseDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class ReviewQueryRepository {

    private final JPAQueryFactory query;
    private final EntityManager entityManager;

    private static final Logger log = LoggerFactory.getLogger(ReviewQueryRepository.class);

    public List<ReviewResponseDto> findReviews(Integer reviewNo, LocalDateTime reviewStartDate, LocalDateTime reviewEndDate) {
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(review.reviewDt.between(reviewStartDate, reviewEndDate));

        if (reviewNo != null) {
            builder.and(review.reviewNo.eq(reviewNo));
        }

        return query.select(Projections.constructor(
                        ReviewRepository.class,
                        review.reviewNo,
                        review.cstmrNo,
                        review.reviewDt
                ))
                .from(review)
                .where(builder)
                .fetch();
    }

    public ReviewManageRs findReviewDetail(Integer reviewNo) {
        QReview review = QReview.review;

        return query.select(Projections.constructor(
                        ReviewManageRs.class,
                        review.reviewNo,
                        review.cstmrNo,
                        review.reviewDt
                ))
                .from(review)
                .where(review.reviewNo.eq(reviewNo))
                .fetchOne();
    }
}


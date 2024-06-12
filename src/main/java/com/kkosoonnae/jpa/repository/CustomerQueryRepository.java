package com.kkosoonnae.jpa.repository;


import com.kkosoonnae.jpa.entity.*;
import com.kkosoonnae.user.mypage.dto.AvailDto;
import com.kkosoonnae.user.mypage.dto.LikeStoreDto;
import com.kkosoonnae.user.mypage.dto.MyReviewDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.querydsl.core.types.dsl.Expressions.numberTemplate;
import static org.hibernate.internal.util.StringHelper.coalesce;
import static org.hibernate.internal.util.StringHelper.count;

/**
 * packageName    : com.kkosoonnae.jpa.repository
 * fileName       : CustomerQueryRepository
 * author         : hagjoon
 * date           : 2024-05-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-23        hagjoon       최초 생성
 */
@RequiredArgsConstructor
@Repository
@Slf4j
public class CustomerQueryRepository {

    private final JPAQueryFactory query;

    private final EntityManager entityManager;

    public List<AvailDto> availList(Integer cstmrNo) {
        QCustomerAvail customerAvail = QCustomerAvail.customerAvail;
        QReservation reservation = QReservation.reservation;
        QStyle style = QStyle.style;
        QStore store = QStore.store;
        QStoreImg storeImg = QStoreImg.storeImg;

        // 서브쿼리로 최소 storeImgNo를 가진 storeImg의 img를 가져오기
        QStoreImg subStoreImg = new QStoreImg("subStoreImg");
        JPQLQuery<Integer> minImgNoQuery = JPAExpressions
                .select(subStoreImg.storeImgNo.min())
                .from(subStoreImg)
                .where(subStoreImg.store.storeNo.eq(store.storeNo));

        return query.select(Projections.fields(AvailDto.class,
                        reservation.reservationNo.as("reservationNo"),
                        reservation.reservationDate.as("reservationDate"),
                        reservation.reservationTime.as("reservationTime"),
                        reservation.reservationStatus.as("reservationStatus"),
                        store.storeName.as("storeName"),
                        storeImg.img.as( "storeImg"), // 서브쿼리를 사용하여 첫 번째 이미지 가져오기
                        reservation.styleName.as("styleName"),
                        style.price.as("price")
                ))
                .from(customerAvail)
                .leftJoin(reservation)
                .on(customerAvail.reservationNo.reservationNo.eq(reservation.reservationNo))
                .leftJoin(store)
                .on(reservation.store.storeNo.eq(store.storeNo))
                .leftJoin(style)
                .on(store.storeNo.eq(style.store.storeNo)
                        .and(style.styleName.eq(reservation.styleName)))
                .leftJoin(storeImg)
                .on(store.storeNo.eq(storeImg.store.storeNo)
                        .and(storeImg.storeImgNo.eq(minImgNoQuery)))
                .where(customerAvail.cstmrNo.cstmrNo.eq(cstmrNo))
                .fetch();
    }





    public void deleteAvail(Integer reservationNo){
        QCustomerAvail customerAvail = QCustomerAvail.customerAvail;
        QReservation reservation = QReservation.reservation;
        QReservedPets reservedPets = QReservedPets.reservedPets;

        long customerAvailDelete = query.delete(customerAvail)
                .where(customerAvail.reservationNo.reservationNo.eq(reservationNo))
                .execute();

        long reservedPetsDelete = query.delete(reservedPets)
                .where(reservedPets.reservation.reservationNo.eq(reservationNo))
                .execute();

        long reservationDelete = query.delete(reservation)
                .where(reservation.reservationNo.eq(reservationNo))
                .execute();

    }

    // 회원 번호와 예약 번호로 예약이 존재하는지 확인
    public boolean existsByCstmrNoAndReservationNo(Integer cstmrNo, Integer reservationNo) {
        QCustomerAvail customerAvail = QCustomerAvail.customerAvail;
        return query.selectFrom(customerAvail)
                .where(customerAvail.cstmrNo.cstmrNo.eq(cstmrNo)
                        .and(customerAvail.reservationNo.reservationNo.eq(reservationNo)))
                .fetch().size() > 0;
    }

    public List<LikeStoreDto> likeList(Integer cstmrNo){
        QLikeStore likeStore = QLikeStore.likeStore;
        QStore store = QStore.store;
        QStoreImg storeImg = QStoreImg.storeImg;
        QReview review = QReview.review;

        QLikeStore subLikeStore = new QLikeStore("subLikeStore");

        // 서브쿼리로 최소 storeImgNo를 가진 storeImg를 선택
        QStoreImg subStoreImg = new QStoreImg("subStoreImg");
        JPQLQuery<Integer> minImgNoQuery = JPAExpressions
                .select(subStoreImg.storeImgNo.min())
                .from(subStoreImg)
                .where(subStoreImg.store.storeNo.eq(store.storeNo));

        NumberExpression<Integer> likeCountExpression = numberTemplate(
                Integer.class,
                "coalesce({0}, 0L)",
                JPAExpressions
                        .select(subLikeStore.store.storeNo.count())
                        .from(subLikeStore)
                        .where(subLikeStore.store.storeNo.eq(store.storeNo))
                        .groupBy(subLikeStore.store.storeNo));

        NumberExpression<Double> scopeExpression = review.scope.avg().coalesce(0.0);

        return query.select(Projections.fields(LikeStoreDto.class,
                        likeStore.likeNo.as("likeNo"),
                        store.storeNo.as("storeNo"),
                        storeImg.img.as("storeImg"),
                        store.storeName.as("storeName"),
                        scopeExpression.as("scope"),
                        likeCountExpression.coalesce(0).intValue().as("totalLikeCount"),
                        store.roadAddress.as("roadAddress"),
                        store.openingTime.as("openTime"),
                        store.closingTime.as("closeTime")
                ))
                .from(likeStore)
                .leftJoin(store)
                .on(likeStore.store.storeNo.eq(store.storeNo))
                .leftJoin(storeImg)
                .on(store.storeNo.eq(storeImg.store.storeNo)
                        .and(storeImg.storeImgNo.eq(minImgNoQuery))) // 최소 imgNo에 해당하는 이미지만 선택
                .leftJoin(review)
                .on(store.storeNo.eq(review.store.storeNo))
                .where(likeStore.customerBas.cstmrNo.eq(cstmrNo))
                .groupBy(likeStore.likeNo,
                        likeStore.store.storeNo,
                        storeImg.img)
                .fetch();
    }


    //회원 번호와 관심 번호로 관심매장을 등록했는지 확인
    public boolean existsByCstmrNoAndLikeNo(Integer cstmrNo, Integer likeNo){
        QLikeStore likeStore = QLikeStore.likeStore;
        return query.selectFrom(likeStore)
                .where(likeStore.customerBas.cstmrNo.eq(cstmrNo)
                        .and(likeStore.likeNo.eq(likeNo)))
                .fetch().size() > 0;
    }


    public void deleteLike(Integer likeNo){
        QLikeStore likeStore = QLikeStore.likeStore;

       long deleteLike = query.delete(likeStore)
                .where(likeStore.likeNo.eq(likeNo))
                .execute();
    }

    public List<MyReviewDto> getMyReview(Integer cstmrNo){
        QReview review = QReview.review;
        QStore store = QStore.store;

        return query.select(Projections.bean(MyReviewDto.class,
                review.reviewNo,
                store.storeNo,
                store.storeName,
                review.scope,
                review.img,
                review.content,
                review.reviewDt
                ))
                .from(review)
                .leftJoin(store)
                .on(review.store.storeNo.eq(store.storeNo))
                .where(review.cstmrNo.cstmrNo.eq(cstmrNo))
                .fetch();
    }


//    //회원 번호와 리뷰 번호로 내가 쓴 리뷰 확인
    public boolean existByCstmrNoAndReviewNo(Integer cstmrNo, Integer reviewNo){
        QReview review = QReview.review;
        return query.selectFrom(review)
                .where(review.cstmrNo.cstmrNo.eq(cstmrNo)
                        .and(review.reviewNo.eq(reviewNo)))
                .fetch().size() > 0;
    }

    public void deleteReview(Integer reviewNo){
        QReview review = QReview.review;

        long deleteReview = query.delete(review)
                .where(review.reviewNo.eq(reviewNo))
                .execute();
    }


}

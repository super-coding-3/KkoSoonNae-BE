package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.*;
import com.kkosoonnae.user.search.dto.MainStoreListViewResponseDto;
import com.kkosoonnae.user.search.dto.StoreListViewResponseDto;
import com.kkosoonnae.user.store.dto.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.kkosoonnae.jpa.entity.QStoreImg.storeImg;


@RequiredArgsConstructor
@Repository
public class StoreQueryRepository {

    private final JPAQueryFactory queryFactory;
    @PersistenceContext
    private EntityManager entityManager;

    public List<MainStoreListViewResponseDto> listViewResponseDto(String addressKeyword, Pageable pageable) {
        QStore store = QStore.store;

        BooleanExpression keywordExpression = store.roadAddress.containsIgnoreCase(addressKeyword);

        return queryFactory.select(
                        Projections.bean(MainStoreListViewResponseDto.class,
                                store.storeNo.as("storeNo"),
                                store.storeName.as("storeName"),
                                store.roadAddress.as("roadAddress")

                        ))
                .from(store)
                .where(keywordExpression)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

    }

    public List<StoreListViewResponseDto> findStoreListQuery(String nameAddressKeyword) {
        QStore store = QStore.store;
        QStoreImg storeImg = QStoreImg.storeImg;

        BooleanBuilder whereClause = new BooleanBuilder();

        if (!StringUtils.isEmpty(nameAddressKeyword)) {
            whereClause.and(store.storeName.contains(nameAddressKeyword)
                    .or(whereClause.and(store.roadAddress.contains(nameAddressKeyword))));
        }

        List<StoreListViewResponseDto> storeList = queryFactory
                .selectFrom(store)
                .leftJoin(storeImg).on(store.storeNo.eq(storeImg.store.storeNo))
                .where(whereClause)
                .transform(GroupBy.groupBy(store.storeNo)
                        .list(Projections.constructor(StoreListViewResponseDto.class,
                                store.storeNo, store.storeName, store.roadAddress,
                                GroupBy.list(Projections.constructor(StoreImgDto.class, storeImg.img)))));

        return storeList;

    }

    public List<StoreReviewsResponseDto> reviewListQuery(Integer storeNo) {
        QStore store = QStore.store;
        QReview review = QReview.review;
        QCustomerBas customerBas = QCustomerBas.customerBas;
        QCustomerDtl customerDtl = QCustomerDtl.customerDtl;
        QPet pet = QPet.pet;

        return queryFactory
                .select(new QStoreReviewsResponseDto(
                        store.storeNo,
                        store.storeName,
                        review.reviewNo,
                        review.cstmrNo.cstmrNo,
                        review.content,
                        review.reviewDt,
                        review.scope,
                        customerDtl.nickName,
                        pet.img,
                        pet.mainPet))
                .from(store)
                .leftJoin(review).on(store.storeNo.eq(review.store.storeNo))
                .leftJoin(customerBas).on(review.cstmrNo.cstmrNo.eq(customerBas.cstmrNo))
                .leftJoin(customerDtl).on(customerBas.cstmrNo.eq(customerDtl.cstmrNo))
                .leftJoin(pet).on(customerDtl.cstmrNo.eq(pet.customerBas.cstmrNo)
                        .and(pet.mainPet.eq("Y")))
                .where(store.storeNo.eq(storeNo))
                .fetch();
    }

    public StoreDetailWithImageResponseDto findStoreDetail(Integer storeNo) {
        QStore store = QStore.store;
        QStoreImg storeImg = QStoreImg.storeImg;


        List<String> images = queryFactory
                .select(storeImg.img)
                .from(storeImg)
                .where(storeImg.store.storeNo.eq(storeNo))
                .fetch();

        StoreDetailWithImageResponseDto result = queryFactory
                .select(new QStoreDetailWithImageResponseDto(
                        store.storeNo, store.storeName, store.content,
                        store.phone, store.roadAddress, store.openingTime, store.closingTime,
                        Expressions.constant(images)))
                .from(store)
                .leftJoin(storeImg).on(store.storeNo.eq(storeImg.store.storeNo))
                .where(store.storeNo.eq(storeNo))
                .groupBy(store.storeNo,store.storeName,store.content,store.phone,
                        store.roadAddress,store.openingTime,store.closingTime)
                .fetchOne();
        return result;

    }
}







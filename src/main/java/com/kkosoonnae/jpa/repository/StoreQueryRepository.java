package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.*;
import com.kkosoonnae.user.search.dto.MainStoreListViewResponseDto;
import com.kkosoonnae.user.search.dto.StoreListViewResponseDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.query.sqm.tree.SqmNode.log;

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

    public List<StoreListViewResponseDto> findStoreListQuery(String nameKeyword, String addressKeyword) {
        QStore store = QStore.store;
        QStoreImg storeImg = QStoreImg.storeImg;

        BooleanBuilder whereClause = new BooleanBuilder();

        if (!StringUtils.isEmpty(nameKeyword)) {
            whereClause.and(store.storeName.contains(nameKeyword));
        }
        if (!StringUtils.isEmpty(addressKeyword)) {
            whereClause.and(store.roadAddress.contains(addressKeyword));
        }
        List<StoreListViewResponseDto> storeList = queryFactory
                .select(Projections.constructor(StoreListViewResponseDto.class,
                        store.storeNo,
                        store.storeName,
                        store.roadAddress,
                        Projections.list(storeImg.img)



                        ))
                .from(store)
                .leftJoin(storeImg).on(store.storeNo.eq(storeImg.store.storeNo))
                .where(whereClause)
                //.groupBy(store.storeNo,store.storeName,store.roadAddress)
                .fetch()
                .stream()
                .map(tuple -> new StoreListViewResponseDto(
                        tuple.getStoreNo(),
                        tuple.getStoreName(),
                        tuple.getRoadAddress(),
                        tuple.getImg()
                ))
                .collect(Collectors.toList());

        return storeList;
    }
}






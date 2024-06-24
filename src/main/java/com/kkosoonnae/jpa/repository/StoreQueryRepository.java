package com.kkosoonnae.jpa.repository;

import com.kkosoonnae.jpa.entity.QLikeStore;
import com.kkosoonnae.jpa.entity.QReview;
import com.kkosoonnae.jpa.entity.QStore;
import com.kkosoonnae.jpa.entity.QStoreImg;
import com.kkosoonnae.user.search.dto.MainStoreListViewResponseDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@RequiredArgsConstructor
@Repository
public class StoreQueryRepository {

    private final JPAQueryFactory query;

    public List<MainStoreListViewResponseDto> listViewResponseDto(String addressKeyword, Pageable pageable) {
        QStore store = QStore.store;

        BooleanExpression keywordExpression = store.roadAddress.containsIgnoreCase(addressKeyword);

        return query.select(
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
}

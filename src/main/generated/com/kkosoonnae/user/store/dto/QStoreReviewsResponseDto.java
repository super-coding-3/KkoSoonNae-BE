package com.kkosoonnae.user.store.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.kkosoonnae.user.store.dto.QStoreReviewsResponseDto is a Querydsl Projection type for StoreReviewsResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QStoreReviewsResponseDto extends ConstructorExpression<StoreReviewsResponseDto> {

    private static final long serialVersionUID = -2112781602L;

    public QStoreReviewsResponseDto(com.querydsl.core.types.Expression<Integer> storeNo, com.querydsl.core.types.Expression<String> storeName, com.querydsl.core.types.Expression<Integer> reviewNo, com.querydsl.core.types.Expression<Integer> cstmrNo, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<java.time.LocalDateTime> reviewDt, com.querydsl.core.types.Expression<Integer> scope, com.querydsl.core.types.Expression<String> nickName, com.querydsl.core.types.Expression<String> img, com.querydsl.core.types.Expression<String> mainPet) {
        super(StoreReviewsResponseDto.class, new Class<?>[]{int.class, String.class, int.class, int.class, String.class, java.time.LocalDateTime.class, int.class, String.class, String.class, String.class}, storeNo, storeName, reviewNo, cstmrNo, content, reviewDt, scope, nickName, img, mainPet);
    }

}


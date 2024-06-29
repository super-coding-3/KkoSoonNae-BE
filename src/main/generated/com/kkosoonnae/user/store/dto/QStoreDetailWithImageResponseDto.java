package com.kkosoonnae.user.store.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.kkosoonnae.user.store.dto.QStoreDetailWithImageResponseDto is a Querydsl Projection type for StoreDetailWithImageResponseDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QStoreDetailWithImageResponseDto extends ConstructorExpression<StoreDetailWithImageResponseDto> {

    private static final long serialVersionUID = 624123253L;

    public QStoreDetailWithImageResponseDto(com.querydsl.core.types.Expression<Integer> storeNo, com.querydsl.core.types.Expression<String> storeName, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<String> phone, com.querydsl.core.types.Expression<String> roadAddress, com.querydsl.core.types.Expression<java.time.LocalTime> openingTime, com.querydsl.core.types.Expression<java.time.LocalTime> closingTime, com.querydsl.core.types.Expression<? extends java.util.List<String>> img) {
        super(StoreDetailWithImageResponseDto.class, new Class<?>[]{int.class, String.class, String.class, String.class, String.class, java.time.LocalTime.class, java.time.LocalTime.class, java.util.List.class}, storeNo, storeName, content, phone, roadAddress, openingTime, closingTime, img);
    }

}


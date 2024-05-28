package com.kkosoonnae.store.dto;

import com.kkosoonnae.jpa.projection.StoreReviewsViewProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreReviewsResponseDto {
    private Integer storeNo;

    private String storeName;

    private String storeImg;

    private Long totalLikeStore;

    private Integer reviewNo;

    private Integer cstmrNo;

    private String content;

    private Integer scope;

    private Double averageScope;

    private String nickName;

    private String petImg;
}


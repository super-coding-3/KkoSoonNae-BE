package com.kkosoonnae.jpa.projection;

import com.kkosoonnae.store.dto.StoreReviewsResponseDto;
import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record StoreReviewsViewProjection(
        Integer storeNo,
        String storeName,
        String storeImg,
        Long totalLikeStore,
        Integer reviewNo,
        Integer cstmrNo,
        String content,
        Integer scope,
        String nickName,
        String petImg

) {
    public StoreReviewsResponseDto toDto(Double averageScope) {
        return StoreReviewsResponseDto.builder()
                .storeNo(storeNo)
                .storeName(storeName)
                .storeImg(storeImg)
                .totalLikeStore(totalLikeStore)
                .reviewNo(reviewNo)
                .cstmrNo(cstmrNo)
                .content(content)
                .scope(scope)
                .averageScope(averageScope)
                .nickName(nickName)
                .petImg(petImg)
                .build();
    }
}

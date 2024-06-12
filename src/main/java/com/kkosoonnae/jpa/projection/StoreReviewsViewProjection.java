package com.kkosoonnae.jpa.projection;

import com.kkosoonnae.user.store.dto.StoreReviewsResponseDto;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record StoreReviewsViewProjection(
        Integer storeNo,
        String storeName,
        Long totalLikeStore,
        Integer reviewNo,
        Integer cstmrNo,
        String content,
        LocalDateTime reviewDt,
        Integer scope,
        String nickName,
        String img,
        String mainPet

) {
    public StoreReviewsResponseDto toDto(Double averageScope) {
        return StoreReviewsResponseDto.builder()
                .storeNo(storeNo)
                .storeName(storeName)
                .totalLikeStore(totalLikeStore)
                .reviewNo(reviewNo)
                .cstmrNo(cstmrNo)
                .content(content)
                .reviewDt(reviewDt)
                .scope(scope)
                .averageScope(averageScope)
                .nickName(nickName)
                .img(img)
                .mainPet(mainPet)
                .build();
    }
}

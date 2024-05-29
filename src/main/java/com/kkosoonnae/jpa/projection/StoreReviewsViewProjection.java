package com.kkosoonnae.jpa.projection;

import com.kkosoonnae.store.dto.StoreReviewsResponseDto;
import lombok.Builder;

@Builder
public record StoreReviewsViewProjection(
        Integer storeNo,
        String storeName,
        Long totalLikeStore,
        Integer reviewNo,
        Integer cstmrNo,
        String content,
        Integer scope,
        String nickName,
        String img,
        String mainPet

) {
    public StoreReviewsResponseDto toDto(Integer averageScope) {
        return StoreReviewsResponseDto.builder()
                .storeNo(storeNo)
                .storeName(storeName)
                .totalLikeStore(totalLikeStore)
                .reviewNo(reviewNo)
                .cstmrNo(cstmrNo)
                .content(content)
                .scope(scope)
                .averageScope(Double.valueOf(averageScope))
                .nickName(nickName)
                .img(img)
                .mainPet(mainPet)
                .build();
    }
}

package com.kkosoonnae.jpa.projection;

import com.kkosoonnae.jpa.entity.StoreImg;
import com.kkosoonnae.search.dto.MainStoreListViewResponseDto;
import lombok.Builder;

import java.util.List;

@Builder
public record MainStoresListviewProjection(
        Integer storeNo,
        String storeName,
        String roadAddress,
        Long totalLikeStore


) {
    public MainStoreListViewResponseDto MainStoreToDto(Double averageScope) {
        return MainStoreListViewResponseDto.builder()
                .storeNo(storeNo)
                .storeName(storeName)
                .roadAddress(roadAddress)
                .totalLikeStore(totalLikeStore)
                .averageScope(averageScope)
                .build();
    }
}


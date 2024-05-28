package com.kkosoonnae.jpa.projection;

import com.kkosoonnae.search.dto.MainStoreListViewResponseDto;
import lombok.Builder;

@Builder
public record MainStoresListviewProjection(
        Integer storeNo,
        String storeName,
        String roadAddress

) {
    public MainStoreListViewResponseDto MainStoreToDto(Double averageScope) {
        return MainStoreListViewResponseDto.builder()
                .storeNo(storeNo)
                .storeName(storeName)
                .roadAddress(roadAddress)
                .averageScope(averageScope)
                .build();


    }
}

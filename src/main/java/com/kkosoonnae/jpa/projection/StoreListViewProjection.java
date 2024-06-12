package com.kkosoonnae.jpa.projection;

import com.kkosoonnae.user.search.dto.StoreListViewResponseDto;
import lombok.Builder;

import java.util.List;

@Builder
public record StoreListViewProjection(
        Integer storeNo,
        String storeName,
        String roadAddress

        // ... (목록 조회 때 보여 줄 필드만 작성)
) {
    public StoreListViewResponseDto toDto(List<String> img,Double averageScope) {
        return StoreListViewResponseDto.builder()
                .storeNo(this.storeNo)
                .storeName(this.storeName)
                .roadAddress(this.roadAddress)
                .img(img)
                .averageScope(averageScope)
                .build();
    }
}


package com.kkosoonnae.jpa.projection;

import com.kkosoonnae.jpa.entity.StoreImg;
import com.kkosoonnae.search.dto.StoreListViewResponseDto;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public record StoreListViewProjection(
        Integer storeNo,
        String storeName,
        String roadAddress,
        Double scope


        // ... (목록 조회 때 보여 줄 필드만 작성)
) {
    public StoreListViewResponseDto toDto(List<String> storeImgList ,Double averageScope) {
        return StoreListViewResponseDto.builder()
                .storeNo(storeNo)
                .storeName(storeName)
                .roadAddress(roadAddress)
                .img(storeImgList)
                .averageScope(averageScope)
                .build();
    }
}

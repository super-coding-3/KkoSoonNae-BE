package com.kkosoonnae.store.dto;

import com.kkosoonnae.jpa.projection.StoreListViewProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreListViewResponseDto {

    private Integer storeNo;
    private String storeName;
    private String img;
    private Integer averageScope;


    public static StoreListViewResponseDto ResponseToEntity(StoreListViewProjection projection) {
        return StoreListViewResponseDto.builder()
                .storeNo(projection.storeNo())
                .storeName(projection.storeName())
                .img(projection.img())
                .averageScope(projection.averageScope())
                .build();
    }
}

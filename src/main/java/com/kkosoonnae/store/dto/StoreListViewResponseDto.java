package com.kkosoonnae.store.dto;

import com.kkosoonnae.jpa.projection.StoreListViewProjection;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreListViewResponseDto {

    private Integer storeNo;
    private String storeName;
    private String img;
    private Double averageScope;
    private Long totalLikeStore;

    public static StoreListViewResponseDto ResponseToEntity(StoreListViewProjection projection) {
        return StoreListViewResponseDto.builder()
                .storeNo(projection.storeNo())
                .storeName(projection.storeName())
                .img(projection.img())
                .averageScope(projection.scope())
                .build();
    }
}

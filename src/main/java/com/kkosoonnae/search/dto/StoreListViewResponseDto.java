package com.kkosoonnae.search.dto;

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
    private String roadAddress;
    private String img;
    private Double averageScope;
   // private Long totalLikeStore;

    public static StoreListViewResponseDto projectToDto(StoreListViewProjection projection) {
        return StoreListViewResponseDto.builder()
                .storeNo(projection.storeNo())
                .storeName(projection.storeName())
                .roadAddress(projection.roadAddress())
                .img(projection.img())
                .averageScope(projection.scope())
                .build();
    }
}

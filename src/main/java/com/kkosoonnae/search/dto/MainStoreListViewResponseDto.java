package com.kkosoonnae.search.dto;

import com.kkosoonnae.jpa.projection.MainStoresListviewProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MainStoreListViewResponseDto {
    @Schema(description = "매장 번호")
    private Integer storeNo;
    @Schema(description = "매장 이름")
    private String storeName;
    @Schema(description = "매장 주소")
    private String roadAddress;
    @Schema(description = "평균 별점")
    private Double averageScope;

    public static MainStoreListViewResponseDto mainStoresDto (MainStoresListviewProjection projection) {
        return MainStoreListViewResponseDto.builder()
                .storeNo(projection.storeNo())
                .storeName(projection.storeName())
                .roadAddress(projection.roadAddress())
                .averageScope(projection.scope())
                .build();
    }

}

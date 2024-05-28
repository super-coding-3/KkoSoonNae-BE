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
    @Schema(description = "총점")
    private Double averageScope;


    }


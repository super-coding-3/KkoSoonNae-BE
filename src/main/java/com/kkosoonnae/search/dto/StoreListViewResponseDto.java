package com.kkosoonnae.search.dto;

import com.kkosoonnae.jpa.entity.Store;
import com.kkosoonnae.jpa.entity.StoreImg;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreListViewResponseDto {

    @Schema(description = "매장 번호")
    private Integer storeNo;
    @Schema(description = "매장 이름")
    private String storeName;
    @Schema(description = "매장 주소")
    private String roadAddress;
    @Schema(description = "매장 이미지")
    private List<String> img;
    @Schema(description = "총점")
    private Double averageScope;

    public StoreListViewResponseDto(Store store, double averageScope) {
        this.storeNo =store.getStoreNo();
        this.storeName =store.getStoreName();
        this.roadAddress =store.getRoadAddress();
        this.img =store.getStoreImg()
                .stream()
                .map(StoreImg::getImg).collect(Collectors.toList());
        this.averageScope = averageScope;
    }
}


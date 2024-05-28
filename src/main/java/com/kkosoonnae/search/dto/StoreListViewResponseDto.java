package com.kkosoonnae.search.dto;

import com.kkosoonnae.jpa.projection.StoreListViewProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

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
    @Schema(description = "평균 별점")
    private Double averageScope;
   // private Long totalLikeStore;

//    public static StoreListViewResponseDto projectToDto(StoreListViewProjection projection) {
//        return StoreListViewResponseDto.builder()
//                .storeNo(projection.storeNo())
//                .storeName(projection.storeName())
//                .roadAddress(projection.roadAddress())
//                .img(projection.img())
//                .averageScope(projection.scope())
//                .build();
//    }
}

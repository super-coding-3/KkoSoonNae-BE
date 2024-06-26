package com.kkosoonnae.user.search.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Data
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

    @Schema(description = "매장 관심수")
    private Long totalLikeStore;

    @Schema(description = "총점")
    private Double averageScope;




    public MainStoreListViewResponseDto mainStoreToDto(Long totalLikeStore,double averageScope) {
        return MainStoreListViewResponseDto.builder()
                .storeNo(this.storeNo)
                .storeName(this.storeName)
                .roadAddress(this.roadAddress)
                .totalLikeStore(totalLikeStore)
                .averageScope(averageScope)
                .build();
    }
}

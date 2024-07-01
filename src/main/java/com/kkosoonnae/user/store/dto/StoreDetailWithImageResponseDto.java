package com.kkosoonnae.user.store.dto;

import com.kkosoonnae.jpa.projection.StoreDetailViewProjection;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreDetailWithImageResponseDto {
    @Schema(description = "매장 번호")
    private Integer storeNo;

    @Schema(description = "매장 이름")
    private String storeName;

    @Schema(description = "매장 설명")
    private String content;

    @Schema(description = "매장 번호 ")
    private String phone;
    @Schema(description = "매장 주소 ")
    private String roadAddress;

    @Schema(description = "오픈시간")
    private LocalTime openingTime;

    @Schema(description = "마감시간")
    private LocalTime closingTime;

    @Schema(description = "매장이미지")
    private List<String> img;

    @Schema(description = "총점")
    private Double averageScope;

    @Schema(description = "관심수")
    private Long totalLikeStore;

    @QueryProjection
    public StoreDetailWithImageResponseDto(Integer storeNo, String storeName, String content, String phone, String roadAddress, LocalTime openingTime, LocalTime closingTime, List<String> img) {
        this.storeNo = storeNo;
        this.storeName = storeName;
        this.content = content;
        this.phone = phone;
        this.roadAddress = roadAddress;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.img = img;
    }

    public StoreDetailWithImageResponseDto DetailStoreToDto(double  averageScope, Long totalLikeStore) {
        return StoreDetailWithImageResponseDto.builder()
                .storeNo(this.storeNo)
                .storeName(this.storeName)
                .content(this.content)
                .phone(this.phone)
                .roadAddress(this.roadAddress)
                .openingTime(this.openingTime)
                .closingTime(this.closingTime)
                .img(this.img)
                .averageScope(averageScope)
                .totalLikeStore(totalLikeStore)
                .build();
    }
}






package com.kkosoonnae.user.search.dto;

import com.kkosoonnae.jpa.entity.Store;
import com.kkosoonnae.user.store.dto.StoreImsDto;
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
    private List<StoreImsDto> img;
    @Schema(description = "매장 관심수")
    private Long totalLikeStore;
    @Schema(description = "총점")
    private Double averageScope;

    //주석된 부분 나중에 완전히 로직바뀌면 삭제할게요
//    public StoreListViewResponseDto(Store store, double averageScope) {
//        this.storeNo =store.getStoreNo();
//        this.storeName =store.getStoreName();
//        this.roadAddress =store.getRoadAddress();
//        this.img =store.getStoreImg()
//                .stream()
//                .map(StoreImg::getImg).collect(Collectors.toList());
//        this.averageScope = averageScope;
//    }

    public StoreListViewResponseDto StoreListToDto(Store store) {
        return StoreListViewResponseDto.builder()
                .storeNo(store.getStoreNo())
                .storeName(store.getStoreName())
                .roadAddress(store.getRoadAddress())
                .build();
    }
    public StoreListViewResponseDto ListToDto(double averageScope,Long totalLikeStore) {
        return StoreListViewResponseDto.builder()
                .storeNo(this.storeNo)
                .storeName(this.storeName)
                .roadAddress(this.roadAddress)
                .img(img)
                .averageScope(averageScope)
                .totalLikeStore(totalLikeStore)
                .build();
    }

    public StoreListViewResponseDto(Integer storeNo, String storeName, String roadAddress, List<StoreImsDto> img) {
        this.storeNo = storeNo;
        this.storeName = storeName;
        this.roadAddress = roadAddress;
        this.img = img;
    }
}



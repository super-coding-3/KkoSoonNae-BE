package com.kkosoonnae.president.mystore.dto;

import com.kkosoonnae.jpa.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminStoreResponseDto {
    private Integer storeNo;
    private String storeName;
    private String content;
    private String phone;
    private Double lat;
    private Double lon;
    private String roadAddress;
    private LocalTime openingTime;
    private LocalTime closingTime;


    public AdminStoreResponseDto storeFromDto(Store store) {
        return AdminStoreResponseDto.builder()
                .storeNo(store.getStoreNo())
                .storeName(store.getStoreName())
                .content(store.getContent())
                .phone(store.getPhone())
                .lat(store.getLat())
                .lon(store.getLon())
                .roadAddress(store.getRoadAddress())
                .openingTime(store.getOpeningTime())
                .closingTime(store.getClosingTime())
                .build();
    }
}

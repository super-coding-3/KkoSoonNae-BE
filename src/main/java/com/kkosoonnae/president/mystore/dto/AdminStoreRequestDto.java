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
public class AdminStoreRequestDto {
    private String storeName;
    private String content;
    private String phone;
    private Double lat;
    private Double lon;
    private String roadAddress;
    private LocalTime openingTime;
    private LocalTime closingTime;


    public Store toEntity() {
        return Store.builder()
                .storeName(this.storeName)
                .content(this.content)
                .phone(this.phone)
                .lat(this.lat)
                .lon(this.lon)
                .roadAddress(this.roadAddress)
                .openingTime(this.openingTime)
                .closingTime(this.closingTime)
                .build();

    }
    public AdminStoreRequestDto storeFromDto(Store store) {
        return AdminStoreRequestDto.builder()
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
    public void updateEntity(Store store) {
        store.setStoreName(this.storeName);
        store.setContent(this.content);
        store.setPhone(this.phone);
        store.setLat(this.lat);
        store.setLon(this.lon);
        store.setRoadAddress(this.roadAddress);
        store.setOpeningTime(this.openingTime);
        store.setClosingTime(this.closingTime);

    }

}


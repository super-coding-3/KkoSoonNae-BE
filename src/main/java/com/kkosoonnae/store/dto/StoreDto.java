package com.kkosoonnae.store.dto;

import com.kkosoonnae.jpa.entity.Store;
import com.kkosoonnae.jpa.entity.StoreImg;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * packageName    : com.kkosoonnae.store.dto
 * fileName       : StoreDto
 * author         : hagjoon
 * date           : 2024-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10        hagjoon       최초 생성
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreDto {
    private Integer storeNo;

    private String storeName;

    private String content;

    private String phone;

    private String roadAddress;

    private Double lat;

    private Double lon;

    private LocalTime openingTime;

    private LocalTime closingTime;


    public StoreDto(Store store) {
        this.storeNo=store.getStoreNo();
        this.storeName=store.getStoreName();
        this.phone=store.getPhone();
        this.content=store.getContent();
        this.roadAddress=store.getRoadAddress();
        this.lat= store.getLat();
        this.lon= store.getLon();
        this.openingTime=store.getOpeningTime();
        this.closingTime=store.getClosingTime();

    }
}





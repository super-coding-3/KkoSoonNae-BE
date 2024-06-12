package com.kkosoonnae.user.store.dto;

import com.kkosoonnae.jpa.entity.Store;
import lombok.*;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

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

    private String petImg;

    private List<StyleDto> style;

    private double averageReviewScore;

    private String latestReviewMessage;
    
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
//        this.storeImg=store.getStoreImg()
//                .stream()
//                .map(StoreImg::getImg).collect(Collectors.toList());
        this.style=store.getStyle().stream().map(StyleDto::new).collect(Collectors.toList());
    }

    public StoreDto(Store store,double averageReviewScore, String latestReviewMessage,String mainPetImage) {
        this.storeNo=store.getStoreNo();
        this.storeName=store.getStoreName();
        this.phone=store.getPhone();
        this.content=store.getContent();
        this.roadAddress=store.getRoadAddress();
        this.lat= store.getLat();
        this.lon= store.getLon();
        this.openingTime=store.getOpeningTime();
        this.closingTime=store.getClosingTime();
        this.petImg=mainPetImage;
        this.style=store.getStyle().stream().map(StyleDto::new).collect(Collectors.toList());
        this.averageReviewScore = averageReviewScore;
        this.latestReviewMessage = latestReviewMessage;
    }
}





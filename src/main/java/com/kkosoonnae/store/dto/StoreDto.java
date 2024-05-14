package com.kkosoonnae.store.dto;

import lombok.*;

import java.time.LocalDateTime;

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

    private String zipCode;

    private String address;

    private String addressDtl;

    private String phone;

    private LocalDateTime storeOperDt;

    private String roadAddress;


}


    //    @Builder
//    public StoreDto(
//            Integer storeNo,
//            String storeName,
//            String content,
//            String zipcode,
//            String address,
//            String addressDtl,
//            String phone,
//            LocalDateTime storeOperDt,
//            String roadAddress
//) {
//        this.storeNo = storeNo;
//        this.storeName = storeName;
//        this.content = content;
//        this.zipCode = zipcode;
//        this.address = address;
//        this.addressDtl = addressDtl;
//        this.phone = phone;
//        this.storeOperDt = storeOperDt;
//        this.roadAddress = roadAddress;
//}
//public Store toEntity(Instant storeOperDt) {
//    LocalDateTime convertedCreateAt = LocalDateTime.ofInstant(
//            storeOperDt,
//            ZoneId.of("/Asia/seoul")
//    );
//
//    return Store.builder()
//            .storeName(getStoreName())
//            .content(getContent())
//            .zipCode(getZipCode())
//            .address(getAddress())
//            .addressDtl(getAddressDtl())
//            .phone(getPhone())
//            .storeOperDt(convertedCreateAt)
//            .roadAddress(getRoadAddress())
//            .build();
//    }
//    public static StoreDto StoreEntity(Store store) {
//        return StoreDto.builder()
//                .storeNo(store.getStoreNo())
//                .storeName(store.getStoreName())
//                .content(store.getContent())
//                .zipCode(store.getZipCode())
//                .address(store.getAddress())
//                .addressDtl(store.getAddressDtl())
//                .phone(store.getPhone())
//                .storeOperDt(store.getStoreOperDt())
//                .roadAddress(store.getRoadAddress())
//                .build();
//    }
//}




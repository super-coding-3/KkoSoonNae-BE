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





package com.kkosoonnae.map.dto;

import com.kkosoonnae.jpa.entity.Store;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NearByStore {
    @Schema(description = "매장 이름")
    private String storeName;

    @Schema(description = "매장설명")
    private String content;

    @Schema(description = "우편주소")
    private String zipCode;

    @Schema(description = "지번 주소")
    private String address;

    @Schema(description = "전화번호")
    private String phone;

    @Schema(description = "도로명 주소")
    private String roadAddress;

    @Schema(description = "별점")
    private String scope;

    @Schema(description = "매장이미지")
    private String img;

}

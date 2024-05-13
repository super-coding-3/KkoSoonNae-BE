package com.kkosoonnae.map.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NearByStore {
    @Schema(description = "매장 이름")
    private String placeName;

    @Schema(description = "매장 거리")
    private String distance;

    @Schema(description = "주소")
    private String addressName;

    @Schema(description = "도로명 주소")
    private String roadAddressName;

    @Schema(description = "전화번호")
    private String phone;

    @Schema(description = "경도")
    private String longitude;

    @Schema(description = "위도")
    private String latitude;
}

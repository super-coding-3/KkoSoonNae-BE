package com.kkosoonnae.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InputStoreInformation {
    private String storeName;

    private String phone;

    private String roadAddress;

    private Double lat;

    private Double lon;

}

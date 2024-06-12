package com.kkosoonnae.user.store.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class InputStoreInformation {
    private String storeName;

    private String phone;

    private String roadAddress;

    private Double lat;

    private Double lon;

    private List<String> storeImgUrl;


}

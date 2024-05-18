package com.kkosoonnae.map.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetailInfo {
    private String scope;
    private String address;
    private String time;
    private String phone;
    private String roadAddress;
}

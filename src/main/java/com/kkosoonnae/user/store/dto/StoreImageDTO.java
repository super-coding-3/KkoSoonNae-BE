package com.kkosoonnae.user.store.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor

public class StoreImageDTO {
    private List<String> newImageUrls;

    public StoreImageDTO(List<String> newImageUrls) {
        this.newImageUrls = newImageUrls;
    }
}

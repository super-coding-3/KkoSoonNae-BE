package com.kkosoonnae.user.store.dto;

import com.kkosoonnae.jpa.entity.Store;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllStore {
    private Integer storeNo;
    private String storeName;


    public AllStore(Store store){
        this.storeNo=store.getStoreNo();
        this.storeName=store.getStoreName();
    }
}

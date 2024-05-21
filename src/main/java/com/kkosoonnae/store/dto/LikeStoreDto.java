package com.kkosoonnae.store.dto;

import com.kkosoonnae.jpa.entity.LikeStore;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeStoreDto {
    private Integer likeNo;
    private Integer cstmrNo;
    private Integer storeNo;
    private LocalDateTime createDt;

    public static LikeStoreDto mapToListStoreDto(LikeStore likeStore) {
        return LikeStoreDto.builder()
                .likeNo(likeStore.getLikeNo())
                .cstmrNo(likeStore.getCustomerBas().getCstmrNo())
                .storeNo(likeStore.getStore().getStoreNo())
                .createDt(likeStore.getCreateDt())
                .build();
    }
    public static LikeStoreDto likeToEntity(Optional<LikeStore> likeStore) {
        return likeStore.map( like-> LikeStoreDto.builder()
                        .likeNo(like.getLikeNo())
                        .cstmrNo(like.getCustomerBas().getCstmrNo())
                        .storeNo(like.getStore().getStoreNo())
                        .createDt(like.getCreateDt())
                        .build())
                .orElseThrow(null);
    }
}


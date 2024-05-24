package com.kkosoonnae.store.service;

import com.kkosoonnae.jpa.entity.Store;
import com.kkosoonnae.search.dto.StoreListViewResponseDto;
import com.kkosoonnae.store.dto.*;

import java.util.List;

public interface StoreService  {
    StoreDetailWithImageResponseDto findStoreDetailWithImage (Integer storeNo);
    List<StyleDto> findStyles (Integer storeNo);
   // List<StoreListViewResponseDto> findByStores (String storeKeyword, String addressKeyword);
    LikeStoreDto saveLikeStore(Integer customerNo,Integer storeNo);
    //관심매장삭제
    void deleteLikeStore(Integer customerNo, Integer storeNo);

    ReviewResponseDto createReview(ReviewDto reviewDto);

    StoreDto createStore(InputStoreInformation inputStoreInformation);

    List<StoreDto> findStores(double lat, double lon);

    void writeReview(Integer cstmrNo, Store storeNo, String content);
}

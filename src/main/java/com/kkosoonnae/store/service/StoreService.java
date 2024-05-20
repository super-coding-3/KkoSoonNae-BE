package com.kkosoonnae.store.service;

import com.kkosoonnae.jpa.entity.CustomerBas;
import com.kkosoonnae.jpa.entity.Store;
import com.kkosoonnae.store.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StoreService  {
    StoreDetailWithImageResponseDto findStoreDetailWithImage (Integer storeNo);
    List<StyleDto> findStyles (Integer storeNo);
    List<StoreListViewResponseDto> findByStores (String storeKeyword,String addressKeyword);
    LikeStoreDto saveLikeStore(Integer customerNo,Integer storeNo);

//    LikeStoreDto deleteSave(Integer customerNo,Integer storeNo);

    //관심매장삭제
    void deleteLikeStore(Integer customerNo, Integer storeNo);

    ReviewResponseDto createReview(ReviewDto reviewDto);
}

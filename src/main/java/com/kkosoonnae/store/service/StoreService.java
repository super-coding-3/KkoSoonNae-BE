package com.kkosoonnae.store.service;

import com.kkosoonnae.jpa.entity.StoreImg;
import com.kkosoonnae.jpa.entity.Style;
import com.kkosoonnae.store.dto.StoreDetailViewResponseDto;
import com.kkosoonnae.store.dto.StoreDetailWithImageResponseDto;
import com.kkosoonnae.store.dto.StoreDto;
import com.kkosoonnae.store.dto.StyleDto;

import java.util.List;
import java.util.Optional;

public interface StoreService  {
    StoreDetailWithImageResponseDto findStoreDetailWithImage (Integer storeNo);

    List<StyleDto> findStyles (Integer storeNo);


}

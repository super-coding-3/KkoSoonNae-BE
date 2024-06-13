package com.kkosoonnae.president.mystore.service;

import com.kkosoonnae.common.exception.CustomException;
import com.kkosoonnae.common.exception.DuplicateStoreNameException;
import com.kkosoonnae.common.exception.ErrorCode;
import com.kkosoonnae.jpa.entity.Store;
import com.kkosoonnae.jpa.repository.StoreRepository;
import com.kkosoonnae.president.mystore.dto.AdminStoreRequestDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.kkosoonnae.president.mystore.service
 * fileName       : MyStoreService
 * author         : hagjoon
 * date           : 2024-06-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-13        hagjoon       최초 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MyStoreService {
    private final StoreRepository storeRepository;
    public AdminStoreRequestDto createStore(AdminStoreRequestDto adminStoreRequestDto) {
        Store storeEntity = adminStoreRequestDto.toEntity();
        if (storeRepository.existsByStoreName(storeEntity.getStoreName())) {
            log.error("해당 매장이름이 존재합니다.매장이름:{}", storeEntity.getStoreName());
        throw new CustomException(ErrorCode.STORE_SAME_NAME);
    }
        Store saveStore = storeRepository.save(storeEntity);
        return adminStoreRequestDto.storeFromDto(saveStore);

    }
}

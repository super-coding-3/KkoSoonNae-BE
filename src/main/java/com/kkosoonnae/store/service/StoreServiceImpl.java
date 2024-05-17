package com.kkosoonnae.store.service;

import com.kkosoonnae.jpa.entity.Review;
import com.kkosoonnae.jpa.entity.Store;
import com.kkosoonnae.jpa.entity.StoreImg;
import com.kkosoonnae.jpa.entity.Style;
import com.kkosoonnae.jpa.projection.StoreDetailViewProjection;
import com.kkosoonnae.jpa.projection.StoreListViewProjection;
import com.kkosoonnae.jpa.repository.ReviewRepository;
import com.kkosoonnae.jpa.repository.StoreImgRepository;
import com.kkosoonnae.jpa.repository.StoreRepository;
import com.kkosoonnae.jpa.repository.StyleRepository;
import com.kkosoonnae.store.dto.StoreDetailWithImageResponseDto;
import com.kkosoonnae.store.dto.StoreListViewResponseDto;
import com.kkosoonnae.store.dto.StyleDto;
import com.kkosoonnae.store.exception.CustomException;
import com.kkosoonnae.store.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * packageName    : com.kkosoonnae.store.service
 * fileName       : StoreService
 * author         : hagjoon
 * date           : 2024-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10        hagjoon       최초 생성
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    private final StoreImgRepository storeImgRepository;

    private final StyleRepository styleRepository;

    private final ReviewRepository reviewRepository;

    @Override
    public StoreDetailWithImageResponseDto findStoreDetailWithImage(Integer storeNo) {
        Optional<StoreDetailViewProjection> storeDetailViewProjection = storeRepository.findStoreByStoreNo(storeNo);
        if (storeDetailViewProjection.isEmpty()) {
            throw new CustomException(ErrorCode.STORE_NOT_FOUND);
        }
        Optional<StoreImg> storeImg = storeImgRepository.findByStoreNo(storeNo);

        if (storeImg.isEmpty()) {
            throw new CustomException(ErrorCode.STORE_IMAGE_NOT_FOUND);
        }
        return new StoreDetailWithImageResponseDto(storeDetailViewProjection.get(), storeImg.get());
    }

    @Override
    public List<StyleDto> findStyles(Integer storeNo) {
        if (storeNo == null) {
            throw new CustomException(ErrorCode.STORE_NOT_FOUND);
        }
        List<Style> styles = styleRepository.findByStoreNo(storeNo);
        if (styles.isEmpty()) {
            throw new CustomException(ErrorCode.STYLE_NOT_FOUND);
        }
        return StyleDto.styleToEntity(styles);
    }

    @Override
    public List<StoreListViewResponseDto> findByStores(String storeKeyword, String addressKeyword) {
        try {
            List<StoreListViewProjection> projection = storeRepository.findStoresByStoreNameInAndAddressInOrderByAddressAsc(storeKeyword, addressKeyword);
            Integer averageScope = calculateAverageScope();
            return projection.stream()
                    .map(storeProjection -> {
                        StoreListViewResponseDto dto = StoreListViewResponseDto.ResponseToEntity(storeProjection);
                        dto.setAverageScope(averageScope);
                        return dto;
                    })
                    .collect(Collectors.toList());
        } catch (CustomException e) {
            throw new CustomException(ErrorCode.STORE_NOT_FOUND);
        }
    }
        private Integer calculateAverageScope () {
            List<Review> reviews = reviewRepository.findAll();
            if (reviews.isEmpty()) {
                return 0;
            }
            int totalScope = reviews.stream().mapToInt(Review::getScope).sum();
            return totalScope / reviews.size();
        }

    @Override
    public Page<StoreListViewResponseDto> findAllWithPageable(String nameKeyword, String addressKeyword, Pageable pageable) {
    Page<StoreListViewProjection> projections = storeRepository.findAllByStoreNameLikeOrAddressLike(nameKeyword,addressKeyword,pageable);
     return  projections.map(StoreListViewResponseDto::ResponseToEntity);
    }
}






package com.kkosoonnae.store.service;

import com.kkosoonnae.jpa.entity.*;
import com.kkosoonnae.jpa.projection.StoreDetailViewProjection;
import com.kkosoonnae.jpa.projection.StoreListViewProjection;
import com.kkosoonnae.jpa.repository.*;
import com.kkosoonnae.store.dto.*;
import com.kkosoonnae.store.exception.CustomException;
import com.kkosoonnae.store.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    private final StyleRepository styleRepository;

    private final ReviewRepository reviewRepository;

    private final LikeStoreRepository likeStoreRepository;

    private final CustomerBasRepository customerBasRepository;

    //매장상세조회
    @Override
    public StoreDetailWithImageResponseDto findStoreDetailWithImage(Integer storeNo) {
        Optional<StoreDetailViewProjection> storeDetailViewProjection = storeRepository.findStoreByStoreNo(storeNo);
        if (storeDetailViewProjection.isEmpty()) {
            throw new CustomException(ErrorCode.STORE_NOT_FOUND);
        }
        Double averageScope = calculateAverageScope();
        Long totalLikeStore = calculateTotalLikeStore(storeNo);
        return new StoreDetailWithImageResponseDto(storeDetailViewProjection.get(),averageScope,totalLikeStore);
    }

    //매장스타일조회
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

    //매장전체조회
    @Override
    public List<StoreListViewResponseDto> findByStores(String storeKeyword, String addressKeyword) {
            List<StoreListViewProjection> projection = storeRepository.findStoresByStoreNameInAndAddressInOrderByAddressAsc(storeKeyword, addressKeyword);
            if (projection.isEmpty()) {
                throw new CustomException(ErrorCode.STORE_NOT_FOUND);
            }
            Double averageScope = calculateAverageScope();
            return projection.stream()
                    .map(storeProjection -> {
                        StoreListViewResponseDto dto = StoreListViewResponseDto.ResponseToEntity(storeProjection);
                        dto.setAverageScope(averageScope);
                        return dto;
                    })
                    .collect(Collectors.toList());
        }

    //관심매장추가
    @Override
    public LikeStoreDto saveLikeStore(Integer customerNo, Integer storeNo) {
        CustomerBas customerBas = customerBasRepository.findById(customerNo)
                .orElseThrow(() -> new CustomException(ErrorCode.CUSTOMER_NOT_FOUND));
        Store store = storeRepository.findById(storeNo)
                .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));
        LikeStore likeStore = LikeStore.builder()
                .customerBas(customerBas)
                .store(store)
                .createDt(LocalDateTime.now())
                .build();
        LikeStore saveLikeStore = likeStoreRepository.save(likeStore);
        return LikeStoreDto.mapToListStoreDto(saveLikeStore);
    }


    //관심매장삭제
    @Override
    public void deleteLikeStore(Integer customerNo, Integer storeNo) {
        boolean exists = likeStoreRepository.existsById(storeNo);
        if (!exists)
            throw new CustomException(ErrorCode.STORE_NOT_FOUND);
        Optional<LikeStore> likeStore = likeStoreRepository.findLikeStoreByCustomerBas_CstmrNoAndStoreStoreNo(customerNo, storeNo);
        if (likeStore.isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATE_LIKE_STORE);
        }
        likeStoreRepository.deleteLikeStoreByStoreStoreNo(storeNo);
    }


    private Double calculateAverageScope() {
        List<Review> reviews = reviewRepository.findAll();
        if (reviews.isEmpty()) {
            return (double) 0;
        }
        int totalScope = reviews.stream().mapToInt(Review::getScope).sum();
        return (double) (totalScope / reviews.size());
    }

    private Long calculateTotalLikeStore(Integer storeNo) {
       List<LikeStore> likeStores = likeStoreRepository.countLikeStoreByStoreStoreNo(storeNo);
       return (long) likeStores.size();
    }

    //리뷰작성
    @Override
    public ReviewResponseDto createReview(ReviewDto reviewDto) {
        Review review = new Review(
                reviewDto.getReviewNo(),
                reviewDto.getStore(),
                reviewDto.getCstmrNo(),
                reviewDto.getImg(),
                reviewDto.getContent(),
                reviewDto.getReviewDt(),
                reviewDto.getScope(),
                reviewDto.getAverageScope()
        );

        Review savedReview = reviewRepository.save(review);

        return new ReviewResponseDto(
                savedReview.getReviewNo(),
                savedReview.getStore(),
                savedReview.getCstmrNo(),
                savedReview.getImg(),
                savedReview.getContent(),
                savedReview.getReviewDt(),
                savedReview.getScope(),
                savedReview.getAverageScope()
        );
    }
}



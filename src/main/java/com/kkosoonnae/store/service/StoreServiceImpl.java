package com.kkosoonnae.store.service;

import com.kkosoonnae.jpa.entity.*;
import com.kkosoonnae.jpa.enu.StyleType;
import com.kkosoonnae.jpa.projection.StoreDetailViewProjection;
import com.kkosoonnae.jpa.projection.StoreListViewProjection;
import com.kkosoonnae.jpa.repository.*;
import com.kkosoonnae.search.dto.StoreListViewResponseDto;
import com.kkosoonnae.store.dto.*;
import com.kkosoonnae.store.exception.CustomException;
import com.kkosoonnae.store.exception.ErrorCode;
import com.kkosoonnae.store.util.RandomStyleTypeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        return new StoreDetailWithImageResponseDto(storeDetailViewProjection.get());
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
        return StyleDto.styleToDto(styles);
    }

    //관심매장추가
    @Override
    public LikeStoreDto saveLikeStore(Integer customerNo, Integer storeNo) {
        CustomerBas customerBas = customerBasRepository.findById(customerNo)
                .orElseThrow(() -> new CustomException(ErrorCode.CUSTOMER_NOT_FOUND));
        Store store = storeRepository.findById(storeNo)
                .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));
        boolean likeStoreExists = likeStoreRepository.existsLikeStoreByCustomerBas_CstmrNoAndStore_StoreNo(customerNo,storeNo);
        if(likeStoreExists)
            throw new CustomException(ErrorCode.DUPLICATE_LIKE_STORE);

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
        boolean likeStoreExists = likeStoreRepository.existsLikeStoreByCustomerBas_CstmrNoAndStore_StoreNo(customerNo,storeNo);
        if(!likeStoreExists)
            throw new CustomException(ErrorCode.LIKE_STORE_NOT_FOUND);

        likeStoreRepository.deleteLikeStoreByCustomerBas_CstmrNoAndStore_StoreNo(customerNo,storeNo);
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
                reviewDto.getScope()
        );

        Review savedReview = reviewRepository.save(review);

        return new ReviewResponseDto(
                savedReview.getReviewNo(),
                savedReview.getStore(),
                savedReview.getCstmrNo(),
                savedReview.getImg(),
                savedReview.getContent(),
                savedReview.getReviewDt(),
                savedReview.getScope()
        );
    }

    @Transactional
    @Override
    public StoreDto createStore(InputStoreInformation inputStoreInformation) {

        Store store =new Store(
                inputStoreInformation.getStoreName(),
                inputStoreInformation.getPhone(),
                inputStoreInformation.getLat(),
                inputStoreInformation.getLon(),
                inputStoreInformation.getRoadAddress()
        );
        List<Style> styles = IntStream.range(0,3)
                .mapToObj(i->new Style(store, RandomStyleTypeUtil.getRandomStyleType()))
                .collect(Collectors.toList());

        store.setStyles(styles);
        List<StoreImg> storeImages = inputStoreInformation.getStoreImgUrl().stream()
                .map(url -> new StoreImg(store, url))
                .collect(Collectors.toList());

        store.setStoreImages(storeImages);

        Store savedStore = storeRepository.save(store);
        return new StoreDto(savedStore);
    }

    @Override
    public List<StoreDto> findStores(double lat, double lon) {
        double distance = 5.0;
        List<Store> stores = storeRepository.findStoresWithinDistance(lat,lon,distance);

        return stores.stream().map(StoreDto::new).collect(Collectors.toList());
    }
}



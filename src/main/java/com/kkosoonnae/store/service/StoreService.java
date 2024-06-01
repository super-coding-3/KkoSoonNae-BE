package com.kkosoonnae.store.service;

import com.kkosoonnae.config.auth.PrincipalDetails;
import com.kkosoonnae.jpa.entity.*;
import com.kkosoonnae.jpa.projection.StoreDetailViewProjection;
import com.kkosoonnae.jpa.projection.StoreReviewsViewProjection;
import com.kkosoonnae.jpa.repository.*;
import com.kkosoonnae.pet.service.PetService;
import com.kkosoonnae.review.service.ReviewService;
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
public class StoreService {

    private final StoreRepository storeRepository;

    private final StyleRepository styleRepository;

    private final LikeStoreRepository likeStoreRepository;

    private final CustomerBasRepository customerBasRepository;

    private final ReviewService reviewService;

    private final StoreImgRepository storeImgRepository;

    private final PetService petService;


    //매장상세조회
    public StoreDetailWithImageResponseDto findStoreDetailWithImage(Integer storeNo) {
        Optional<StoreDetailViewProjection> storeDetailViewProjectionOpt = storeRepository.findStoreByStoreNo(storeNo);
        if (storeDetailViewProjectionOpt.isEmpty()) {
            throw new CustomException(ErrorCode.STORE_NOT_FOUND);
        }

        StoreDetailViewProjection storeDetailViewProjection = storeDetailViewProjectionOpt.get();

        List<String> imgUrls = storeImgRepository.findImgUrlsByStoreNo(storeNo);

        double averageScope = reviewService.getAverageReviewScore(storeNo);

        StoreDetailViewProjection projectionWithImages = new StoreDetailViewProjection(
                storeDetailViewProjection.storeNo(),
                storeDetailViewProjection.storeName(),
                storeDetailViewProjection.content(),
                storeDetailViewProjection.phone(),
                storeDetailViewProjection.roadAddress(),
                storeDetailViewProjection.openingTime(),
                storeDetailViewProjection.closingTime(),
                imgUrls,
                averageScope,
                storeDetailViewProjection.totalLikeStore()
        );

        return new StoreDetailWithImageResponseDto(projectionWithImages);
    }

    //매장스타일조회
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
    @Transactional
    public LikeStoreDto saveLikeStore(PrincipalDetails principalDetails,Integer storeNo) {
        Integer cstmrNo = principalDetails.getCustomerBas().getCstmrNo();
        CustomerBas customerBas = customerBasRepository.findById(cstmrNo)
                .orElseThrow(() -> new CustomException(ErrorCode.CUSTOMER_NOT_FOUND));
        Store store = storeRepository.findById(storeNo)
                .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));
        boolean likeStoreExists = likeStoreRepository.existsLikeStoreByCustomerBas_CstmrNoAndStore_StoreNo(cstmrNo,storeNo);
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
    @Transactional
    public void deleteLikeStore(PrincipalDetails principalDetails, Integer storeNo) {
        Integer cstmrNo = principalDetails.getCustomerBas().getCstmrNo();
        boolean likeStoreExists = likeStoreRepository.existsLikeStoreByCustomerBas_CstmrNoAndStore_StoreNo(cstmrNo,storeNo);
        if(!likeStoreExists)
            throw new CustomException(ErrorCode.LIKE_STORE_NOT_FOUND);

        likeStoreRepository.deleteLikeStoreByCustomerBas_CstmrNoAndStore_StoreNo(cstmrNo,storeNo);
    }

    //리뷰작성
//    public ReviewResponseDto createReview(ReviewDto reviewDto) {
//        Review review = new Review(
//                reviewDto.getReviewNo(),
//                reviewDto.getStore(),
//                reviewDto.getCstmrNo(),
//                reviewDto.getImg(),
//                reviewDto.getContent(),
//                reviewDto.getReviewDt(),
//                reviewDto.getScope()
//        );
//
//        Review savedReview = reviewRepository.save(review);
//
//        return new ReviewResponseDto(
//                savedReview.getReviewNo(),
//                savedReview.getStore(),
//                savedReview.getCstmrNo(),
//                savedReview.getImg(),
//                savedReview.getContent(),
//                savedReview.getReviewDt(),
//                savedReview.getScope()
//        );
//    }

    @Transactional
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

    public List<StoreDto> findStores(double lat, double lon) {
        double distance = 5.0;
        List<Store> stores = storeRepository.findStoresWithinDistance(lat,lon,distance);

        return stores.stream()
                .map(store -> {
                    double averageReviewScore = reviewService.getAverageReviewScore(store.getStoreNo());
                    Review latestReview = reviewService.getLatestReview(store.getStoreNo());
                    String latestReviewComment = (latestReview != null) ? latestReview.getContent() : "리뷰가 없습니다.";

                    String mainPetImage=null;

                    if (latestReview !=null){
                        mainPetImage=petService.getMainPetImageByCustomerNo(latestReview.getCstmrNo());
                    }

                    return new StoreDto(store, averageReviewScore, latestReviewComment,mainPetImage);

                })
                .collect(Collectors.toList());
    }


    public List<AllStore> getAllStores() {
        List<Store> stores = storeRepository.findAll();
        return stores.stream()
                .map(AllStore::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean updateStoreImg(Integer storeNo, List<String> newImageUrl) {
        Store store =storeRepository.findById(storeNo)
                .orElseThrow(()-> new CustomException(ErrorCode.STORE_NOT_FOUND));

        storeImgRepository.deleteByStore_StoreNo(storeNo);

        for (String imageUrl : newImageUrl){
            StoreImg storeImg = new StoreImg(store, imageUrl);
            storeImgRepository.save(storeImg);
        }

        return true;
    }

    public List<StoreReviewsResponseDto> findReviews(Integer storeNo) {
        if(storeNo == null) {
            throw new CustomException(ErrorCode.STORE_NOT_FOUND);
        }
        List<StoreReviewsViewProjection> reviewsViewProjections = storeRepository.findByStoreReviews(storeNo);
        if(reviewsViewProjections.isEmpty()) {
            throw new CustomException(ErrorCode.REVIEW_NOT_FOUND);
        }

        Double averageScope = reviewService.getAverageReviewScore(storeNo);

        return reviewsViewProjections.stream()
                .map(viewProjection -> viewProjection.toDto(averageScope))
                .collect(Collectors.toList());
    }
}




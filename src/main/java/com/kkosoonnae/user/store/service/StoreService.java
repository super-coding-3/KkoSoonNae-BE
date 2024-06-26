package com.kkosoonnae.user.store.service;

import com.kkosoonnae.common.exception.CustomException;
import com.kkosoonnae.common.exception.ErrorCode;
import com.kkosoonnae.common.util.RandomStyleTypeUtil;
import com.kkosoonnae.config.auth.PrincipalDetails;
import com.kkosoonnae.jpa.entity.*;
import com.kkosoonnae.jpa.projection.StoreDetailViewProjection;
import com.kkosoonnae.jpa.repository.*;
import com.kkosoonnae.user.pet.service.PetService;
import com.kkosoonnae.user.review.service.ReviewService;
import com.kkosoonnae.user.store.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
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

    private final RedisLikeStoreRepository redisLikeStoreRepository;

    private final StoreQueryRepository storeQueryRepository;

    private final RedisScopeRepository redisScopeRepository;


    //매장상세조회
    public StoreDetailWithImageResponseDto findStoreDetailWithImage(Integer storeNo) {
        try {
            StoreDetailWithImageResponseDto responseDto = storeQueryRepository.findStoreDetail(storeNo);
            if (responseDto == null) {
                throw new CustomException(ErrorCode.STORE_NOT_FOUND);
            }
            double averageScope = redisScopeRepository.getAverageScope(storeNo);
            Long totalLike = redisLikeStoreRepository.getTotalLikeStoreCount(storeNo);

            return responseDto.DetailStoreToDto(averageScope,totalLike);
        } catch (DataAccessException e) {
            throw new CustomException(ErrorCode.DATABASE_ERROR);
        }
    }
    //매장 펫스타일 조회
    public List<StyleDto> findStyles(Integer storeNo){
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
    public LikeStoreDto saveLikeStore(PrincipalDetails principalDetails, Integer storeNo) {
        if (principalDetails == null) {
            throw new CustomException(ErrorCode.USER_NOT_LOGIN);
        }
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

        redisLikeStoreRepository.LikeStoreCount(cstmrNo,storeNo);


        return LikeStoreDto.mapToListStoreDto(saveLikeStore);
    }

    //관심매장삭제
    @Transactional
    public void deleteLikeStore(PrincipalDetails principalDetails, Integer storeNo) {
        if (principalDetails ==null) {
            throw new CustomException(ErrorCode.USER_NOT_LOGIN);
        }
        Integer cstmrNo = principalDetails.getCustomerBas().getCstmrNo();
        boolean likeStoreExists = likeStoreRepository.existsLikeStoreByCustomerBas_CstmrNoAndStore_StoreNo(cstmrNo,storeNo);
        if(!likeStoreExists)
            throw new CustomException(ErrorCode.LIKE_STORE_NOT_FOUND);

        likeStoreRepository.deleteLikeStoreByCustomerBas_CstmrNoAndStore_StoreNo(cstmrNo,storeNo);

        redisLikeStoreRepository.RedisDeleteLikeStore(cstmrNo, storeNo);
    }



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
    //매장 리뷰리스트 조회
    public List<StoreReviewsResponseDto> findReviews(Integer storeNo) {
        if (storeNo == null) {
            throw new CustomException(ErrorCode.STORE_NOT_FOUND);
        }
        List<StoreReviewsResponseDto> reviewResponse;
        try {
            reviewResponse = storeQueryRepository.reviewListQuery(storeNo);
        }catch (DataAccessException dae) {
            throw new CustomException(ErrorCode.DATABASE_ERROR);
        }
        if (reviewResponse.isEmpty()) {
                throw new CustomException(ErrorCode.REVIEW_NOT_FOUND);

        }
        double averageScope = redisLikeStoreRepository.getTotalLikeStoreCount(storeNo);
        Long likeStore = redisLikeStoreRepository.getTotalLikeStoreCount(storeNo);
        return reviewResponse.stream()
                .map(reviewDto -> reviewDto.ReviewToDto(averageScope,likeStore))
                .collect(Collectors.toList());
    }
}




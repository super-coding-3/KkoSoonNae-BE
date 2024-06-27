package com.kkosoonnae.user.review.service;

import com.kkosoonnae.common.exception.NotFoundException;
import com.kkosoonnae.config.auth.PrincipalDetails;
import com.kkosoonnae.jpa.entity.CustomerBas;
import com.kkosoonnae.jpa.entity.Review;
import com.kkosoonnae.jpa.entity.Store;
import com.kkosoonnae.jpa.repository.RedisScopeRepository;
import com.kkosoonnae.jpa.repository.ReviewRepository;
import com.kkosoonnae.jpa.repository.StoreRepository;
import com.kkosoonnae.user.review.dto.ReviewRqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    private final StoreRepository storeRepository;

    private  final RedisScopeRepository redisScopeRepository;

    public Double getAverageReviewScore(Integer storeId){
        List<Review> reviews = reviewRepository.findByStoreStoreNo(storeId);
        double averageScore= reviews.stream()
                .mapToInt(Review::getScope)
                .average()
                .orElse(Double.NaN);

        if (Double.isNaN(averageScore)) {
            return 0.0; // or another appropriate default value
        }

        // Round to one decimal place
        double roundedAverageScore = Math.round(averageScore * 10.0) / 10.0;

        return roundedAverageScore;
    }

    public Review getLatestReview(Integer storeNo) {
        Optional<Review> latestReview = reviewRepository.findTopByStoreStoreNoOrderByReviewDtDesc(storeNo);
        return latestReview.orElse(null);
    }


//    public void writeReview(PrincipalDetails principalDetails, Store storeNo, String content) {
//        CustomerBas cstmrNo = principalDetails.getCustomerBas();
//        Store store = storeRepository.findById(storeNo.getStoreNo())
//                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 store 입니다."));
//
//        Review review = Review.of(cstmrNo,store, content, LocalDateTime.now());
//
//        reviewRepository.save(review);
//    }

    public void writeReview(PrincipalDetails principalDetails, Integer storeNo, ReviewRqDto rq){
        CustomerBas customerBas = principalDetails.getCustomerBas();

        Store store = storeRepository.findById(storeNo)
                .orElseThrow(()-> new NotFoundException("해당 매장을 찾을 수 없습니다."));

        Review review = Review.builder()
                .store(store)
                .cstmrNo(customerBas)
                .scope(rq.getScope())
                .content(rq.getContent())
                .reviewDt(rq.getCreateDt())
                .build();
        reviewRepository.save(review);

        redisScopeRepository.addScope(customerBas.getCstmrNo(),storeNo, rq.getScope());
    }

}

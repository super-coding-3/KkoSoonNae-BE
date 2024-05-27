package com.kkosoonnae.review.service;

import com.kkosoonnae.config.auth.PrincipalDetails;
import com.kkosoonnae.jpa.entity.CustomerBas;
import com.kkosoonnae.jpa.entity.Review;
import com.kkosoonnae.jpa.entity.Store;
import com.kkosoonnae.jpa.repository.ReviewRepository;
import com.kkosoonnae.jpa.repository.StoreRepository;
import com.kkosoonnae.review.dto.ReviewRqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    private final StoreRepository storeRepository;

    public double getAverageReviewScore(Integer storeId){
        List<Review> reviews = reviewRepository.findByStoreStoreNo(storeId);
        double averageScore= reviews.stream()
                .mapToInt(Review::getScope)
                .average()
                .orElse(Double.NaN);
        return Math.round(averageScore *10.0)/10.0;
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

    public void writeReview(PrincipalDetails principalDetails, Integer storeNo,ReviewRqDto rq){
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
    }

}

package com.kkosoonnae.review;

import com.kkosoonnae.jpa.entity.CustomerBas;
import com.kkosoonnae.jpa.entity.Review;
import com.kkosoonnae.jpa.entity.Store;
import com.kkosoonnae.jpa.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

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

    public void writeReview(Integer cstmrNo, Store storeNo, String content) {

        CustomerBas customer = reviewRepository.findCustomerByCustomerNumber(cstmrNo);
        if (customer != null) {
            Review review = new Review.Builder()
                    .cstmrNo(cstmrNo)
                    .storeNo(storeNo)
                    .content(content)
                    .createdAt(LocalDateTime.now())
                    .build();
            reviewRepository.save(review);
        } else {
            throw new IllegalArgumentException("Invalid customer number");
        }
    }

}

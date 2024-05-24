package com.kkosoonnae.review;

import com.kkosoonnae.jpa.entity.Review;
import com.kkosoonnae.jpa.entity.Store;
import com.kkosoonnae.jpa.repository.ReviewRepository;
import com.kkosoonnae.store.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<ReviewDto> getReviewsByStoreNo(Integer storeNo) {
        List<Review> reviews = reviewRepository.findByStoreStoreNo(storeNo);
        return reviews.stream().map(ReviewDto::new).collect(Collectors.toList());
    }

}

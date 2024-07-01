package com.kkosoonnae.president.reviewmanage.service;

import com.kkosoonnae.jpa.entity.Review;
import com.kkosoonnae.jpa.repository.ReviewQueryRepository;
import com.kkosoonnae.jpa.repository.ReviewRepository;
import com.kkosoonnae.president.reviewmanage.dto.ReviewManageRs;
import com.kkosoonnae.user.review.dto.ReviewResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * packageName    : com.kkosoonnae.president.review.service
 * fileName       : ReviewManageService
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
public class ReviewManageService {

    private final ReviewRepository reviewRepository;
    private final ReviewQueryRepository queryRepository;

    public List<ReviewResponseDto> getReviewsByCustomerAndDate(Integer reivewNo, LocalDateTime startDate, LocalDateTime endDate) {
        return queryRepository.findReviews(reivewNo, startDate, endDate);
    }

    public ReviewManageRs getReviewDetail(Integer reviewNo) {
        return queryRepository.findReviewDetail(reviewNo);
    }
}

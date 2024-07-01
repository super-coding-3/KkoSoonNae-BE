package com.kkosoonnae.president.reviewmanage.controller;

import com.kkosoonnae.president.reviewmanage.dto.ReviewManageRs;
import com.kkosoonnae.president.reviewmanage.service.ReviewManageService;
import com.kkosoonnae.user.review.dto.ReviewResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * packageName    : com.kkosoonnae.president.review.controller
 * fileName       : ReviewManageController
 * author         : hagjoon
 * date           : 2024-06-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-13        hagjoon       최초 생성
 */
@RestController
@RequestMapping("/api/president/Review-Manage")
@Tag(name = "ReviewManageController",description = "리뷰 관리 API 컨트롤러")
@Slf4j
@RequiredArgsConstructor
public class ReviewManageController {

    private final ReviewManageService reviewManageService;

    @Operation(summary = "리뷰 목록 상점 일련번호로 조회")
    @GetMapping("/customer/{cstmrNo}")
    public List<ReviewResponseDto> getReviewsByCustomer(@PathVariable Integer cstmrNo,
                                                         @RequestParam LocalDateTime startDate,
                                                         @RequestParam LocalDateTime endDate) {
        return reviewManageService.getReviewsByCustomerAndDate(cstmrNo, startDate, endDate);
    }

    @Operation(summary = "리뷰 목록 일련번호로 조회")
    @GetMapping("/{reviewNo}")
    public ReviewManageRs getReviewDetail(@PathVariable Integer reviewNo) {
        return reviewManageService.getReviewDetail(reviewNo);
    }
}

package com.kkosoonnae.user.review.controller;

import com.kkosoonnae.config.auth.PrincipalDetails;
import com.kkosoonnae.user.review.dto.ReviewRqDto;
import com.kkosoonnae.user.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * packageName    : com.kkosoonnae.user.review.controller
 * fileName       : ReviewController
 * author         : hagjoon
 * date           : 2024-06-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-13        hagjoon       최초 생성
 */
@RestController
@RequestMapping("/api/user/review")
@Tag(name = "ReviewController",description = "리뷰 작성 API 컨트롤러")
@Slf4j
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;


    @PostMapping("{storeNo}/reviews")
    @Operation(summary = "리뷰 작성")
    public ResponseEntity<?> writeReview(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                         @PathVariable Integer storeNo,
                                         @RequestBody ReviewRqDto reviewRqDto) {
        try {
            reviewService.writeReview(principalDetails, storeNo,reviewRqDto);
            return ResponseEntity.ok("리뷰가 정상적으로 작성되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

package com.kkosoonnae.mypage.controller;

import com.kkosoonnae.config.auth.PrincipalDetails;
import com.kkosoonnae.mypage.dto.AvailDto;
import com.kkosoonnae.mypage.dto.LikeStoreDto;
import com.kkosoonnae.mypage.dto.MyReviewDto;
import com.kkosoonnae.mypage.service.MyPageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * packageName    : com.kkosoonnae.mypage.controller
 * fileName       : MyPageController
 * author         : hagjoon
 * date           : 2024-05-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-23        hagjoon       최초 생성
 */
@RestController
@RequestMapping("/KkoSoonNae")
@Tag(name = "MyPageController",description = "마이페이지 API 정보 컨트롤러")
@Slf4j
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService service;

    @Operation(summary = "예약 내역 리스트")
    @GetMapping("/avail-list")
    public ResponseEntity<List<AvailDto>> getAvailList(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        Integer cstmrNo = principalDetails.getCustomerBas().getCstmrNo();
        List<AvailDto> result = service.getMyAvailList(cstmrNo);
        return ResponseEntity.ok().body(result);
    }

    @Operation(summary = "예약 취소")
    @DeleteMapping("/avail-cancel/{reservationNo}")
    public ResponseEntity<?> deleteAvail(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Integer reservationNo) {
        Integer cstmrNo = principalDetails.getCustomerBas().getCstmrNo();
        service.deleteAvail(cstmrNo, reservationNo);
        return ResponseEntity.ok(Collections.singletonMap("message", "예약이 취소되었습니다."));
    }

    @Operation(summary = "관심매장 조회")
    @GetMapping("/like-store")
    public ResponseEntity<List<LikeStoreDto>> getLikeStore(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        Integer cstmrNo = principalDetails.getCustomerBas().getCstmrNo();
        List<LikeStoreDto> result = service.getMyLikeStore(cstmrNo);
        return ResponseEntity.ok().body(result);
    }

    @Operation(summary = "관심매장 취소")
    @DeleteMapping("/like-cancel/{likeNo}")
    public ResponseEntity<?> deleteLike(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Integer likeNo) {
        Integer cstmrNo = principalDetails.getCustomerBas().getCstmrNo();
        service.deleteLike(cstmrNo, likeNo);
        return ResponseEntity.ok(Collections.singletonMap("message", "관심 매장 취소가 완료 되었습니다."));
    }
    @Operation(summary = "내가 쓴 리뷰")
    @GetMapping("/my-review-list")
    public ResponseEntity<List<MyReviewDto>> getMyReview(@AuthenticationPrincipal PrincipalDetails principalDetails){
        Integer cstmrNo = principalDetails.getCustomerBas().getCstmrNo();
        List<MyReviewDto> result = service.getMyReview(cstmrNo);
        return ResponseEntity.ok().body(result);
    }

    @Operation(summary = "리뷰 삭제")
    @DeleteMapping("/my-review/{reviewNo}")
    public ResponseEntity<?> deleteReview(@AuthenticationPrincipal PrincipalDetails principalDetails,@PathVariable Integer reviewNo){
        Integer cstmrNo = principalDetails.getCustomerBas().getCstmrNo();
        service.deleteReview(cstmrNo,reviewNo);
        return ResponseEntity.ok(Collections.singletonMap("message","내 리뷰가 삭제 되었습니다."));
    }
}

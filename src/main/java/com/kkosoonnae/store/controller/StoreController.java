package com.kkosoonnae.store.controller;

import com.kkosoonnae.config.auth.PrincipalDetails;
import com.kkosoonnae.jpa.entity.Store;
import com.kkosoonnae.review.dto.ReviewRqDto;
import com.kkosoonnae.review.service.ReviewService;
import com.kkosoonnae.store.dto.*;
import com.kkosoonnae.store.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName    : com.kkosoonnae.store.controller
 * fileName       : StoreController
 * author         : hagjoon
 * date           : 2024-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10        hagjoon       최초 생성
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/KkoSoonNae/store")
@Slf4j
public class StoreController {

    private final StoreService storeService;
    private final ReviewService reviewService;

    @GetMapping("/{storeNo}")
    @Operation(summary = "상세매장조회")
    public ResponseEntity<?> StoreDetailWithImage(@PathVariable Integer storeNo) {
        try {
            log.info("GET/Store 조회요청이 들어왔습니다.StoreNo:" + storeNo);
            StoreDetailWithImageResponseDto storeDetailWithImageResponseDto = storeService.findStoreDetailWithImage(storeNo);
            log.info("GET/Store 조회응답:" + storeDetailWithImageResponseDto);
            return ResponseEntity.ok().body(storeDetailWithImageResponseDto);
        } catch (Exception e) {
            Map<String, String> errorBody = new HashMap<>();
            errorBody.put("error", "매장이 없습니다.");
            log.info("Client 요청에 문제가 있어 다음 오류를 출력합니다.:" + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);
        }
    }

    @GetMapping("/{storeNo}/pethair")
    @Operation(summary = "펫스타일조회")
    public ResponseEntity<?> AllStyles(@PathVariable Integer storeNo) {
        try {
            log.info("GET/storeNo로 펫스타일 조회요청이 들어왔습니다.:" + storeNo);
            List<StyleDto> styleDto = storeService.findStyles(storeNo);
            log.info("GET/petHair 응답:" + styleDto);
            return ResponseEntity.ok(styleDto);
        } catch (Exception e) {
            Map<String, String> errorBody = new HashMap<>();
            errorBody.put("error", "펫스타일이 없습니다.");
            log.info("Client 요청에 문제가 있어 다음 오류를 출력합니다.:" + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);

        }
    }

    @PostMapping("/like-store")
    @Operation(summary = "관심매장등록")
    public ResponseEntity<?> likeStore(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestParam Integer storeNo) {
        try{
            log.info("POST/customerNo,storeNo 관심매장등록 요청이 들어왔습니다.:");
            LikeStoreDto likeStoreDto = storeService.saveLikeStore(principalDetails,storeNo);
            log.info("POST/LikeStore 응답조회:" + likeStoreDto);
            return ResponseEntity.ok(likeStoreDto);
        } catch (Exception e) {
            Map<String, String> errorBody = new HashMap<>();
            errorBody.put("error", "관심매장이 중복입니다.");
            log.info("Client 요청에 문제가 있어 다음 오류를 출력합니다.:" + e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorBody);
        }
    }

    @DeleteMapping("/delete-likeStore")
    @Operation(summary = "관심매장삭제")
    public ResponseEntity<?> removeLikeStore(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestParam Integer storeNo) {
        try {
            log.info("POST/customerNo,storeNo 관심매장삭제 요청이 들어왔습니다.");
            storeService.deleteLikeStore(principalDetails, storeNo);
            log.info("POST/관심매장삭제 응답");
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            Map<String, String> errorBody = new HashMap<>();
            errorBody.put("error", "관심매장이 없습니다.");
            log.info("Client 요청에 문제가 있어 다음 오류를 출력합니다.:" + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);
        }
    }

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

    @PostMapping("/add")
    @Operation(summary = "매장 등록")
    public ResponseEntity<StoreDto> createStore(@RequestBody InputStoreInformation inputStoreInformation){
        try{
            log.info("Post /add 스토어 추가 요청이 들어왔습니다. InputStoreInformation:{}",inputStoreInformation);
            StoreDto storeDto = storeService.createStore(inputStoreInformation);
            log.info("Post /add 스토어 추가 응답: {}",storeDto);
            return ResponseEntity.ok(storeDto);
        }catch (Exception e){
            log.info("스토어 등록중 오류 발생:{}", e.getMessage());
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/nearby")
    @Operation(summary = "내 주변 매장 정보 보기")
    public ResponseEntity<?> getNearByStores(@RequestParam double lat, @RequestParam double lon) {
        List<StoreDto> stores = storeService.findStores(lat, lon);
        if (stores.isEmpty()) {
            return ResponseEntity.ok("내 주변 5KM 이내에 매장이 없습니다.");
        } else {
            return ResponseEntity.ok(stores);
        }
    }

    @GetMapping("/allStore")
    @Operation(summary = "전체 매장 정보 보기")
    public ResponseEntity<List<AllStore>> getAllStore(){
        List<AllStore> stores = storeService.getAllStores();
        return ResponseEntity.ok(stores);
    }
    @GetMapping("list-review/{storeNo}")
    @Operation(summary = "매장별 리뷰 리스트")
    public ResponseEntity<List<StoreReviewsResponseDto>> getReviews(@PathVariable Integer storeNo) {
        List<StoreReviewsResponseDto> reviewsResponse = storeService.findReviews(storeNo);
        return ResponseEntity.ok(reviewsResponse);
    }

    @PutMapping("/{storeNo}/images")
    @Operation(summary = "매장 이미지 바꾸는 코드")
    public ResponseEntity<String> updateStoreImg(@PathVariable Integer storeNo,
                                   @RequestBody StoreImageDTO newImageUrls){
        List<String> newImages =newImageUrls.getNewImageUrls();
       boolean success= storeService.updateStoreImg(storeNo,newImages);
       if (success){
           return ResponseEntity.ok("변경 성공");
       }else {
           return ResponseEntity.status(500).body("변경 실패");
       }
    }



}




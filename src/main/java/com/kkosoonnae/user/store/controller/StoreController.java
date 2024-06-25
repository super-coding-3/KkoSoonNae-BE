package com.kkosoonnae.user.store.controller;



import com.kkosoonnae.config.auth.PrincipalDetails;
import com.kkosoonnae.user.store.service.StoreService;
import com.kkosoonnae.user.store.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;


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
@RequestMapping("/api/user/store")
@Tag(name = "StoreController",description = "매장 API 정보 컨트롤러")
@Slf4j
public class StoreController {

    private final StoreService storeService;


    @GetMapping("/{storeNo}")
    @Operation(summary = "상세매장조회")
    public ResponseEntity<?> StoreDetailWithImage(@PathVariable Integer storeNo) {
        log.info("GET/Store 조회요청이 들어왔습니다.StoreNo:" + storeNo);
        StoreDetailWithImageResponseDto storeDetailWithImageResponseDto = storeService.findStoreDetailWithImage(storeNo);
        log.info("GET/Store 조회응답:" + storeDetailWithImageResponseDto);
        return ResponseEntity.ok().body(storeDetailWithImageResponseDto);

    }


    @GetMapping("/{storeNo}/pethair")
    @Operation(summary = "펫스타일조회")
    public ResponseEntity<?> AllStyles(@PathVariable Integer storeNo) {
            log.info("GET/storeNo로 펫스타일 조회요청이 들어왔습니다.:" + storeNo);
            List<StyleDto> styleDto = storeService.findStyles(storeNo);
            log.info("GET/petHair 응답:" + styleDto);
            return ResponseEntity.ok(styleDto);

    }

    @PostMapping("/like-store/{storeNo}")
    @Operation(summary = "관심매장등록")
    public ResponseEntity<?> likeStore(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @PathVariable Integer storeNo) {
            log.info("POST/customerNo,storeNo 관심매장등록 요청이 들어왔습니다.:");
            LikeStoreDto likeStoreDto = storeService.saveLikeStore(principalDetails, storeNo);
            log.info("POST/LikeStore 응답조회:" + likeStoreDto);
            return ResponseEntity.ok(likeStoreDto);
    }

    @DeleteMapping("/delete-likeStore/{storeNo}")
    @Operation(summary = "관심매장삭제")
    public ResponseEntity<?> removeLikeStore(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @PathVariable Integer storeNo) {
        log.info("POST/customerNo,storeNo 관심매장삭제 요청이 들어왔습니다.");
        storeService.deleteLikeStore(principalDetails, storeNo);
        log.info("POST/관심매장삭제 응답");
        return ResponseEntity.noContent().build();

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
    public ResponseEntity<?> getReviews(@PathVariable Integer storeNo) {
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




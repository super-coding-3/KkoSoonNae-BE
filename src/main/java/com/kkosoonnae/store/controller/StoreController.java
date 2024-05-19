package com.kkosoonnae.store.controller;

import com.kkosoonnae.store.dto.LikeStoreDto;
import com.kkosoonnae.store.dto.StoreDetailWithImageResponseDto;
import com.kkosoonnae.store.dto.StoreListViewResponseDto;
import com.kkosoonnae.store.dto.StyleDto;
import com.kkosoonnae.store.exception.CustomException;
import com.kkosoonnae.store.exception.ErrorCode;
import com.kkosoonnae.store.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
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
@RequestMapping("/KkoSoonNae/store")
@Slf4j
public class StoreController {


    private final StoreService storeService;

    @GetMapping("/{storeNo}")
    @Operation(summary = "상세매장조회")
    public ResponseEntity<StoreDetailWithImageResponseDto> StoreDetailWithImage(@PathVariable Integer storeNo) {
        try {
            log.info("GET/Store 조회요청이 들어왔습니다.StoreNo:" + storeNo);
            StoreDetailWithImageResponseDto storeDetailWithImageResponseDto = storeService.findStoreDetailWithImage(storeNo);
            log.info("GET/Store 조회응답:" + storeDetailWithImageResponseDto);
            return ResponseEntity.ok().body(storeDetailWithImageResponseDto);
        } catch (NotFoundException e) {
            log.info("Client 요청에 문제가 있어 다음 오류를 출력합니다.:" + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{storeNo}/petHair")
    @Operation(summary = "펫스타일조회")
    public ResponseEntity<List<StyleDto>> AllStyles(@PathVariable Integer storeNo) {
        try {
            log.info("GET/storeNo로 펫스타일 조회요청이 들어왔습니다.:" + storeNo);
            List<StyleDto> styleDto = storeService.findStyles(storeNo);
            log.info("GET/petHair 응답:" + styleDto);
            return ResponseEntity.ok(styleDto);
        } catch (NotFoundException e) {
            log.info("Client 요청에 문제가 있어 다음 오류를 출력합니다.:" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("/stores/search")
    @Operation(summary = "전체매장검색")
    public ResponseEntity<List<StoreListViewResponseDto>> searchByStores(@RequestParam(required = false) String storeKeyword, @RequestParam(required = false) String addressKeyword) {
        try{
            log.info("GET/storeKeyword 또는 addressKeyword 조회요청 들어왔습니다.:" +storeKeyword,addressKeyword);
        List<StoreListViewResponseDto> storeListViewResponseDto = storeService.findByStores(storeKeyword, addressKeyword);
            log.info("GET/storeKeyword 또는 addressKeyword 조회응답.:" + storeListViewResponseDto);
        return ResponseEntity.ok(storeListViewResponseDto);
    }catch (NotFoundException e) {
            log.info("Client 요청에 문제가 있어 다음 오류를 출력합니다.:" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/stores-page")
    public Page<StoreListViewResponseDto> findStoresPagination(@RequestParam(required = false) String storeKeyword, String addressKeyword, Pageable pageable) {
        return storeService.findAllWithPageable(storeKeyword, addressKeyword, pageable);
    }

    @PostMapping("/likeStore")
    @Operation(description = "관심매장등록")
    public ResponseEntity<?> likeStore(@RequestParam Integer customerNo, Integer storeNo) {
        try {
            log.info("POST/customerNo,storeNo 관심매장등록 요청이 들어왔습니다.:");
            LikeStoreDto likeStoreDto = storeService.saveLikeStore(customerNo, storeNo);
            log.info("POST/LikeStore 응답조회:" + likeStoreDto);
            return ResponseEntity.ok(likeStoreDto);
        } catch (NotFoundException e) {
            log.info("Client 요청에 문제가 있어 다음 오류를 출력합니다.:" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteLikeStore/customer/{customerNo}/store/{storeNo}")
    @Operation(description = "관심매장삭제")
    public ResponseEntity<?> removeLikeStore(@PathVariable Integer customerNo,
                                             @PathVariable Integer storeNo) {
        try {
            log.info("POST/customerNo,storeNo 관심매장삭제 요청이 들어왔습니다.");
            storeService.deleteLikeStore(customerNo, storeNo);
            log.info("POST/관심매장삭제 응답" );
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            log.info("Client 요청에 문제가 있어 다음 오류를 출력합니다.:" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}



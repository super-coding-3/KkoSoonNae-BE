package com.kkosoonnae.store.controller;

import com.kkosoonnae.store.dto.StoreDetailWithImageResponseDto;
import com.kkosoonnae.store.dto.StoreListViewResponseDto;
import com.kkosoonnae.store.dto.StyleDto;
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
            // return ResponseEntity.status(HttpStatus.NOT_FOUND).body((StoreDetailWithImageResponseDto) Collections.emptyList());
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
    public ResponseEntity<List<StoreListViewResponseDto>> searchByStores (@RequestParam(required = false) String storeKeyword, @RequestParam(required = false) String addressKeyword) {
        List<StoreListViewResponseDto> storeListViewResponseDto = storeService.findByStores(storeKeyword,addressKeyword);
        return ResponseEntity.ok(storeListViewResponseDto);
    }

    @GetMapping("/stores-page")
    public Page<StoreListViewResponseDto> findStoresPagination(@RequestParam String storeKeyword,String addressKeyword,Pageable pageable) {
        return storeService.findAllWithPageable(storeKeyword,addressKeyword,pageable);
    }
}




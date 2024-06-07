package com.kkosoonnae.search.controller;

import com.kkosoonnae.jpa.projection.MainStoresListviewProjection;
import com.kkosoonnae.search.dto.MainStoreListViewResponseDto;
import com.kkosoonnae.search.dto.StoreListViewResponseDto;
import com.kkosoonnae.search.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.*;

/**
 * packageName    : com.kkosoonnae.store.service
 * fileName       : searchController
 * author         : KimJaeIk
 * date           : 2024-05-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-22        KimJaeIk    최초 생성
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search/")
@Slf4j
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/stores/")
    @Operation(summary = "전체매장검색")
    public ResponseEntity<?> searchByStores(@RequestParam String nameAddressKeyword) {
        try {
            log.info("GET/storeKeyword 또는 addressKeyword 조회요청 들어왔습니다.:" + nameAddressKeyword);
            List<StoreListViewResponseDto> storeListViewResponseDto = searchService.findByStores(nameAddressKeyword);
            log.info("GET/storeKeyword 또는 addressKeyword 조회응답.:" + storeListViewResponseDto);
            return ResponseEntity.ok(storeListViewResponseDto);
        } catch (Exception e) {
            Map<String, String> errorBody = new HashMap<>();
            errorBody.put("error", "해당 매장이 없습니다.");
            log.info("Client 요청에 문제가 있어 다음 오류를 출력합니다.:" + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);
        }
    }

    @GetMapping("/main-stores/{addressKeyword}")
    @Operation(summary = "메인 강남구 매장 정보")
    public ResponseEntity<?> mainByStores(@PathVariable String addressKeyword) {
        try {
            Pageable pageable = PageRequest.of(0, 10);
            List<MainStoreListViewResponseDto> mainListViewResponseDto = searchService.findByMainStores(addressKeyword,pageable);
            return ResponseEntity.ok(mainListViewResponseDto);
        } catch (Exception e) {
            Map<String, String> errorBody = new HashMap<>();
            errorBody.put("error", "해당 매장 없습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);
        }
    }
}

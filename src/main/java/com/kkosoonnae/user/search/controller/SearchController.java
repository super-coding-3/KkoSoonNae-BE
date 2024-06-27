package com.kkosoonnae.user.search.controller;


import com.kkosoonnae.user.search.dto.MainStoreListViewResponseDto;
import com.kkosoonnae.user.search.dto.StoreListViewResponseDto;
import com.kkosoonnae.user.search.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/user/search/")
@Tag(name = "SearchController",description = "매장검색 API 정보 컨트롤러")
@Slf4j
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/stores/{nameAddressKeyword}")
    @Operation(summary = "전체매장검색")
    public ResponseEntity<?> searchByStores(@PathVariable String nameAddressKeyword) {
            log.info("GET/storeKeyword 또는 addressKeyword 조회요청 들어왔습니다.:" + nameAddressKeyword);
            List<StoreListViewResponseDto> storeListViewResponseDto = searchService.findByStores(nameAddressKeyword);
            log.info("GET/storeKeyword 또는 addressKeyword 조회응답.:" + storeListViewResponseDto);
            return ResponseEntity.ok(storeListViewResponseDto);

        }


    @GetMapping("/main-stores/{addressKeyword}")
    @Operation(summary = "메인 강남구 매장 정보")
    public ResponseEntity<?> mainByStores(@PathVariable String addressKeyword) {
            Pageable pageable = PageRequest.of(0, 10);
            List<MainStoreListViewResponseDto> mainListViewResponseDto = searchService.findByMainStores(addressKeyword,pageable);
            return ResponseEntity.ok(mainListViewResponseDto);

        }
    }


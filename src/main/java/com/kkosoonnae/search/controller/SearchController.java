package com.kkosoonnae.search.controller;

import com.kkosoonnae.search.dto.StoreListViewResponseDto;
import com.kkosoonnae.search.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

import java.util.List;

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
@RequestMapping("/KkoSoonNae/search/")
@Slf4j
public class SearchController {

    private final SearchService searchService;
    @GetMapping("/stores/")
    @Operation(summary = "전체매장검색")
    public ResponseEntity<List<StoreListViewResponseDto>> searchByStores(@RequestParam(required = false) String storeKeyword, @RequestParam(required = false) String addressKeyword) {
        try{
            log.info("GET/storeKeyword 또는 addressKeyword 조회요청 들어왔습니다.:" +storeKeyword,addressKeyword);
            List<StoreListViewResponseDto> storeListViewResponseDto = searchService.findByStores(storeKeyword, addressKeyword);
            log.info("GET/storeKeyword 또는 addressKeyword 조회응답.:" + storeListViewResponseDto);
            return ResponseEntity.ok(storeListViewResponseDto);
        }catch (NotFoundException e) {
            log.info("Client 요청에 문제가 있어 다음 오류를 출력합니다.:" + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

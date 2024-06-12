package com.kkosoonnae.president.mystore.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.kkosoonnae.president.mystore.controller
 * fileName       : MyStoreController
 * author         : hagjoon
 * date           : 2024-06-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-13        hagjoon       최초 생성
 */
@RestController
@RequestMapping("/api/president/MyStore")
@Tag(name = "MyStoreController",description = "내 매장 정보 API 컨트롤러")
@Slf4j
@RequiredArgsConstructor
public class MyStoreController {
}

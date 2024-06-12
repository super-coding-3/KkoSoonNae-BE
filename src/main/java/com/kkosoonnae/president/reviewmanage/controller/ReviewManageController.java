package com.kkosoonnae.president.reviewmanage.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.kkosoonnae.president.review.controller
 * fileName       : ReviewManageController
 * author         : hagjoon
 * date           : 2024-06-13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-13        hagjoon       최초 생성
 */
@RestController
@RequestMapping("/api/president/Review-Manage")
@Tag(name = "ReviewManageController",description = "리뷰 관리 API 컨트롤러")
@Slf4j
@RequiredArgsConstructor
public class ReviewManageController {
}

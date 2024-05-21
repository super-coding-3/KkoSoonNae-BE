package com.kkosoonnae.point.controller;

import com.kkosoonnae.config.auth.PrincipalDetails;
import com.kkosoonnae.point.dto.PointDto;
import com.kkosoonnae.point.service.PointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.kkosoonnae.point.controller
 * fileName       : PointController
 * author         : hagjoon
 * date           : 2024-05-20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-20        hagjoon       최초 생성
 */
@RestController
@RequestMapping("/KkoSoonNae/point")
@Tag(name = "PointController",description = "포인트 API 정보 컨트롤러")
@Slf4j
@RequiredArgsConstructor
public class PointController {

    private final PointService service;

    @Operation(summary = "잔여 포인트")
    @GetMapping()
    public ResponseEntity<?> getMyPoint(@AuthenticationPrincipal PrincipalDetails principalDetails){
        PointDto dto = service.getMyPoint(principalDetails);
        return ResponseEntity.ok().body(dto);
    }
}
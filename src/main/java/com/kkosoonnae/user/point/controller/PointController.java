package com.kkosoonnae.user.point.controller;

import com.kkosoonnae.config.auth.PrincipalDetails;
import com.kkosoonnae.user.point.dto.PointDto;
import com.kkosoonnae.user.point.service.PointService;
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
@RequestMapping("/api/point")
@Tag(name = "PointController",description = "포인트 API 정보 컨트롤러")
@Slf4j
@RequiredArgsConstructor
public class PointController {

    private final PointService service;

    @Operation(summary = "잔여 포인트")
    @GetMapping()
    public ResponseEntity<?> getMyPoint(@AuthenticationPrincipal PrincipalDetails principalDetails){
        Integer cstmrNo = principalDetails.getCustomerBas().getCstmrNo();
        PointDto dto = service.getMyPoint(cstmrNo);
        return ResponseEntity.ok().body(dto);
    }
}

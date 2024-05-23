package com.kkosoonnae.mypage.controller;

import com.kkosoonnae.config.auth.PrincipalDetails;
import com.kkosoonnae.mypage.dto.AvailDto;
import com.kkosoonnae.mypage.service.MyPageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * packageName    : com.kkosoonnae.mypage.controller
 * fileName       : MyPageController
 * author         : hagjoon
 * date           : 2024-05-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-23        hagjoon       최초 생성
 */
@RestController
@RequestMapping("/avail")
@Tag(name = "MyPageController",description = "마이페이지 API 정보 컨트롤러")
@Slf4j
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService service;

    @Operation(summary = "예약 내역 리스트")
    @GetMapping("/list")
    public ResponseEntity<List<AvailDto>> getAvailList(@AuthenticationPrincipal PrincipalDetails principalDetails){
        List<AvailDto> result = service.getMyAvailList(principalDetails);
        return ResponseEntity.ok().body(result);
    }

    @Operation(summary = "예약 취소")
    @DeleteMapping("/cancel/{reservationNo}")
    public ResponseEntity<?> deleteAvail(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Integer reservationNo){
        Integer cstmrNo = principalDetails.getCustomerBas().getCstmrNo();
        service.deleteAvail(cstmrNo,reservationNo);
        return ResponseEntity.ok(Collections.singletonMap("message","예약이 취소되었습니다."));
    }
}

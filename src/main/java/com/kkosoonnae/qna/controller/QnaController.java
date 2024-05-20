package com.kkosoonnae.qna.controller;

import com.kkosoonnae.config.auth.PrincipalDetails;
import com.kkosoonnae.qna.dto.QnaListDto;
import com.kkosoonnae.qna.dto.QnaRqDto;
import com.kkosoonnae.qna.service.QnaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName    : com.kkosoonnae.member.controller
 * fileName       : MemberController
 * author         : hagjoon
 * date           : 2024-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10        hagjoon       최초 생성
 */
@RestController
@RequestMapping("/KkoSoonNae/qna")
@Tag(name = "QnaController",description = "회원 QNA API 정보 컨트롤러")
@Slf4j
@RequiredArgsConstructor
public class QnaController {

    private final QnaService service;

    @Operation(summary = "문의사항 등록")
    @PostMapping("/create")
    public ResponseEntity<?> addQna(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody QnaRqDto rq){
        try{
            Map<String,String> rs = new HashMap<>();
            rs.put("message","문의사항 등록에 성공하였습니다.");
            service.createQna(principalDetails, rq);
            return ResponseEntity.ok().body(rs);
        }catch (IllegalStateException e){
            Map<String,String> rs = new HashMap<>();
            rs.put("message","문의사항 등록에 실패하였습니다.");
            rs.put("e.message : {}",e.getMessage());
            return ResponseEntity.ok().body(rs);
        }
    }

    @Operation(summary = "문의사항 전체 조회")
    @GetMapping("/all-list")
    public ResponseEntity<?> getQnaAllList(){
        try {
            List<QnaListDto> result = service.allList();
            return ResponseEntity.ok().body(result);
        }catch (IllegalStateException e){
            Map<String,String> rs = new HashMap<>();
            rs.put("message","문의 사항 불러오는 과정에서 에러가 발생 하였습니다.");
            rs.put("e.message",e.getMessage());
            return ResponseEntity.ok().body(rs);
        }catch (Exception e){
            Map<String,String> rs = new HashMap<>();
            rs.put("message","문의 사항 불러오는 과정에서 알수 없는 에러가 발생 하였습니다.");
            rs.put("e.message",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(rs);
        }
    }

    @Operation(summary = "문의사항 상세 조회")
    @GetMapping("/detailQna/{qnaNo}")
    public ResponseEntity<QnaListDto> getDetailQna(@PathVariable Integer qnaNo){
        QnaListDto result = service.detailQna(qnaNo);
        return ResponseEntity.ok().body(result);
    }

    @Operation(summary = "문의사항 삭제")
    @DeleteMapping("/deleteQna/{qnaNo}")
    public ResponseEntity<?> deleteQna(@PathVariable Integer qnaNo){
        Map<String,String> rs = new HashMap<>();
        rs.put("message","문의사항 삭제 성공하였습니다.");
        service.deleteQna(qnaNo);
        return ResponseEntity.ok(rs);
    }

}

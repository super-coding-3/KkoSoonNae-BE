package com.kkosoonnae.pet.controller;

import com.kkosoonnae.config.auth.PrincipalDetails;
import com.kkosoonnae.jpa.entity.CustomerBas;
import com.kkosoonnae.pet.dto.PetInfoDto;
import com.kkosoonnae.pet.dto.PetUpdate;
import com.kkosoonnae.pet.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.Collections;
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
@RequestMapping("/KkoSoonNae/pet")
@Tag(name = "PetController",description = "반려동물 API 정보 컨트롤러")
@Slf4j
@RequiredArgsConstructor
public class PetController {

    private final PetService service;


    @Operation(summary = "반려동물 전체 조회")
    @GetMapping("/allPet-list")
    public ResponseEntity<List<PetInfoDto>> allListPet(){
        List<PetInfoDto> myPets = service.petList();
        return ResponseEntity.ok().body(myPets);
    }

    @Operation(summary = "반려동물 상세 조회")
    @GetMapping("/pet-list/{petNo}")
    public ResponseEntity<PetInfoDto>detailPet(@PathVariable Integer petNo){
        PetInfoDto result = service.detailPet(petNo);
        log.info("result : ",result);
        return ResponseEntity.ok().body(result);
    }


    @Operation(summary = "반려동물 추가")
    @PostMapping("/addPet")
    public ResponseEntity<?> addPet(@AuthenticationPrincipal PrincipalDetails principalDetails,@RequestBody PetInfoDto petInfoDto){
        try{
            Map<String, String> rs = new HashMap<>();
            rs.put("message","반려동물 추가에 성공 하였습니다.");
            service.petAdd(principalDetails,petInfoDto);
            return ResponseEntity.ok(rs);
        }catch (NotFoundException e){
            Map<String,String> rs = new HashMap<>();
            rs.put("message","정보를 찾을 수 없습니다.");
            rs.put("message : {} ", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rs);
        }catch (Exception e){
            Map<String, String> rs = new HashMap<>();
            rs.put("message" , "반려동물 추가에 실패 하였습니다.");
            rs.put("message : {} ",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(rs);
        }
    }

    @Operation(summary = "반려동물 정보 수정")
    @PutMapping("/update/{petNo}")
    public ResponseEntity<?> updatePet(@AuthenticationPrincipal PrincipalDetails principalDetails,@PathVariable Integer petNo,@RequestBody PetUpdate petUpdate){
        try {
            Map<String, String> rs = new HashMap<>();
            rs.put("message","반려동물 정보 수정에 성공 하였습니다.");
            service.petUpdate(principalDetails,petNo,petUpdate);
            return ResponseEntity.ok(rs);
        }catch (NotFoundException e){
            Map<String,String> rs = new HashMap<>();
            rs.put("message","반려동물 정보를 찾을 수 없습니다.");
            rs.put("message : {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rs);
        }catch (Exception e){
            Map<String, String> rs = new HashMap<>();
            rs.put("message" , "반려동물 정보 수정에 실패 하였습니다.");
            rs.put("message : {} ",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(rs);
        }
    }

    @Operation(summary = "반려동물 정보 삭제")
    @DeleteMapping("/deletePet/{petNo}")
    public ResponseEntity<?> deletePet(@AuthenticationPrincipal PrincipalDetails principalDetails,@PathVariable Integer petNo){
        try {
            Integer cstmrNo = principalDetails.getCustomerBas().getCstmrNo();
            service.deletePet(cstmrNo,petNo);
            return ResponseEntity.ok(Collections.singletonMap("message","반려동물 정보 삭제에 성공 하였습니다."));
        }catch (IllegalStateException e){
            return ResponseEntity.ok(Collections.singletonMap("message",e.getMessage()));
        }
    }

}

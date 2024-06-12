package com.kkosoonnae.user.pet.controller;

import com.kkosoonnae.common.exception.NotFoundException;
import com.kkosoonnae.config.auth.PrincipalDetails;
import com.kkosoonnae.config.s3.S3Uploader;
import com.kkosoonnae.user.pet.dto.PetAddDto;
import com.kkosoonnae.user.pet.dto.PetInfoDto;
import com.kkosoonnae.user.pet.dto.PetUpdate;
import com.kkosoonnae.user.pet.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
@RequestMapping("/api/pet")
@Tag(name = "PetController",description = "반려동물 API 정보 컨트롤러")
@Slf4j
@RequiredArgsConstructor
public class PetController {

    private final PetService service;

    private final S3Uploader s3Uploader;


    @Operation(summary = "반려동물 전체 조회")
    @GetMapping("/allPet-list")
    public ResponseEntity<List<PetInfoDto>> allListPet(@AuthenticationPrincipal PrincipalDetails principalDetails){
        Integer custmrNo = principalDetails.getCustomerBas().getCstmrNo();
        List<PetInfoDto> myPets = service.petList(custmrNo);
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
    @PostMapping(value = "/addPet",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> addPet(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                    @RequestPart PetAddDto petAddDto,
                                   @Parameter(description = "반려동물 이미지") @RequestPart(required = false)MultipartFile petImg){
        try{
            Map<String, String> rs = new HashMap<>();
            rs.put("message","반려동물 추가에 성공 하였습니다.");
            String img = s3Uploader.upload(petImg,"pet");
            petAddDto.setImg(img);

            service.petAdd(principalDetails,petAddDto);
            return ResponseEntity.ok(rs);
        }catch (NotFoundException e){
            Map<String,String> rs = new HashMap<>();
            rs.put("message","정보를 찾을 수 없습니다.");
            rs.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rs);
        }catch (Exception e){
            Map<String, String> rs = new HashMap<>();
            rs.put("message" , "반려동물 추가에 실패 하였습니다.");
            rs.put("error",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(rs);
        }
    }

    @Operation(summary = "반려동물 정보 수정")
    @PutMapping(value = "/update/{petNo}",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> updatePet(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                       @PathVariable Integer petNo,
                                       @RequestPart PetUpdate petUpdate,
                                       @Parameter(description = "반려동물 이미지") @RequestPart(required = false)MultipartFile petImg){
        try {
            Map<String, String> rs = new HashMap<>();
            rs.put("message","반려동물 정보 수정에 성공 하였습니다.");
            service.petUpdate(principalDetails,petNo,petUpdate,petImg);
            return ResponseEntity.ok(rs);
        }catch (NotFoundException e){
            Map<String,String> rs = new HashMap<>();
            rs.put("message","반려동물 정보를 찾을 수 없습니다.");
            rs.put("message : {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rs);
        }catch (AccessDeniedException e){
            Map<String, String> rs = new HashMap<>();
            rs.put("message", "본인의 반려동물 정보만 수정 할 수 있습니다.");
            rs.put("details", e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(rs);
        } catch (Exception e){
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
        }catch (IllegalArgumentException e){
            return ResponseEntity.ok(Collections.singletonMap("message",e.getMessage()));
        }catch (IllegalStateException e){
            return ResponseEntity.ok(Collections.singletonMap("message",e.getMessage()));
        }
    }

    @Operation(summary = "대표 반려동물 설정")
    @PutMapping("/main-pet/{petNo}")
    public ResponseEntity<?> mainPet(@PathVariable Integer petNo,
                                     @AuthenticationPrincipal PrincipalDetails principalDetails){
        try{
            Integer cstmrNo = principalDetails.getCustomerBas().getCstmrNo();
        service.mainPet(petNo, cstmrNo);
        return ResponseEntity.ok(Collections.singletonMap("message","대표 반려동물 설정에 성공 하였습니다."));
        } catch (Exception e){
            return ResponseEntity.ok(Collections.singletonMap("message",e.getMessage()));
        }
    }

}

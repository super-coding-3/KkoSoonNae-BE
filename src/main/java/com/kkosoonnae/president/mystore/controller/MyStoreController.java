package com.kkosoonnae.president.mystore.controller;

import com.kkosoonnae.president.mystore.dto.AdminStoreImgRequestDto;
import com.kkosoonnae.president.mystore.dto.AdminStoreRequestDto;
import com.kkosoonnae.president.mystore.service.MyStoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
@Tag(name = "MyStoreController",description = "어드민 내 매장 정보 API 컨트롤러")
@Slf4j
@RequiredArgsConstructor
public class MyStoreController {
    private final MyStoreService myStoreService;

    @PostMapping("/register")
    @Operation(summary = "어드민 매장등록" )
    public ResponseEntity<?> adminCreateStore(@RequestBody AdminStoreRequestDto adminStoreRequestDto) {
        AdminStoreRequestDto requestDto = myStoreService.createStore(adminStoreRequestDto);
        return ResponseEntity.ok().body(requestDto);

    }

    @PostMapping("/register-img")
    @Operation(summary = "어드민 매장이미지 등록 ")
    public ResponseEntity<?> uploadStoreImg(@RequestPart(name = "storeNo")Integer storeNo, @RequestPart(name = "multipartFile") MultipartFile multipartFile) throws IOException {
        AdminStoreImgRequestDto imgRequestDto = myStoreService.uploadImg(storeNo,multipartFile);
        return ResponseEntity.ok().body(imgRequestDto);
    }

    @PutMapping("/update-store/{storeNo}")
    @Operation(summary = "어드민 매장정보 수정")
    public ResponseEntity<?> updateStoreInfo(@PathVariable Integer storeNo,@RequestBody AdminStoreRequestDto adminStoreRequestDto) {
        AdminStoreRequestDto requestDto = myStoreService.updateStore(storeNo,adminStoreRequestDto);
        return ResponseEntity.ok().body(requestDto);

    }
    @PutMapping("/updateImg/{storeNo}")
    @Operation(summary = "어드민 매장이미지 수정")
    public ResponseEntity<?> updateStoreImgInfo(@PathVariable Integer storeNo,@RequestPart(name = "newFile") MultipartFile newFile) throws IOException {
        AdminStoreImgRequestDto imgRequestDto = myStoreService.updateStoreImg(storeNo,newFile);
        return ResponseEntity.ok().body(imgRequestDto);
    }
    @DeleteMapping("/delete-store/{storeNo}")
    @Operation(summary = "매장삭제")
    public ResponseEntity<?> deleteStoreInfo(@PathVariable Integer storeNo) {
        myStoreService.deleteStore(storeNo);
        return ResponseEntity.noContent().build();

    }

}




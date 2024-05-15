package com.kkosoonnae.store.controller;

import com.kkosoonnae.store.dto.StoreDetailWithImageResponseDto;
import com.kkosoonnae.store.dto.StyleDto;
import com.kkosoonnae.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;
import java.util.List;

/**
 * packageName    : com.kkosoonnae.store.controller
 * fileName       : StoreController
 * author         : hagjoon
 * date           : 2024-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10        hagjoon       최초 생성
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/KkoSoonNae/store")
public class StoreController {


    private final StoreService storeService;

    @GetMapping("/{storeNo}")
    public ResponseEntity<StoreDetailWithImageResponseDto> StoreDetailWithImage(@PathVariable Integer storeNo) {
        try {
            StoreDetailWithImageResponseDto storeDetailWithImageResponseDto =
                    storeService.findStoreDetailWithImage(storeNo);
            return ResponseEntity.ok().body(storeDetailWithImageResponseDto);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            // return ResponseEntity.status(HttpStatus.NOT_FOUND).body((StoreDetailWithImageResponseDto) Collections.emptyList());
        }
    }

    @GetMapping("/{storeNo}/pethair")
    public ResponseEntity<List<StyleDto>> AllStyles(@PathVariable Integer storeNo) {
        try {
            List<StyleDto> styleDto = storeService.findStyles(storeNo);
            return ResponseEntity.ok(styleDto);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
}




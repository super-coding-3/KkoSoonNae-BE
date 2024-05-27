package com.kkosoonnae.style.controller;

import com.kkosoonnae.style.service.StyleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/KkoSoonNae/style")
@Slf4j
public class StyleController {
    private final StyleService styleService;

    @PutMapping("/{styleName}/update-image")
    public ResponseEntity<String> updateStyleImageByName(@PathVariable String styleName,
                                                         @RequestBody String newImageUrl){
        String result = styleService.updateStyleImageByName(styleName, newImageUrl);
        if (result.equals("완료")) {
            return ResponseEntity.ok("완료");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }
}

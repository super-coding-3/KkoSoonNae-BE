package com.kkosoonnae.map.controller;

import com.kkosoonnae.map.dto.GetNearByStore;
import com.kkosoonnae.map.dto.NearByStore;
import com.kkosoonnae.map.service.MapService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/KkoSoonNae/map")
@RequiredArgsConstructor
@Slf4j
public class MapController {
    private final MapService mapService;
    @PostMapping("/near")
    @Operation(summary = "내 근처 매장 정보 저장")
    public ResponseEntity<List<NearByStore>> saveStore(@RequestBody List<GetNearByStore> requestBody){
        List<NearByStore> nearByStores= mapService.saveNearByStore(requestBody);
        return ResponseEntity.ok(nearByStores);
    }
}

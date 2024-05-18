package com.kkosoonnae.map.service;

import com.kkosoonnae.jpa.entity.Store;
import com.kkosoonnae.jpa.repository.MapRepository;
import com.kkosoonnae.map.dto.GetNearByStore;
import com.kkosoonnae.map.dto.NearByStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MapService {
    private final MapRepository mapRepository;
//    public List<NearByStore> saveNearByStore(List<GetNearByStore> requestBody) {
//        for (GetNearByStore nearByStore : requestBody){
//
//        }
//
//
//    }
}

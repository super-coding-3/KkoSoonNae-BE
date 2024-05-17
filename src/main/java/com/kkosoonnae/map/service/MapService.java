package com.kkosoonnae.map.service;

import com.kkosoonnae.jpa.entity.Store;
import com.kkosoonnae.jpa.repository.MapRepository;
import com.kkosoonnae.jpa.repository.StoreRepository;
import com.kkosoonnae.map.WebCrawler;
import com.kkosoonnae.map.dto.DetailInfo;
import com.kkosoonnae.map.dto.GetNearByStore;
import com.kkosoonnae.map.dto.NearByStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Service
@RequiredArgsConstructor
@Slf4j
public class MapService {
    private final MapRepository mapRepository;
    private final WebCrawler webCrawler;

    public List<NearByStore> saveNearByStore(List<GetNearByStore> requestBody) {
        List<NearByStore> nearByStores = new ArrayList<>();

        for (GetNearByStore nearByStore : requestBody){
            try {
                DetailInfo detailInfo = webCrawler.crawlDetailInfoFromUrl(nearByStore.getUrl());

                Store store =new Store(
                        nearByStore.getPlaceName(),
                        null,
                        detailInfo.getZipCode(),
                        detailInfo.getAddress(),
                        null,
                        detailInfo.getPhone(),
                        null,
                        detailInfo.getRoadAddress()
                );

                mapRepository.save(store);

                NearByStore nearByStore1 = new NearByStore();
                nearByStore1.setStoreName(nearByStore.getPlaceName());
                nearByStore1.setPhone(detailInfo.getPhone());
                nearByStore1.setAddress(detailInfo.getAddress());
                nearByStore1.setZipCode(detailInfo.getScope());
                nearByStore1.setRoadAddress(detailInfo.getRoadAddress());

                nearByStores.add(nearByStore1);
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        return nearByStores;
    }




    private String csvFilePath = "\"C:\\Users\\ehd38\\Downloads\\data.csv\"";
    public void saveDataFromCsvOnServer() throws Exception{
        try(BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))){
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                // CSV 파일을 파싱하여 데이터베이스에 저장하는 로직을 여기에 작성
                String firstColumn = tokenizer.nextToken();
                System.out.println(firstColumn);
                log.info(firstColumn);
            }
        }catch (Exception e) {
            throw new Exception("서버의 CSV 파일을 읽는 동안 오류가 발생했습니다.", e);
        }
    }
}

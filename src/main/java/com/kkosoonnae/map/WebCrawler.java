package com.kkosoonnae.map;

import com.kkosoonnae.map.dto.DetailInfo;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class WebCrawler {
    public DetailInfo crawlDetailInfoFromUrl(String url) throws IOException{
        Document doc = Jsoup.connect(url).get();
        log.info("doc"+doc.title());

        //우편주소 정보 추출
        Elements addressElement = doc.select(".txt_address");
        String zipCode = null;
        String roadAddress=null;
        String address=null;
        if (addressElement != null) {
            String fullAddress = addressElement.text().trim();
            int startIdx = fullAddress.indexOf("(우)");
            if (startIdx != -1) {
                // "(우)" 앞에 있는 정보 추출 (도로명 주소)
                roadAddress = fullAddress.substring(0, startIdx).trim();
                // "(우)" 뒤에 있는 정보 추출 (우편번호)
                zipCode = fullAddress.substring(startIdx + 3).trim();
            }
        }
        log.info("addressElement:"+addressElement);
        log.info("roadaddress"+roadAddress);
        log.info("zipcode"+zipCode);


        //지번 주소 추출
        Element addrNumElement = doc.selectFirst("span.txt_addrnum");
        if (addrNumElement != null) {
            // "지번" 다음에 오는 정보 추출 (지번 주소)
            address = addrNumElement.text().trim().replace("지번", "").trim();
        }
        log.info("address"+address);
        //영업시간
        Element timeElement=doc.selectFirst("span.time_operation");
        String time=timeElement != null ? timeElement.text() : null;

        //전화번호
        Element phoneElement=doc.selectFirst("span.txt_contact");
        String phone = phoneElement != null ? phoneElement.text() : null;
        log.info("phone"+phone);

        DetailInfo detailInfo = new DetailInfo();
        detailInfo.setZipCode(zipCode);
        detailInfo.setAddress(address);
        detailInfo.setRoadAddress(roadAddress);
        detailInfo.setTime(time);
        detailInfo.setPhone(phone);

        return detailInfo;
    }
}

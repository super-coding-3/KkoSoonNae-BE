package com.kkosoonnae.map;

import com.kkosoonnae.map.dto.DetailInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;

public class WebCrawler {
    public DetailInfo crawlDetailInfoFromUrl(String url) throws IOException{
        Document doc = Jsoup.connect(url).get();

        //우편주소 정보 추출
        Element scopeElement = doc.selectFirst("span.txt_address");
        String scope = null;
        if (scopeElement != null) {
            String fullAddress = scopeElement.text().trim();
            int startIdx = fullAddress.lastIndexOf("(우)");
            if (startIdx != -1) {
                // "(우)" 뒤에 있는 정보 추출
                scope = fullAddress.substring(startIdx + 5).trim();
            }
        }

        //도로명 주소 추출
        Element roadAddressElement =doc.selectFirst("span.txt_address");
        String roadAddress=null;
        if (roadAddressElement !=null){
            String fullAddress = roadAddressElement.text().trim();
            int endIdx = fullAddress.indexOf("(우)");
            if (endIdx != -1) {
                // "(우)" 앞에 있는 정보 추출
                roadAddress = fullAddress.substring(0, endIdx).trim();
            }
        }

        //지번 주소 추출
        Element addrElement = doc.selectFirst("span.txt_addrnum");
        String addr = null;
        if (addrElement != null) {
            String fullAddr = addrElement.text().trim();
            int startIdx = fullAddr.indexOf("지번");
            if (startIdx != -1) {
                // "지번" 다음에 오는 정보 추출
                addr = fullAddr.substring(startIdx + 2).trim();
            }
        }

        //영업시간
        Element timeElement=doc.selectFirst("span.time_operation");
        String time=timeElement != null ? timeElement.text() : null;

        //전화번호
        Element phoneElement=doc.selectFirst("span.txt_contact");
        String phone = phoneElement != null ? phoneElement.text() : null;

        DetailInfo detailInfo = new DetailInfo();
        detailInfo.setScope(scope);
        detailInfo.setAddress(addr);
        detailInfo.setRoadAddress(roadAddress);
        detailInfo.setTime(time);
        detailInfo.setPhone(phone);

        return detailInfo;
    }
}

package com.kkosoonnae.style.service;

import com.kkosoonnae.jpa.entity.Style;
import com.kkosoonnae.jpa.repository.StyleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StyleService {
    private final StyleRepository styleRepository;

    @Transactional
    public String updateStyleImageByName(String styleName, String newImageUrl) {
        List<Style> styles = styleRepository.findByStyleName(styleName);
        if (styles.isEmpty()) {
            return "스타일을 찾을 수 없습니다.";
        }

        // 여러 결과 중 첫 번째 결과를 업데이트
        for (Style style : styles) {
            style.updateImageUrl(newImageUrl);
        }

        return "완료";
    }
}

package com.kkosoonnae.store.util;

import com.kkosoonnae.jpa.enu.StyleType;

import java.util.*;
import java.util.stream.Collectors;

public class RandomStyleTypeUtil {
    private static final Random random =new Random();
    private static final Set<StyleType> usedStyleTypes= new HashSet<>();

    public static StyleType getRandomStyleType(){
        StyleType[] styles = StyleType.values();

        // 사용되지 않은 스타일 타입 목록 생성
        List<StyleType> availableStyles = Arrays.stream(styles)
                .filter(styleType -> !usedStyleTypes.contains(styleType))
                .collect(Collectors.toList());

        // 사용 가능한 스타일 타입 중에서 랜덤으로 선택
        StyleType randomStyleType = availableStyles.get(random.nextInt(availableStyles.size()));

        // 선택된 스타일 타입을 사용된 목록에 추가
        usedStyleTypes.add(randomStyleType);

        // 사용된 스타일 타입 목록이 모두 사용되었다면 초기화
        if (usedStyleTypes.size() == styles.length) {
            usedStyleTypes.clear();
        }

        return randomStyleType;
    }
}

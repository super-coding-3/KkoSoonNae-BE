package com.kkosoonnae.store.util;

import com.kkosoonnae.jpa.enu.StyleType;

import java.util.Random;

public class RandomStyleTypeUtil {
    private static final Random random =new Random();

    public static StyleType getRandomStyleType(){
        StyleType[] styles = StyleType.values();
        return styles[random.nextInt(styles.length)];
    }
}

package com.kkosoonnae.map.dto;

import lombok.Data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Data
public class StoreCsvData {
    private String name;
    private String phone;
    private String addr;

    public static List<String> getFieldNames(){
        Field[] declaredFields = StoreCsvData.class.getDeclaredFields();
        List<String> result = new ArrayList<>();
        for (Field declaredField:declaredFields){
            result.add(declaredField.getName());
        }
        return result;
    }
}

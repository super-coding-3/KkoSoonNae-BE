//package com.kkosoonnae.map.dto;
//
//import com.kkosoonnae.jpa.entity.Store;
//import lombok.Data;
//
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.List;
//
//@Data
//public class StoreCsvData {
//    private String name;
//    private String phone;
//    private String addr;
//
//    public static List<String> getFieldNames(){
//        Field[] declaredFields = StoreCsvData.class.getDeclaredFields();
//        List<String> result = new ArrayList<>();
//        for (Field declaredField:declaredFields){
//            result.add(declaredField.getName());
//        }
//        return result;
//    }
//
//    public Store toEntity(){
//        return Store.builder()
//                .storeName(this.name)
//                .phone(this.phone)
//                .address(this.addr)
//                .build();
//    }
//}

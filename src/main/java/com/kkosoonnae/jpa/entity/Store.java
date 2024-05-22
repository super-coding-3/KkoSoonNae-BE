package com.kkosoonnae.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "STORE")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STORE_NO")
    private Integer storeNo;

    @Column(name = "STORE_NAME")
    private String storeName;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "LAT")
    private Double lat;

    @Column(name = "LON")
    private Double lon;

    @Column(name = "ROAD_ADDRESS")
    private String roadAddress;

    @Column(name = "OPENING_TIME")
    private LocalTime openingTime;

    @Column(name = "CLOSING_TIME")
    private LocalTime closingTime;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<StoreImg> storeImg;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Style> style;


    public Store(String storeName, String phone, Double lat,
                 Double lon, String roadAddress) {
        this.storeName = storeName;
        this.content = storeName+"매장 입니다. ";
        this.phone = phone;
        this.lat = lat;
        this.lon = lon;
        this.roadAddress = roadAddress;
        this.openingTime = LocalTime.parse("09:00");
        this.closingTime = LocalTime.parse("18:00");
    }

    public void setStyles(List<Style> styles) {
        this.style = styles;
    }

    public void setStoreImages(List<StoreImg> storeImages) {
        this.storeImg = storeImages;
    }




    public Store(String placeName, Object o, String zipCode, String address, Object o1, String phone, Object o2, String roadAddress) {

    }

    public Store(String storeName, String address, String phone) {
        this.storeName = storeName;
        this.roadAddress = address;
        this.phone = phone;
    }



}

package com.kkosoonnae.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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

    @Column(name = "ZIP_CODE")
    private String zipCode;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "ADDRESS_DTL")
    private String addressDtl;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "STORE_OPER_DT")
    private LocalDateTime storeOperDt;

    @Column(name = "ROAD_ADDRESS")
    private String roadAddress;

    @OneToMany(mappedBy = "storeNo" , fetch = FetchType.LAZY)
    private List<StoreImg> storeImg;

}

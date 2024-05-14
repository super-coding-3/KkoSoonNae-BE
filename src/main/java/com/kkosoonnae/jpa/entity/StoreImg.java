package com.kkosoonnae.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "sotre_img")
public class StoreImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STORE_IMG_NO")
    private Integer storeImgNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_NO")
    private Store storeNo;

    @Column(name = "IMG")
    private String img;

}

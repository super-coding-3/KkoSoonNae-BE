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
@Table(name = "STORE_IMG")
public class StoreImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STORE_IMG_NO")
    private Integer storeImgNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_NO")
    private Store store;

    @Column(name = "IMG")
    private String img;



    public StoreImg(Store store, String imageUrl){
        this.store=store;
        this.img=imageUrl;
    }

    public void updateImageUrl(String newImageUrl) {
        this.img = newImageUrl;
    }
}

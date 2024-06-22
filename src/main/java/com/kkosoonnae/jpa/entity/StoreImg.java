package com.kkosoonnae.jpa.entity;

import com.kkosoonnae.president.mystore.dto.AdminStoreRequestDto;
import jakarta.persistence.*;
import lombok.*;

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


    public StoreImg(String url) {

    }

    public void setImg(String img) {
        this.img = img;
    }

    public void updateImageUrl(String newImageUrl) {
        this.img = newImageUrl;
    }
}

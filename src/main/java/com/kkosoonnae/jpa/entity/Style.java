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
@Table(name = "STYLE")
public class Style {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STYLE_NO")
    private Integer styleNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_NO")
    private Store store;

    @Column(name = "STYLE_NAME")
    private String styleName;

    @Column(name = "IMG")
    private String img;

    @Column(name = "PRICE")
    private Integer price;

}

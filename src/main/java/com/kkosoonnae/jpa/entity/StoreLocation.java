package com.kkosoonnae.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "STORE_LOCATION")
public class StoreLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCATION_NO")
    private Integer locationNo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_NO", referencedColumnName = "STORE_NO")
    private Store store;

    @Column(name = "LAT")
    private Double lat;

    @Column(name = "LON")
    private Double lon;

}

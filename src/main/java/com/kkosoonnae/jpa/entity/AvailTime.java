package com.kkosoonnae.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "AVAIL_TIME")
public class AvailTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AVAIL_NO")
    private Integer availNo;

    @Column(name = "STORE_NO")
    private Integer storeNo;

    @Column(name = "AVAIL_DATE")
    private LocalDate availDate;

    @Column(name = "AVAIL_TIME")
    private LocalTime availTime;
}

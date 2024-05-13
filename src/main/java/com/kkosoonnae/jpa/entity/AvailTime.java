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
@Table(name = "avail_time")
public class AvailTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "avail_no")
    private Integer availNo;

    @Column(name = "stroe_no")
    private Integer storeNo;

    @Column(name = "avail_date")
    private LocalDate availDate;

    @Column(name = "avail_time")
    private LocalTime availTime;
}

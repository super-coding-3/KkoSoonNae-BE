package com.kkosoonnae.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.kkosoonnae.jpa.entity
 * fileName       : Point
 * author         : hagjoon
 * date           : 2024-05-20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-20        hagjoon       최초 생성
 */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "POINT")
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POINT_NO")
    private Integer pointNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CSTMR_NO")
    private CustomerBas customerBas;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "POINT_RM")
    private Double pointRm;
}

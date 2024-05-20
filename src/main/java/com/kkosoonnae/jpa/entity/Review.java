package com.kkosoonnae.jpa.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "REVIEW")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REVIEW_NO")
    private Integer reviewNo;

    @ManyToOne
    @JoinColumn(name = "STORE_NO")
    private Store store;

    @Column(name = "CSTMR_NO")
    private Integer cstmrNo;

    @Column(name = "IMG")
    private String img;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "REVIEW_DT")
    private LocalDateTime reviewDt;

    @Column(name = "SCOPE")
    private Integer scope;

    @Column(name = "AVERAGE_SCOPE")
    private Integer averageScope;

}

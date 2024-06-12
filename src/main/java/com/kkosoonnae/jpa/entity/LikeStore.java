package com.kkosoonnae.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LIKE_STORE")
public class LikeStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LIKE_NO")
    private Integer likeNo;

    @ManyToOne
    @JoinColumn(name = "CSTMR_NO")
    private CustomerBas customerBas;

    @ManyToOne
    @JoinColumn(name = "STORE_NO")
    private Store store;

    @Column(name = "CREATE_DT")
    private LocalDateTime createDt;

}

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
    private Store storeNo;

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

    private Review(Builder builder) {
        this.cstmrNo = builder.cstmrNo;
        this.storeNo = builder.storeNo;
        this.content = builder.content;
        this.reviewDt = builder.reviewDt;
    }

    public static class Builder {
        private Integer cstmrNo;
        private Store storeNo;
        private String content;
        private LocalDateTime reviewDt;

        public Builder cstmrNo(Integer cstmrNo) {
            this.cstmrNo = cstmrNo;
            return this;
        }

        public Builder storeNo(Store storeNo) {
            this.storeNo = storeNo;
            return this;
        }


        public Builder content(String content) {
            this.content = content;
            return this;
        }


        public Builder createdAt(LocalDateTime reviewDt) {
            this.reviewDt = reviewDt;
            return this;
        }

        public Review build() {
            return new Review(this);
        }
    }


}

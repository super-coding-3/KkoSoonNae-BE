package com.kkosoonnae.store.dto;

import com.kkosoonnae.jpa.entity.Review;
import com.kkosoonnae.jpa.entity.Store;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class ReviewDto {

    private Integer reviewNo;

    private Store store;

    private Integer cstmrNo;

    private String img;

    private String content;

    private LocalDateTime reviewDt;

    private Integer scope;

    private Integer averageScope;

    public ReviewDto(Review review) {
        this.reviewNo = review.getReviewNo();
        this.cstmrNo = review.getCstmrNo();
        this.img = review.getImg();
        this.content = review.getContent();
        this.reviewDt = review.getReviewDt();
        this.scope = review.getScope();
    }
}

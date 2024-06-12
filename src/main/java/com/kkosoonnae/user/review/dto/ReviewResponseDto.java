package com.kkosoonnae.user.review.dto;

import com.kkosoonnae.jpa.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewResponseDto {

    private Integer id;

    private Integer reviewNo;

    private Store store;

    private Integer cstmrNo;

    private String img;

    private String content;

    private LocalDateTime reviewDt;

    private Integer scope;

    private Integer averageScope;

    public ReviewResponseDto(Integer reviewNo, Store store, Integer cstmrNo, String img, String content, LocalDateTime reviewDt, Integer scope) {
    }
}

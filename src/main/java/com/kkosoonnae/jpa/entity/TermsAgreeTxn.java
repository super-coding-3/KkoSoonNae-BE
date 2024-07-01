package com.kkosoonnae.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


/**
 * packageName    : com.kkosoonnae.jpa.entity
 * fileName       : TermAgreeTxn
 * author         : hagjoon
 * date           : 2024-06-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-24        hagjoon       최초 생성
 */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "TERMS_AGREE_TXN")
public class TermsAgreeTxn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AGREE_NO")
    private Integer agreeNo;

    @Column(name = "TERM_NO")
    private Integer termNo;

    @Column(name = "CSTMR_NO")
    private Integer cstmrNo;

    @Column(name = "AGREE_YN")
    private String agreeYn;

    @Column(name = "AGREE_DT")
    private LocalDateTime agreeDt;

}

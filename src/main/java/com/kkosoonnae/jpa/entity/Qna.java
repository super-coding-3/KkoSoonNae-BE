package com.kkosoonnae.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * packageName    : com.kkosoonnae.jpa.entity
 * fileName       : Qna
 * author         : hagjoon
 * date           : 2024-05-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-10        hagjoon       최초 생성
 */
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "QnA")
public class Qna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QNA_NO")
    private Integer qnaNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CSTMR_NO")
    private CustomerBas cstmrNo;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "QNA_STATE")
    private String qnaState;

    @Column(name = "CREATE_DT")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createDt;

}

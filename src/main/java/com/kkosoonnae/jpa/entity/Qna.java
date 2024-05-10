package com.kkosoonnae.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

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
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "qna")
public class Qna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QNA_NO")
    private Integer qnaNo;

    @Column(name = "CSTMR_NO")
    private Integer cstmrNO;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

}

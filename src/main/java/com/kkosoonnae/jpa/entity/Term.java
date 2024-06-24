package com.kkosoonnae.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.kkosoonnae.jpa.entity
 * fileName       : Term
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
@Table(name = "TERM")
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TERM_NO")
    private Integer termNo;

    @Column(name = "TERM_NAME")
    private String termName;

    @Column(name = "TERM_CONTENT")
    private String termContent;

    @Column(name = "TERM_FLAG")
    private String termFlag;


}

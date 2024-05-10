package com.kkosoonnae.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * packageName    : com.kkosoonnae.jpa.entity
 * fileName       : Pet
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
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PET_NO")
    private Integer petNo;

    @Column(name = "CSTMR_NO")
    private Integer cstmrNo;

    @Column(name = "IMG")
    private String img;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "BIRTH_DT")
    private String birthDt;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "WEIGHT")
    private String weight;
}

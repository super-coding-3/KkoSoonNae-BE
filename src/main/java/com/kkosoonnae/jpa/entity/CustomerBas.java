package com.kkosoonnae.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

/**
 * packageName    : com.kkosoonnae.jpa
 * fileName       : Customer
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
@Table(name = "customer_bas")
public class CustomerBas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CSTMR_NO")
    private Integer cstmrNo;

    @Column(name = "LOGIN_ID")
    private String loginId;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "CREATE_DT")
    private LocalDateTime createDt;

    @Column(name = "CSTMR_DIV_CD")
    @Enumerated(EnumType.STRING)
    private RoleType cstmrDivCd;

}

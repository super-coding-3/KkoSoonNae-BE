package com.kkosoonnae.jpa.entity;

import com.kkosoonnae.president.info.dto.InfoUpdateRq;
import com.kkosoonnae.president.info.dto.PwRq;
import jakarta.persistence.*;
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
@Table(name = "CUSTOMER_BAS")
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

    @OneToOne(mappedBy = "customerBas")
    private CustomerDtl customerDtl;

    public void updatePassword(String encodeNewPassword){
        this.password = encodeNewPassword;
    }

    public void updateEmail(InfoUpdateRq rq){
        this.email = rq.getEmail();
    }

}

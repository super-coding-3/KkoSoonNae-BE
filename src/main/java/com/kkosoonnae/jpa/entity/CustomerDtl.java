package com.kkosoonnae.jpa.entity;

import com.kkosoonnae.customer.dto.InfoDto;
import jakarta.persistence.*;
import lombok.*;

/**
 * packageName    : com.kkosoonnae.jpa.entity
 * fileName       : CustomerDtl
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
@Table(name = "customer_dtl")
public class CustomerDtl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CSTMR_NO")
    private Integer cstmrNo;

    @Column(name = "NICKNAME")
    private String nickName;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "ZIP_CODE")
    private String zipCode;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "ADDRESS_DTL")
    private String addressDtl;

    @OneToOne(mappedBy = "customerDtl")
    private CustomerBas customerBas;

    public void updateFromDto(InfoDto infoDto) {
        this.nickName = infoDto.getNickName();
        this.phone = infoDto.getPhone();
        this.zipCode = infoDto.getZipCode();
        this.address = infoDto.getAddress();
        this.addressDtl = infoDto.getAddressDtl();
    }

}

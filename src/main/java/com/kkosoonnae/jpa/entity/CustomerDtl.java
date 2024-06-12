package com.kkosoonnae.jpa.entity;

import com.kkosoonnae.user.customer.dto.InfoDto;
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
@Table(name = "CUSTOMER_DTL")
public class CustomerDtl {

    @Id
    @Column(name = "CSTMR_NO")
    private Integer cstmrNo;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId // cstmrNo 필드를 매핑
    @JoinColumn(name = "CSTMR_NO")
    private CustomerBas customerBas;

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

    public void updateCustomer(InfoDto infoDto){
        this.nickName = infoDto.getNickName();
        this.phone = infoDto.getPhone();
        this.zipCode = infoDto.getZipCode();
        this.address = infoDto.getAddress();
        this.addressDtl = infoDto.getAddressDtl();
    }
}

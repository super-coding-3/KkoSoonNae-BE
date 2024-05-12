package com.kkosoonnae.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.kkosoonnae.customer.dto
 * fileName       : InfoDto
 * author         : hagjoon
 * date           : 2024-05-12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-05-12        hagjoon       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InfoDto {

    private Integer cstmrNo;

    private String nickName;

    private String phone;

    private String zipCode;

    private String address;

    private String addressDtl;

}

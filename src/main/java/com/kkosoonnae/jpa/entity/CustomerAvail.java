package com.kkosoonnae.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "CUSTOMER_AVAIL")
public class CustomerAvail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CSTMR_AVAIL_NO")
    private Integer cstmrAvailNo;

    @Column(name = "CSTMR_NO")
    private Integer cstmrNo;

    @Column(name = "RESERVATION_NO")
    private Integer reservationNo;

    @Column(name = "AVAIL_NO")
    private Integer availNo;

    public CustomerAvail(Integer cstmrNo, Integer reservationNo, Integer availNo) {
        this.cstmrNo = cstmrNo;
        this.reservationNo = reservationNo;
        this.availNo = availNo;
    }
}

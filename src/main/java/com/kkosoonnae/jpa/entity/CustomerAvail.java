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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CSTMR_NO")
    private CustomerBas cstmrNo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESERVATION_NO")
    private Reservation reservationNo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AVAIL_NO")
    private AvailTime availNo;

    public CustomerAvail(CustomerBas cstmrNo, Reservation reservationNo, AvailTime availNo) {
        this.cstmrNo = cstmrNo;
        this.reservationNo = reservationNo;
        this.availNo = availNo;
    }
}

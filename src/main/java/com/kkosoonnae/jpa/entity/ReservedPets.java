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
@Table(name = "RESERVED_PETS")
public class ReservedPets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVATION_NO")
    private Integer reservationNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PET_NO")
    private Pet pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AVAIL_NO")
    private AvailTime avail;
}

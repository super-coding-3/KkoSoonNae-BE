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
    @Column(name = "RESERVED_PETS_NO")
    private Integer reservedPetsNo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESERVATION_NO")
    private Reservation reservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PET_NO")
    private Pet pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AVAIL_NO")
    private AvailTime avail;

    @Column(name = "AVAIL_STATUS")
    private String availStatus;

    public ReservedPets(Reservation reservation, Pet pet, AvailTime avail) {
        this.reservation = reservation;
        this.pet = pet;
        this.avail = avail;
        this.availStatus = "Y";
    }

    public void markAsNotAvailable() {
        this.availStatus = "N";
    }

}

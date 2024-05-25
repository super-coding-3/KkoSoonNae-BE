package com.kkosoonnae.jpa.entity;

import com.kkosoonnae.config.auth.PrincipalDetails;
import com.kkosoonnae.pet.dto.PetAddDto;
import com.kkosoonnae.pet.dto.PetInfoDto;
import com.kkosoonnae.pet.dto.PetUpdate;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "PET")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PET_NO")
    private Integer petNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CSTMR_NO")
    private CustomerBas customerBas;

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

    public Pet(CustomerBas customerBas, String type, String weight) {
        this.customerBas = customerBas;
        this.type = type;
        this.weight = weight;
    }

    public void updatePet(PetUpdate petUpdate){
        this.img = petUpdate.getImg();
        this.name = petUpdate.getName();
        this.type = petUpdate.getType();
        this.birthDt = petUpdate.getBirthDt();
        this.gender = petUpdate.getGender();
        this.weight = petUpdate.getWeight();
    }
}

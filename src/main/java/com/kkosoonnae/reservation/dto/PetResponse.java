package com.kkosoonnae.reservation.dto;

import com.kkosoonnae.jpa.entity.Pet;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PetResponse {
    @Schema(description = "견종/묘종")
    private Integer petNumber;

    @Schema(description = "펫 이름")
    private String petName;

    @Schema(description = "펫 이미지")
    private String petImg;

    @Schema(description = "견종/묘종")
    private String breed;

    @Schema(description = "몸무게")
    private String weight;

    public static PetResponse petToPetResponse(Pet pet) {
        return PetResponse.builder()
                .petNumber(pet.getPetNo())
                .petName(pet.getName())
                .petImg(pet.getImg())
                .breed(pet.getType())
                .weight(pet.getWeight())
                .build();
    }

    public static List<PetResponse> petsToPetResponse(List<Pet> pets) {
        return pets.stream().map(PetResponse::petToPetResponse).collect(Collectors.toList());
    }
}

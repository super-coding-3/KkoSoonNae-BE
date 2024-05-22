package com.kkosoonnae.reservation.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kkosoonnae.jpa.entity.Pet;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.List;
import java.util.stream.Collectors;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PetResponse {
    @Schema(description = "견종/묘종")
    private Integer petNumber;

    @Schema(description = "견종/묘종")
    private String breed;

    @Schema(description = "몸무게")
    private String weight;

    public static PetResponse petToPetResponse(Pet pet) {
        return PetResponse.builder()
                .petNumber(pet.getPetNo())
                .breed(pet.getType())
                .weight(pet.getWeight())
                .build();
    }

    public static List<PetResponse> petsToPetResponse(List<Pet> pets) {
        return pets.stream().map(PetResponse::petToPetResponse).collect(Collectors.toList());
    }
}

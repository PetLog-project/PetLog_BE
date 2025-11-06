package com.petlog.pet.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdatePetProfileRequestDto(

    String imageUrl,

    @Schema(description = "10글자 제한")
    String name,

    @Schema(description = "4글자 제한")
    String age,

    @Schema(description = "6글자 제한")
    String weight,

    @Schema(description = "FEMALE/MALE")
    String gender

) {
}

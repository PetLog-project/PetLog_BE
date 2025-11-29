package com.petlog.pet.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record GetPetProfileDto(

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
    public GetPetProfileDto(
        final String imageUrl,
        final String name,
        final String age,
        final String weight,
        final String gender
    ) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.gender = gender;
    }
}

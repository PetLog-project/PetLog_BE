package com.petlog.pet.service.dto;

public record UpdatePetProfileDto(

    String imageUrl,
    String name,
    String age,
    String weight,
    String gender

) {
}

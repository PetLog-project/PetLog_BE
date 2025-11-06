package com.petlog.petgroup.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record CreatePetGroupRequestDto(

    String imageUrl,

    @Schema(description = "10글자 제한")
    String name,

    @Schema(description = "4글자 제한")
    String age,

    @Schema(description = "6글자 제한")
    String weight,

    @Schema(description = "FEMALE/MALE")
    String gender,

    int feedingCycle,

    LocalDateTime lastFeedingTime,

    int wateringCycle,

    LocalDateTime lastWateringTime,

    @Schema(description = "1000글자 제한")
    String note

) {
}

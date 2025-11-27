package com.petlog.petgroup.service.dto;

import com.petlog.petgroup.controller.dto.request.CreatePetGroupRequestDto;

import java.time.LocalDateTime;

public record CreatePetGroupDto(

    String imageUrl,
    String name,
    String age,
    String gender,
    String weight,
    int feedingCycle,
    LocalDateTime lastFeedingTime,
    int wateringCycle,
    LocalDateTime lastWateringTime,
    String note

) {
    public static CreatePetGroupDto from(final CreatePetGroupRequestDto request) {
        return new CreatePetGroupDto(
            request.imageUrl(),
            request.name(),
            request.age(),
            request.gender(),
            request.weight(),
            request.feedingCycle(),
            request.lastFeedingTime(),
            request.wateringCycle(),
            request.lastWateringTime(),
            request.note()
        );
    }
}

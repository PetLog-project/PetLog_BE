package com.petlog.pet.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record GetWateringInfoDto(

    int wateringCycle,

    LocalDateTime lastWateringTime,

    String lastCheckerName,

    @Schema(description = "100글자 제한")
    String lastMemo

) {
    public GetWateringInfoDto(final int wateringCycle, final LocalDateTime lastWateringTime, final String lastCheckerName, final String lastMemo) {
        this.wateringCycle = wateringCycle;
        this.lastWateringTime = lastWateringTime;
        this.lastCheckerName = lastCheckerName;
        this.lastMemo = lastMemo;
    }
}

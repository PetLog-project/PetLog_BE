package com.petlog.pet.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record GetFeedingInfoDto(

    int feedingCycle,

    LocalDateTime lastFeedingTime,

    String lastCheckerName,

    @Schema(description = "100글자 제한")
    String lastMemo

) {
    public GetFeedingInfoDto(final int feedingCycle, final LocalDateTime lastFeedingTime, final String lastCheckerName, final String lastMemo) {
        this.feedingCycle = feedingCycle;
        this.lastFeedingTime = lastFeedingTime;
        this.lastCheckerName = lastCheckerName;
        this.lastMemo = lastMemo;
    }
}
